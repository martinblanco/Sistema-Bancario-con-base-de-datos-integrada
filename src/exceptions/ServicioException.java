package exceptions;

@SuppressWarnings("serial")
public class ServicioException extends DAOException {
    
	public ServicioException(String message, Throwable cause) {
        super(message, cause);
    }
}
