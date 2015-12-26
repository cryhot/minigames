package core.exceptions;

import core.game.Player; // javadoc only

/** Cette exception est levée lorsqu'un joueur tente une action sur un pion ne lui appartenant pas.
 * @see Player
 */
public class OwnerException extends ActionException {
	public OwnerException() {
		super();
	}
	public OwnerException(String message) {
		super(message);
	}
}