package dev.wybran.perceptus.service;

import dev.wybran.perceptus.dto.response.CommandHistoryResponse;
import dev.wybran.perceptus.model.CommandsHistory;
import dev.wybran.perceptus.repository.CommandsHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommandsHistoryService {
    private final CommandsHistoryRepository historyRepository;

    public List<CommandHistoryResponse> getHistory() {
        List<CommandsHistory> history = historyRepository.findAll();
        List<CommandHistoryResponse> response = new ArrayList<>();
        history.forEach(h -> response.add(toResponse(h)));
        return response;
    }

    public List<CommandHistoryResponse> getHistoryByHostId(Long hostId) {
        List<CommandsHistory> history = historyRepository.findAllByHostId(hostId);
        List<CommandHistoryResponse> response = new ArrayList<>();
        history.forEach(h -> response.add(toResponse(h)));
        return response;
    }

    private CommandHistoryResponse toResponse(CommandsHistory history) {
        return new CommandHistoryResponse(
                history.getId(),
                history.getCommand(),
                history.getHost().getId(),
                history.getCreated_at()
        );
    }
}
