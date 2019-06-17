package exceptions;

import java.sql.SQLException;

@SuppressWarnings("serial")
public class DBException extends SQLException {
	
    public DBException(String message, Throwable cause) {
    	super(message, cause);
    }
}
