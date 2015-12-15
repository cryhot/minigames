//Louis
package game;

import java.util.*;
import board.Case;
import board.Board;

public class Level {
	
	public final Board board;
	private Set<Pawn> pawns;
	
	
	public Level(){ //!\\ A MODIFIER
		this.board=null;
	}
	
	public Pawn getPawnAt(Case c) {
		for(Pawn p:this.pawns){
			if(p.getCase().equals(c))
				return p;
		}
		return null;
	}
	
}