package am.gitc.trello.demo.exception;

public class FileLoadException extends RuntimeException{

    public static final long UUID = 5256725788897405L;

    public FileLoadException() {
    }

    public FileLoadException(String message) {
        super(message);
    }

    public FileLoadException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileLoadException(Throwable cause) {
        super(cause);
    }

    public FileLoadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
