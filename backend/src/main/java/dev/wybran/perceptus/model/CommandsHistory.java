package dev.wybran.perceptus.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    private String output;

    @NonNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Host host;

    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Date created_at;
}
