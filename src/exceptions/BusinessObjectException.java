package exceptions;

@SuppressWarnings("serial")
public class BusinessObjectException extends ServicioException {
	 
	public BusinessObjectException(String message, Throwable cause) {
	    	super(message, cause);
	    }
}
