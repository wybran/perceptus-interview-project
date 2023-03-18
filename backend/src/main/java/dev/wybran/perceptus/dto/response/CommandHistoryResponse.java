package dev.wybran.perceptus.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter @Setter
@AllArgsConstructor
public class CommandHistoryResponse {
    private Long id;
    private String command;
    private String hostIP;
    private Date created_at;
}
