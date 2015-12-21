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
	final Set<Pawn> pawns;
	final List<Player> players;
	
	public Level(){ //!\\ A MODIFIER
		this.board=null;
		this.pawns=null;
		this.players=null;
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
	public Set<Pawn> getPawns(Property<Pawn> p){ //renvoie tous les pions 
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
	
	public Pawn placeGhost(Ghost g,Case c) { //DEL
		// appelé à l'initialisation
		// crée un Pawn pour g et le rajoute à la liste des pions pour ce level
		// ATTENTION : vérifier que le level n'a pas déjà un pion comme lui
		// on peut utiliser le fait que Pawn.equals(Object) est redéfinie, et utiliser le booléen retourné par Set.add(E) (voir doc. pour plus d'info)
		// avant même de l'ajouter à la liste, placer le pion sur la case c ( méthode Pawn.place(c) ) : cela permet d'éliminer des erreurs décisives
		return null; // à terme: renvoie le Pawn créé
	}
	
	public void movePawn(Pawn p,Case c) { //DEL
		p.move(c);
	}
	
	public int getIndex(Player p) {
		return this.players.indexOf(p);
	}
	
}