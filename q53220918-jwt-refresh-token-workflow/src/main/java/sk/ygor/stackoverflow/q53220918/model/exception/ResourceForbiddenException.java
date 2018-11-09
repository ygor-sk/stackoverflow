package sk.ygor.stackoverflow.q53220918.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ResourceForbiddenException extends RuntimeException {

    public ResourceForbiddenException() {
        super(HttpStatus.FORBIDDEN.getReasonPhrase());
    }

}