package forum.hub.api.infra.exception;

public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }

}
