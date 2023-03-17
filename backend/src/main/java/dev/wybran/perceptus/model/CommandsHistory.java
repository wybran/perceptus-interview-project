package dev.wybran.perceptus.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "commands_history")
@Getter @Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class CommandsHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String command;

    @NonNull
    private String output;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Host host;
}
