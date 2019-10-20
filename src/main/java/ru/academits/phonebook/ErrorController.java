package ru.academits.phonebook;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.academits.model.ErrorInfo;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@ControllerAdvice
public class ErrorController {
    private static final Logger logger = LogManager.getLogger(ErrorController.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ErrorInfo processException(Exception e) {
        logger.error("processException: " + e.getMessage());
        return new ErrorInfo(e.getMessage());
    }
}
