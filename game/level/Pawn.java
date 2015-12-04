//Louis
package game.level;

import game.level.board.Case;

public final class Pawn {
	private Case c;
	public final Ghost ghost;// le pion possède un attribut ghost
	
	public Pawn() {
		this.ghost = null; //!\\
	}
	
	public Case getCase(){
		return this.c;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!this.getClass().equals(o.getClass()))
			return false;
		return this.ghost.equals(((Pawn)o).ghost);
	}
	
	@Override
	public int hashCode() {
		return this.ghost.hashCode();
	}
	
}