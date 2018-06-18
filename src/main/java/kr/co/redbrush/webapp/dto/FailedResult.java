package kr.co.redbrush.webapp.dto;

import lombok.Data;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Map;
import java.util.stream.Collectors;

@Data
public class FailedResult extends RequestResult {
    private Map<String, String> errors;

    public FailedResult() {
        super(false, null);
    }

    public FailedResult(BindingResult bindingResult) {
        super(false, null);

        errorsToMap(bindingResult);
    }

    public FailedResult(String message, BindingResult bindingResult) {
        super(false, message);

        errorsToMap(bindingResult);
    }

    public FailedResult(String message) {
        super(false, message);
    }

    private void errorsToMap(BindingResult bindingResult) {
        errors = bindingResult.getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
    }
}
