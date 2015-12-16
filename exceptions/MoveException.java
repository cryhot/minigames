package exceptions;

/** Cette exception est levée lorsqu'un déplacement interdit vers une case est effectué.
 * @see movement.Move
 */
public class MoveException extends LocationException {
	public MoveException() {
		super();
	}
	public MoveException(String message) {
		super(message);
	}
}