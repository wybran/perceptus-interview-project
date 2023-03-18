package dev.wybran.perceptus.service;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import dev.wybran.perceptus.component.SSHSessionManager;
import dev.wybran.perceptus.dto.request.SessionRequest;
import dev.wybran.perceptus.exception.BadRequestException;
import dev.wybran.perceptus.model.CommandsHistory;
import dev.wybran.perceptus.repository.CommandsHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
@AllArgsConstructor
public class SSHService {
    private final SSHSessionManager sessionManager;
    private final CommandsHistoryRepository historyRepository;

    public String newSession(SessionRequest req) {
        String uuid = UUID.randomUUID().toString();
        Session session = sessionManager.newSession(req, uuid);
        if (session.isConnected()) {
            return uuid;
        } else
            return "Error";
    }

    public String executeCommand(String uuid, String command) {
        Session session = sessionManager.getSession(uuid);
        if (session == null || !session.isConnected()) {
            throw new BadRequestException("Session not found");
        }
        ChannelExec channel = null;
        try {
            channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(command);
            channel.setInputStream(null);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ByteArrayOutputStream err = new ByteArrayOutputStream();
            channel.setOutputStream(out);
            channel.setErrStream(err);
            channel.connect();

            InputStream in = channel.getInputStream();
            byte[] tmp = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) break;
                    out.write(tmp, 0, i);
                }
                if (channel.isClosed()) {
                    int i = in.read(tmp, 0, 1024);
                    while (i >= 0) {
                        err.write(tmp, 0, i);
                        i = in.read(tmp, 0, 1024);
                    }
                    break;
                }
            }
            CommandsHistory history = new CommandsHistory(command, session.getHost());
            historyRepository.save(history);

            String outStr = out.toString();
            String errStr = err.toString();
            if (!errStr.isEmpty()) {
                return errStr;
            } else {
                return outStr;
            }
        } catch (JSchException | IOException e) {
            return "Error: " + e.getMessage();
        } finally {
            if (channel != null) {
                channel.disconnect();
            }
        }
    }

}
