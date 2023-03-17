package dev.wybran.perceptus.component;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import dev.wybran.perceptus.model.Host;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SSHSessionManager {

    private final Map<Long, Session> sessions = new HashMap<>();

    public Session getSession(Host host) throws Exception {
        Session session = sessions.get(host.getId());
        if (session == null || !session.isConnected()) {
            JSch jsch = new JSch();
            session = jsch.getSession(host.getUsername(), host.getIp(), host.getPort());
            session.setPassword(host.getPassword());
            session.setConfig("StrictHostKeyChecking", "no");
            session.connect();
            sessions.put(host.getId(), session);
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
