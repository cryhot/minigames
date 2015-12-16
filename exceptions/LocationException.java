package exceptions;

/** Cette exception est levée lorsqu'une action se déroule sur une case interdite.
 * @see board.Case
 */
public class LocationException extends ActionException {
	public LocationException() {
		super();
	}
	public LocationException(String message) {
		super(message);
	}
}