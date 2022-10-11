package JavaErrors;

public class ValueNotHandledException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Valor não Tratado!";
    }
}
