package ru.academits.phonebook;

import org.junit.Test;
import ru.academits.model.ErrorInfo;

import static org.junit.Assert.*;

public class ErrorControllerTest {
    private ErrorController errorController = new ErrorController();

    @Test
    public void processExceptionTest() {
        Exception exception = new RuntimeException("Something went wrong ...");
        ErrorInfo errorInfo = errorController.processException(exception);
        assertEquals(errorInfo.getMessage(), "Something went wrong ...");
    }
}