package dev.wybran.perceptus.controller;

import dev.wybran.perceptus.dto.response.CommandHistoryResponse;
import dev.wybran.perceptus.model.CommandsHistory;
import dev.wybran.perceptus.repository.CommandsHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/history")
public class CommandHistoryController {
    private final CommandsHistoryRepository historyRepository;

    @GetMapping
    public List<CommandHistoryResponse> getHistory() {
        List<CommandsHistory> history = historyRepository.findAll();
        List<CommandHistoryResponse> response = new ArrayList<>();
        history.forEach(h -> {
            response.add(new CommandHistoryResponse(h.getId(), h.getCommand(), h.getOutput(), h.getHost().getId(), h.getCreated_at()));
        });
        return response;
    }
}
