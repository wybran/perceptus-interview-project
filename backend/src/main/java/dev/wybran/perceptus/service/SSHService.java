package dev.wybran.perceptus.service;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import dev.wybran.perceptus.component.SSHSessionManager;
import dev.wybran.perceptus.model.CommandsHistory;
import dev.wybran.perceptus.model.Host;
import dev.wybran.perceptus.repository.CommandsHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
@AllArgsConstructor
public class SSHService {
    private final JSch jsch = new JSch();
    private final SSHSessionManager sessionManager;
    private final CommandsHistoryRepository historyRepository;

    public String executeCommand(Host host, String command) throws Exception {
        Session session = sessionManager.getSession(host);
        ChannelExec channel = null;
        try {
            channel = (ChannelExec) session.openChannel("exec");
            channel.setCommand(command);
            channel.setInputStream(null);
            channel.setErrStream(System.err);
            InputStream in = channel.getInputStream();
            channel.connect();

            StringBuilder resultBuilder = new StringBuilder();
            byte[] tmp = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0) break;
                    resultBuilder.append(new String(tmp, 0, i));
                }
                if (channel.isClosed()) {
                    if (in.available() > 0) continue;
                    break;
                }
            }
            CommandsHistory history = new CommandsHistory(command, resultBuilder.toString(), host);
            historyRepository.save(history);

            return resultBuilder.toString();
        } catch (JSchException | IOException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        } finally {
            if (channel != null) {
                channel.disconnect();
            }
        }
    }
}
