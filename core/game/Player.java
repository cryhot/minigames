package core.game;

import java.util.Set;
import java.util.HashSet;

public class Player {
	final Level level;
	public final int index;
	
	private Player(Error nimportequoi){this.level=null;this.index=-1;} // CONSTRUCTEUR BIDON POUR QUE LE PROGRAMME COMPILE !
	
	/**
	 * Renvoie tous les pions de ce joueur.
	 * @return  l'ensemble de tous les pions appartenant à ce joueur
	 */
	public Set<Pawn> getPawns() {
		final Player player = this;
		return this.level.getPawns( new Property<Pawn>() {
			protected boolean validate(Pawn p) { return p.getOwner().equals(player); }
		} );
	}
	
	Level getLevel() {
		return this.level;
	}
	
}