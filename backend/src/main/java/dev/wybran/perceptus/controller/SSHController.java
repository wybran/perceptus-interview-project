package dev.wybran.perceptus.controller;

import com.jcraft.jsch.Session;
import dev.wybran.perceptus.service.SSHService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SSHController {

    private final SSHService sshService;

    private Session session;

    @GetMapping("/ssh/session")
    public Session getSession() {
        if (session == null || !session.isConnected()) {
            session = sshService.createSession();
        }
        return session;
    }

    @PostMapping("/ssh")
    public String executeCommand(@RequestBody String command) {
        if (session == null || !session.isConnected()) {
            System.out.println("Creating new session");
            session = sshService.createSession();
        }
        return sshService.executeCommand(session, command);
    }
}
