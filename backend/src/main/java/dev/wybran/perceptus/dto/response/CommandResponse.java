package dev.wybran.perceptus.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class CommandResponse {
    private String command;
    private String output;
    private Boolean isError;
}
