package dev.wybran.perceptus.repository;

import dev.wybran.perceptus.model.CommandsHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandsHistoryRepository extends JpaRepository<CommandsHistory, Integer> {
    List<CommandsHistory> findAllByHostIP(String hostIP);
    List<CommandsHistory> findByOrderByIdDesc();
}
