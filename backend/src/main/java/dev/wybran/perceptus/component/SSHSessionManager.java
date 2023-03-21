package dev.wybran.perceptus.component;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import dev.wybran.perceptus.dto.request.SessionRequest;
import dev.wybran.perceptus.dto.response.NewSessionResponse;
import dev.wybran.perceptus.exception.BadRequestException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class SSHSessionManager {

    private final Map<String, Session> sessions = new HashMap<>();

    public Session newSession(SessionRequest req, String uuid) {
        Session session = sessions.get(uuid);
        if (session == null || !session.isConnected()) {
            try {
                JSch jsch = new JSch();
                session = jsch.getSession(req.getUsername(), req.getIp(), req.getPort());
                session.setPassword(req.getPassword());
                session.setConfig("StrictHostKeyChecking", "no");
                session.setTimeout(10000);
                session.connect();
                sessions.put(uuid, session);
            } catch (JSchException ex) {
                throw new BadRequestException(ex.getMessage());
            }
        }
        return session;
    }

    public Session getSession(String uuid) {
        return sessions.get(uuid);
    }

    public List<NewSessionResponse> getSessions() {
        List <NewSessionResponse> response = new ArrayList<>();
        sessions.forEach((uuid, session) -> {
            if (session.isConnected()) {
                response.add(new NewSessionResponse(uuid, session.getHost(), session.getPort(), session.getUserName()));
            }
        });
        return response;
    }

    public void removeSession(String uuid) {
        Session session = sessions.get(uuid);
        if (session != null && session.isConnected()) {
            session.disconnect();
        }
        sessions.remove(uuid);
    }
}
