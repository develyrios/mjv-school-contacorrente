package JavaErrors;

public class InvalidDateException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Data inserida é invalida!";
    }
}
