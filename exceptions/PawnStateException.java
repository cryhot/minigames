package exceptions;

/** Cette exception est levée lorsqu'une action est effectuée sur un pion qui n'est pas dans le bon état.
 */
public class PawnStateException extends ActionException {
	public PawnStateException() {
		super();
	}
	public PawnStateException(String message) {
		super(message);
	}
}