package core.game;

import java.util.Set;
import java.util.HashSet;

public class Player {
	final Level level;
	public final int index;
	
	private Player(Error nimportequoi){this.level=null;this.index=-1;} // CONSTRUCTEUR BIDON POUR QUE LE PROGRAMME COMPILE !
	
	/**
	 * Renvoie tous les pions de ce joueur.
	 * @return  l'ensemble de tous les pions appartenant à joueur
	 */
	public Set<Pawn> getPawns(){
		Set<Pawn> pawns = new HashSet<Pawn>();
		for (Pawn p:this.level.getPawns()) {
			if (this.equals(p.ghost.player))
				pawns.add(p);
		}
		return pawns;
	}
	
	Level getLevel() {
		return this.level;
	}
	
}