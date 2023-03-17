package dev.wybran.perceptus.service;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
public class SSHService {
    private JSch jsch;

    public SSHService() {
        jsch = new JSch();
    }

    public Session createSession() {
        Session session = null;
        try {
            session = jsch.getSession("pi", "192.168.1.150", 22);
            session.setPassword("raspberry");
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
        } catch (JSchException e) {
            e.printStackTrace();
        }
        return session;
    }

    public String executeCommand(Session session, String command) {
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
