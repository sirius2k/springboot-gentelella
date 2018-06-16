package kr.co.redbrush.webapp.controller.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Controller
@Slf4j
public class CustomErrorController implements ErrorController {
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

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
