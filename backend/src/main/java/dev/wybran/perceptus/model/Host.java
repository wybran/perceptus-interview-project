package dev.wybran.perceptus.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "hosts")
@Getter @Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Host {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String ip;

    @NonNull
    private String username;

    @NonNull
    private String password;

    @NonNull
    private Integer port;
}
