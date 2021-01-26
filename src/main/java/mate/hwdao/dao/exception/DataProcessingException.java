package mate.hwdao.dao.exception;

public class DataProcessingException extends RuntimeException {

    public DataProcessingException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataProcessingException(String message) {
        System.out.println(message);
    }
}
