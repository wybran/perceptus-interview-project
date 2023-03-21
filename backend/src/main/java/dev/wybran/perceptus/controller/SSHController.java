package dev.wybran.perceptus.controller;

import dev.wybran.perceptus.dto.request.CommandRequest;
import dev.wybran.perceptus.dto.request.SessionRequest;
import dev.wybran.perceptus.dto.response.CommandResponse;
import dev.wybran.perceptus.dto.response.NewSessionResponse;
import dev.wybran.perceptus.service.SSHService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/session")
public class SSHController {

    private final SSHService sshService;

    @PostMapping()
    public NewSessionResponse newSession(@RequestBody SessionRequest req) {
        return sshService.newSession(req);
    }

    @PostMapping("/executeCommand")
    public CommandResponse executeCommand(@RequestBody CommandRequest req) {
        return sshService.executeCommand(req.getUuid(), req.getCommand());
    }

    @GetMapping()
    public List<NewSessionResponse> getSessions() {
        return sshService.getSessions();
    }

    @DeleteMapping("/{uuid}")
    public void removeSession(@PathVariable String uuid) {
        sshService.removeSession(uuid);
    }

}
