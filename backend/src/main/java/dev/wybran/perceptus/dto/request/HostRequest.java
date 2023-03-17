package dev.wybran.perceptus.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HostRequest {
    private String name;
    private String ip;
    private String username;
    private String password;
    private Integer port;
}
