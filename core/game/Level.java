package core.game;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

import core.board.Case;
import core.board.Board;
import util.Property;

/** <code>Level</code> est un niveau de jeu, qui se définit simplement en la liste des éléments d'une partie.
 * Cette liste est composée de :
 * <ul>
 * 	<li>le {@link Board plateau de jeu} du niveau</li>
 * 	<li>les {@link Player joueurs} participant à la partie</li>
 * 	<li>l'ensemble des {@link Pawn pions} du jeu</li>
 * </ul>
 * @see Game
 */
class Level {
	final Board board;
	final List<Player> players;
	final Set<Pawn> pawns;
	
	/** Construit un nouveau niveau vierge.
	 * Les joueurs, les pions... etc doivent être créés de manière correcte par le {@link Game jeu} lors de sa construction.
	 * @param b  le plateau de jeu utilisé dans ce niveau
	 */
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
			protected boolean validate(Pawn p) { return c.equals(p.getCase()); }
		}.find(this.pawns);
	}
	
	/** Renvoie l'ordre de passage d'un joueur.
	 * @param p  le joueur dont est demmandé l'ordre de passage
	 * @return  l'ordre de passage, qui est numéroté à partir de 0
	 */
	public int getIndex(Player p) {
		return this.players.indexOf(p);
	}
	
}