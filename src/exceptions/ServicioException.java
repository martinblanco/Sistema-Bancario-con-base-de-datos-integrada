package exceptions;

@SuppressWarnings("serial")
public class ServicioException extends Exception {
    
	public ServicioException(String message, Throwable cause) {
        super(message, cause);
    }
}
