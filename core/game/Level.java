package core.game;

import java.util.Set;
import java.util.HashSet;

import core.board.Case;
import core.board.Board;

public class Level {
	public final Board board;
	private Set<Pawn> pawns;
	
	public Level(){ //!\\ A MODIFIER
		this.board=null;
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
	public Set<Pawn> getPawns(Property<Pawns> p){ //renvoie tous les pions 
		return p.select(this.pawn);
	}
	
	/** Renvoie tous les pions du jeu.
	 * @return  l'ensemble des pions du jeu
	 */
	public Pawn getPawnAt(Case c) {
		return new Property<Pawn>(c) {
			private Case c;
			public Property(Case c) { this.c = c; }
			protected boolean validate(Pawn p) { return p.getCase().equals(this.c); }
		}.find(this.pawn);
	}
	
	public Pawn placeGhost(Ghost g,Case c) {
		// appelé à l'initialisation
		// crée un Pawn pour g et le rajoute à la liste des pions pour ce level
		// ATTENTION : vérifier que le level n'a pas déjà un pion comme lui
		// on peut utiliser le fait que Pawn.equals(Object) est redéfinie, et utiliser le booléen retourné par Set.add(E) (voir doc. pour plus d'info)
		// avant même de l'ajouter à la liste, placer le pion sur la case c ( méthode Pawn.place(c) ) : cela permet d'éliminer des erreurs décisives
		return null; // à terme: renvoie le Pawn créé
	}
	
	public void movePawn(Pawn p,Case c) {
		p.move(c);
	}
	
}