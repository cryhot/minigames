//Louis
package game;

import java.util.*;
import board.Case;

public class Level {
	
	private Set<Pawn> pawns;
	
	public Pawn getPawnAt(Case c){
		
		for(Pawn p :this.pawns){
			if(p.getCase().equals(c)){
				return p;
			}
		}
		return null;
		
	}
	
}