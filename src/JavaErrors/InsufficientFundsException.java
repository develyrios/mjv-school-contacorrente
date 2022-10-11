package JavaErrors;

public class InsufficientFundsException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Saldo insuficiente para essa transação!";
    }
}
