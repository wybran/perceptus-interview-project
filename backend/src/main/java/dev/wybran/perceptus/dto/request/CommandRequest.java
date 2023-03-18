package dev.wybran.perceptus.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommandRequest {
    private String ip;
    private Integer port;
    private String username;
    private String password;
    private String command;
}
