package core.game;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

import core.board.Case;
import core.board.Board;
import util.Property;

class Level {
	final Board board;
	final List<Player> players;
	final Set<Pawn> pawns;
	
	Level(Board b) {
		if (b==null)
			throw new NullPointerException();
		this.board = b;
		this.players = new ArrayList<Player>(2);
		this.pawns = new HashSet<Pawn>();
	}
	
	/** Renvoie tous les pions du jeu.
	 * @return  l'ensemble des pions du jeu
	 */
	public Set<Pawn> getPawns(){
		return new HashSet<Pawn>(this.pawns);
	}
	
	/** Renvoie tous les pions du jeu vérifiant une certaine propriété.
	 * @param p  la propriété utilisée
	 * @return  l'ensemble des pions du jeu vérifiant la propriété
	 */
	public Set<Pawn> getPawns(Property<Pawn> p){
		return p.select(this.pawns);
	}
	
	/** Renvoie le pion situé sur une case.
	 * @param c  la case de recherche
	 * @return  le pion s'il existe, ou <code>null</code> sinon
	 */
	public Pawn getPawnAt(final Case c) {
		return new Property<Pawn>() {
			protected boolean validate(Pawn p) { return p.getCase().equals(c); }
		}.find(this.pawns);
	}
	
		
	public int getIndex(Player p) {
		return this.players.indexOf(p);
	}
	
}