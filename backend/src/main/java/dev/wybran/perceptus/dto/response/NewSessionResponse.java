package dev.wybran.perceptus.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class NewSessionResponse {
    private String uuid;
    private String hostIP;
    private Integer port;
    private String username;
}
