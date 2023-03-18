package dev.wybran.perceptus.controller;

import dev.wybran.perceptus.dto.response.CommandHistoryResponse;
import dev.wybran.perceptus.service.CommandsHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/history")
public class CommandHistoryController {

    private final CommandsHistoryService historyService;

    @GetMapping
    public List<CommandHistoryResponse> getHistory() {
        return historyService.getHistory();
    }

    @PostMapping
    public List<CommandHistoryResponse> getHistoryByHostId(@RequestBody String hostIP) {
        return historyService.getHistoryByHostIP(hostIP);
    }
}
