package core.exceptions;

/** Cette exception est levée lorsqu'une erreur apparait dans le jeu.
 */
public class GameException extends RuntimeException {
	public GameException() {
		super();
	}
	public GameException(String message) {
		super(message);
	}
}