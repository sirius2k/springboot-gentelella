package kr.co.redbrush.webapp.controller.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class CustomErrorController implements ErrorController {
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, ModelMap model) {
        HttpStatus httpStatus = getHttpStatus(request);
        String view = "page_error";

        LOGGER.debug("handleError. httpStatus : {}", httpStatus);

        if (httpStatus != null) {
            switch (httpStatus) {
                case FORBIDDEN:
                case NOT_FOUND:
                case INTERNAL_SERVER_ERROR:
                    view = "page_" + httpStatus.value();
                    break;
            }

            model.put("reason", httpStatus.getReasonPhrase());
        }

        return view;
    }

    private HttpStatus getHttpStatus(HttpServletRequest request) {
        Integer statusCode = (Integer)request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        return HttpStatus.resolve(statusCode);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
