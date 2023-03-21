package dev.wybran.perceptus.controller;

import dev.wybran.perceptus.dto.request.CommandRequest;
import dev.wybran.perceptus.dto.request.SessionRequest;
import dev.wybran.perceptus.dto.response.CommandResponse;
import dev.wybran.perceptus.dto.response.NewSessionResponse;
import dev.wybran.perceptus.service.SSHService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SSHController {

    private final SSHService sshService;

    @PostMapping("/newSession")
    public NewSessionResponse executeCommand(@RequestBody SessionRequest req) {
        return sshService.newSession(req);
    }

    @PostMapping("/executeCommand")
    public CommandResponse executeCommand(@RequestBody CommandRequest req) {
        return sshService.executeCommand(req.getUuid(), req.getCommand());
    }
}
