package kr.co.redbrush.webapp.controller.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@ControllerAdvice
@Slf4j
public class CustomErrorController {

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public BindingResult handleBindException(BindException exception) {
        return exception.getBindingResult();
    }

    // TODO : Implement or remove below code
    /*
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, ModelMap model) {
        StringBuilder view = new StringBuilder("error");

        getHttpStatus(request).ifPresent(httpStatus -> {
            if (httpStatus != null) {
                switch (httpStatus) {
                    case FORBIDDEN:
                    case NOT_FOUND:
                    case INTERNAL_SERVER_ERROR:
                        view.append("_").append(httpStatus.value());
                        break;
                }

                model.put("reason", httpStatus.getReasonPhrase());
            }
        });

        return view.toString();
    }

    private Optional<HttpStatus> getHttpStatus(HttpServletRequest request) {
        int statusCode = Optional.ofNullable((Integer)request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE))
                .orElse(-1);

        return Optional.ofNullable(HttpStatus.resolve(statusCode));
    }
    */
}
