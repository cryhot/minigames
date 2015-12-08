//Louis
package game;

import board.Case;

public final class Pawn {
	
	public final Ghost ghost;
	Case location;
	private Level level;
	
	public Pawn() { //!\\ 
		this.ghost = null;
	}
	
	public Case getCase(){
		return this.location;
	}
	
	public Level getLevel(){
		return this.level;
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
	
	boolean canMove(Case c){
		
		if(!c.isInside()){return false;}
		if(!c.isEmpty() && this.ghost.player.equals(level.getPawnAt(c).ghost.player)){return false;}
		if(!c.isEmpty() && !this.ghost.soul.canEat){return false;}
		if(!this.ghost.soul.mobility.getMoveTable(this.level.board.paradigm ).validate( this.location,c)){return false;}
		if(c.isEscape && !this.ghost.soul.activVictory){return false;}
		return true;
	}
	
	public void captur(Case c){
		
		level.getPawnAt(c).location = null;
		level.capturPawns.add(level.getPawnAt(c));
		
	}
		
	
	void move(Pawn p, Case c){
	
		if(!canMove){
			throw(new Exception ("La case n'est pas accessible par ce pion !"));
		}
		else{
				if(this.ghost.player.equals(level.getPawnAt(c).ghost.player)){
					captur(c);
				}
				this.location=c;
		}
	}	
			
}

