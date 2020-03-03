package exceptions;

public class InvalidTargetException extends HearthstoneException {

	public InvalidTargetException() {
		super("Icehowl cannot attack hero.");
	}

	public InvalidTargetException(String s) {
		super(s);
	}

}
