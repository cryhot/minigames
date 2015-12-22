package core.exceptions;

/** Cette exception est levée lorsqu'un état de match nul est atteint.
 */
public class DrawException extends Exception {
	public DrawException() {
		super();
	}
	public DrawException(String message) {
		super(message);
	}
}