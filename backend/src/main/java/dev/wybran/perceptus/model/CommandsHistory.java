package dev.wybran.perceptus.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

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
    private String hostIP;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date created_at;
}
