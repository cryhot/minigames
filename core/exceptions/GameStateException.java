package core.exceptions;

/** Cette exception est levée lorsqu'une action est effectuée à un mauvais moment du jeu.
 */
public class GameStateException extends ActionException {
	public GameStateException() {
		super();
	}
	public GameStateException(String message) {
		super(message);
	}
}