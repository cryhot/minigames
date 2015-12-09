//Louis
package game;

import java.util.*;
import board.Case;

public class Level {
	
	private Set<Pawn> pawns;
	protected Set<Pawn> capturPawns; // INUTILE ( tous les pawn qui on pour case : null )
	// MANQUE D'AUTRES ATTRIBUTS ( voir schéma )
	
	public Pawn getPawnAt(Case c) {
		for(Pawn p:this.pawns){
			if(p.getCase().equals(c))
				return p;
		}
		return null;
	}
	
}