package kr.co.redbrush.webapp.dto;

import lombok.Data;

@Data
public class FailedResult extends RequestResult {
    public FailedResult() {
        super(false, null);
    }

    public FailedResult(String message) {
        super(false, message);
    }
}
