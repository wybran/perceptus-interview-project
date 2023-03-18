package dev.wybran.perceptus.component;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import dev.wybran.perceptus.exception.BadRequestException;
import dev.wybran.perceptus.model.Host;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SSHSessionManager {

    private final Map<Long, Session> sessions = new HashMap<>();

    public Session getSession(Host host) {
        Session session = sessions.get(host.getId());
        if (session == null || !session.isConnected()) {
            try {
                JSch jsch = new JSch();
                session = jsch.getSession(host.getUsername(), host.getIp(), host.getPort());
                session.setPassword(host.getPassword());
                session.setConfig("StrictHostKeyChecking", "no");
                session.setTimeout(10000);
                session.connect();
                sessions.put(host.getId(), session);
            } catch (JSchException ex) {
                throw new BadRequestException(ex.getMessage());
            }
        }
        return session;
    }

    public void closeSession(Long hostId) {
        Session session = sessions.get(hostId);
        if (session != null && session.isConnected()) {
            session.disconnect();
            sessions.remove(hostId);
        }
    }

    public void closeAllSessions() {
        sessions.values().forEach(Session::disconnect);
        sessions.clear();
    }
}
