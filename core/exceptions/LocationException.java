package core.exceptions;

import core.board.Case; // javadoc only

/** Cette exception est levée lorsqu'une action se déroule sur une case interdite.
 * @see Case
 */
public class LocationException extends ActionException {
	public LocationException() {
		super();
	}
	public LocationException(String message) {
		super(message);
	}
}