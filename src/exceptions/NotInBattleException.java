package exceptions;

public class NotInBattleException extends RuntimeException {
    public NotInBattleException(String message) {
        super(message);
    }
}
