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
	
	/*
	public Set<Pawn> getPawns(Property<Pawns> p){ //renvoie tous les pions vérifiant une certaine propriété
		...
	}
	//*/
	
	public Set<Pawn> getPawns(){ //renvoie tous les pions
		return pawns;
	}
	
	public Pawn getPawnAt(Case c) {
		for(Pawn p:this.pawns){
			if(p.getCase().equals(c))
				return p;
		}
		return null;
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