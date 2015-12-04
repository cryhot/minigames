//Louis

package game.level;

import game.level.board.Case;

public class Pawn {
	
	
	private Case c;
	public final Ghost ghost;// le pion possède un attribut ghost
	
	
	public Case getCase(){
		return this.c;
	}
	
	@Override
	public boolean equals(Pawn p1){
		if(this.ghost.equals(p1.ghost)){
			return true:
		}
		return false;
	}
	
	
	
}