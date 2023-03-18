package dev.wybran.perceptus.controller;

import dev.wybran.perceptus.dto.request.CommandRequest;
import dev.wybran.perceptus.service.SSHService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SSHController {

    private final SSHService sshService;

    @PostMapping("/ssh")
    public String executeCommand(@RequestBody CommandRequest req) {
        return sshService.executeCommand(req);
    }
}
