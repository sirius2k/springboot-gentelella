package kr.co.redbrush.webapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RequestResult {
    boolean success;
    String message;
}
