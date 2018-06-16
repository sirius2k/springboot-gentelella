package kr.co.redbrush.webapp.dto;

import lombok.Data;

@Data
public class SuccessfulResult extends RequestResult {
    public SuccessfulResult() {
        super(true, null);
    }

    public SuccessfulResult(String message) {
        super(true, message);
    }
}
