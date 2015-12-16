package core.exceptions;

/** Cette exception est levée lorsqu'une action fait référence à une case obstruée.
 */
public class ObstructedLocationException extends LocationException {
	public ObstructedLocationException() {
		super();
	}
	public ObstructedLocationException(String message) {
		super(message);
	}
}