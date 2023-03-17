package dev.wybran.perceptus.controller;

import dev.wybran.perceptus.dto.request.HostRequest;
import dev.wybran.perceptus.model.Host;
import dev.wybran.perceptus.repository.HostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/hosts")
public class HostController {

    private final HostRepository hostRepository;

    @GetMapping()
    public List<Host> getHosts() {
        return hostRepository.findAll();
    }

    @PostMapping()
    public Host addHost(@RequestBody HostRequest req) {
        Host host = new Host(req.getName(), req.getIp(), req.getUsername(), req.getPassword(), req.getPort());
        return hostRepository.save(host);
    }
}
