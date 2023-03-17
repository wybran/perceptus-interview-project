package dev.wybran.perceptus.controller;

import com.jcraft.jsch.Session;
import dev.wybran.perceptus.model.Host;
import dev.wybran.perceptus.repository.HostRepository;
import dev.wybran.perceptus.service.SSHService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class SSHController {

    private final SSHService sshService;
    private final HostRepository hostRepository;

    @PostMapping("/ssh/{hostId}")
    public String executeCommand(@PathVariable("hostId") Long hostId, @RequestBody String command) throws Exception {
        Host host = hostRepository.findById(hostId).orElseThrow();
        return sshService.executeCommand(host, command);
    }
}
