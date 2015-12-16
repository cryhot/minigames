package core.exceptions;

/** Cette exception est levée lorsqu'un pion essaie d'être capturé dans un mauvais contexte.
 */
public class PawnCaptureException extends ActionException {
	public PawnCaptureException() {
		super();
	}
	public PawnCaptureException(String message) {
		super(message);
	}
}