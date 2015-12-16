package core.exceptions;

/** Cette exception est levée lorsqu'une action échoue.
 */
public class ActionException extends GameException {
	public ActionException() {
		super();
	}
	public ActionException(String message) {
		super(message);
	}
}