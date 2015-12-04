//Louis

package game.level;

public class Pawn {
	private static Move[] moves = {new Move(0,1), new Move(1,0), new Move(-1,0) , new Move(0,-1)};
	private Case c;
	
	public Case getCase(){
		return this.c;
	}
	
	
	
	
}