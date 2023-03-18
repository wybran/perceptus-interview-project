package dev.wybran.perceptus.component;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import dev.wybran.perceptus.dto.request.CommandRequest;
import dev.wybran.perceptus.exception.BadRequestException;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SSHSessionManager {

    private final Map<String, Session> sessions = new HashMap<>();

    public Session getSession(CommandRequest req) {
        String identifier = req.getIp()+req.getPort()+req.getUsername();
        Session session = sessions.get(identifier);
        if (session == null || !session.isConnected()) {
            try {
                JSch jsch = new JSch();
                session = jsch.getSession(req.getUsername(), req.getIp(), req.getPort());
                session.setPassword(req.getPassword());
                session.setConfig("StrictHostKeyChecking", "no");
                session.setTimeout(10000);
                session.connect();
                sessions.put(identifier, session);
            } catch (JSchException ex) {
                throw new BadRequestException(ex.getMessage());
            }
        }
        return session;
    }
}
