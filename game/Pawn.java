//Louis
package game;


import board.Case;

public final class Pawn {
	
	public final Ghost ghost;
	Case location;
	private Level level;
	
	private Pawn() { //!\\ A MODIFIER ! CONSTRUCTEUR POUR LES TESTS
		this.ghost = null;
	}
	
	public Case getCase(){
		return this.location;
	}
	
	public Level getLevel(){
		return this.level;
	}
	
	private boolean validateMove(Case c){
		return this.ghost.soul.mobility.getMoveTable( this.level.board.paradigm ).validate( this.location,c );
	}
	
	boolean canMove(Case c) { 
		if(!c.isInside()) // destination hors plateau
			return false;
		
		if(level.getPawnAt(c)!=null && this.ghost.player.equals(level.getPawnAt(c).ghost.player)) // le pion essaie de capturer un allié
			return false;
		
		if(level.getPawnAt(c)!=null && !this.ghost.soul.canEat) // le pion ne peut pas capturer d'autre pion
			return false;
		
		if(!validateMove(c)) // mouvement invalide
			return false;
		
		
		return true;
	}
	
	void capture(){ 
		this.location = null;
	}
	
	void move(Case c) {
		
		if(!canMove(c))
			throw(new RuntimeException ("La case n'est pas accessible par ce pion !"));
		
		Pawn prev = level.getPawnAt(c);
		if(prev!=null)
			prev.capture();
		
		this.location = c;
		}
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

