package core.exceptions;

import core.game.Game; // javadoc only

/** Cette exception est levée lorsqu'une action est effectuée à un mauvais moment du jeu.
 * @see Game
 */
public class GameStateException extends ActionException {
	public GameStateException() {
		super();
	}
	public GameStateException(String message) {
		super(message);
	}
}