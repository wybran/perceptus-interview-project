package dev.wybran.perceptus.controller;

import dev.wybran.perceptus.exception.NotFoundException;
import dev.wybran.perceptus.model.Host;
import dev.wybran.perceptus.repository.HostRepository;
import dev.wybran.perceptus.service.SSHService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SSHController {

    private final SSHService sshService;
    private final HostRepository hostRepository;

    @PostMapping("/ssh/{hostId}")
    public String executeCommand(@PathVariable("hostId") Long hostId, @RequestBody String command) {
        Host host = hostRepository.findById(hostId).orElseThrow(() -> new NotFoundException("Host not found"));
        return sshService.executeCommand(host, command);
    }
}
