package core.exceptions;

/** Cette exception est levée lors d'une référence à un pion invalide.
 */
public class PawnNotFoundException extends GameException {
	public PawnNotFoundException() {
		super();
	}
	public PawnNotFoundException(String message) {
		super(message);
	}
}