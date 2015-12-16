package exceptions;

/** Cette exception est levée lorsqu'une action fait référence à une case hors plateau.
 * @see board.Case#isInside()
 */
public class OutOfBoardException extends ObstructedLocationException {
	public OutOfBoardException() {
		super();
	}
	public OutOfBoardException(String message) {
		super(message);
	}
}