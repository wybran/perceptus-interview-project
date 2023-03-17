package dev.wybran.perceptus.controller;

import dev.wybran.perceptus.dto.response.CommandHistoryResponse;
import dev.wybran.perceptus.service.CommandsHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/{hostId}")
    public List<CommandHistoryResponse> getHistoryByHostId(@PathVariable("hostId") Long hostId) {
        return historyService.getHistoryByHostId(hostId);
    }
}
