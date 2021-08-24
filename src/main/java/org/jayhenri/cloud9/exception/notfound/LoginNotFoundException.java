package org.jayhenri.cloud9.exception.notfound;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Item not found exception.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class LoginNotFoundException extends Exception {

    private List<String> errorMessages = new ArrayList<>();

    /**
     * Instantiates a new Item not found exception.
     */
    public LoginNotFoundException() {
    }

    /**
     * Instantiates a new Item not found exception.
     *
     * @param msg the msg
     */
    public LoginNotFoundException(String msg) {
        super(msg);
    }

    /**
     * Gets error messages.
     *
     * @return the error messages
     */
    public List<String> getErrorMessages() {
        return errorMessages;
    }

    /**
     * Sets error messages.
     *
     * @param errorMessages the error messages
     */
    public void setErrorMessages(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    /**
     * Add error message.
     *
     * @param msg the msg
     */
    public void addErrorMessage(String msg) {
        this.errorMessages.add(msg);
    }
}