package JavaErrors;

public class MissingMotiveException extends RuntimeException{
    @Override
    public String getMessage() {
        return "Não foi fornecida uma justificativa para o cancelamento!";
    }
}
