package core.exceptions;

import core.movement.Move; // javadoc only

/** Cette exception est levée lorsqu'un déplacement interdit vers une case est effectué.
 * @see Move
 */
public class MoveException extends LocationException {
	public MoveException() {
		super();
	}
	public MoveException(String message) {
		super(message);
	}
}