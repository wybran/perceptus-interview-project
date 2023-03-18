package dev.wybran.perceptus.dto.request;


import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommandRequest {
    private String uuid;
    private String command;
}
