package core.game;

import java.util.Set;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;
import core.exceptions.*;
import core.board.Case;
import util.Property;

public class Player {
	final Level level;
	PlayerControler controler;
	private List<Pawn> initPawns;
	private Set<Case> initCases;
	
	Player(Game g) {
		this.level = g.getLevel();
		this.controler = null;
		this.initPawns = null;
		this.initCases = null;
	}
	
	void setControler(PlayerControler c) {
		this.controler = c;
		this.controler.setPlayer(this);
	}
	
	/** Méthode appelée par la mécanique du jeu, déclarant la configuration initiale pour le joueur.
	 * @param pawns  les pions à placer, dans l'ordre
	 * @param cases  les cases où placer les pions;
	 */
	void setInitialConfig(List<Pawn> pawns,Set<Case> cases) {
		this.initPawns = pawns;
		this.initCases = cases;
	}
	
	/** Méthode appelée par la mécanique du jeu, initialisant le placement des pions.
	 * Ne peut être appelée qu'une fois.
	 */
	void placePawns() {
		if (this.initPawns==null||this.initCases==null)
			throw new RuntimeException("Erreur inattendue :p");
		this.controler.placePawns();
		this.initPawns = null;
		this.initCases = null;
	}
	
	/** Méthode appelée par la mécanique du jeu, déroulant un tour de jeu.
	 */
	void playTurn() {
		this.controler.playTurn();
	}
	
	/** Méthode appelée par la mécanique du jeu, vérifiant une victoire active de ce joueur.
	 * Le joueur a gagné activement s'il possède un pion pouvant gagner activement et situé sur une case de sortie.
	 * @return  <code>true</code> si le joueur gagne activement.
	 */
	boolean checkActiveVictory() {
		final Player this_ = this;
		return new Property<Pawn>() {
			protected boolean validate(Pawn p) { return p.getOwner().equals(this_) && p.induceVictory(); }
		}.exists(this.getLevel().pawns);
	}
	
	/** Méthode appelée par la mécanique du jeu, vérifiant une victoire passive de ce joueur.
	 * Le joueur a gagné passivement si tous ses pions empêchant une victoire passive ont étés éliminés.
	 * @return  <code>true</code> si le joueur gagne passivement.
	 */
	boolean checkPassiveVictory() {
		final Player this_ = this;
		return !( new Property<Pawn>() {
			protected boolean validate(Pawn p) { return p.getOwner().equals(this_) && p.inhibitVictory(); }
		}.exists(this.getLevel().pawns) );
	}
	
	/** Méthode appelée par la mécanique du jeu, vérifiant une défaite de ce joueur.
	 * Le joueur a perdu s'il n'a plus de pions pouvant gagner activement en jeu.
	 * @return  <code>true</code> si le joueur perd.
	 */
	boolean checkDefeat() {
		final Player this_ = this;
		return !( new Property<Pawn>() {
			protected boolean validate(Pawn p) { return p.getOwner().equals(this_) && p.inhibitDefeat(); }
		}.exists(this.getLevel().pawns) );
	}
	
	/** Renvoie tous les pions de ce joueur.
	 * @return  l'ensemble de tous les pions appartenant à ce joueur
	 */
	public Set<Pawn> getPawns() {
		final Player this_ = this;
		return this.level.getPawns( new Property<Pawn>() {
			protected boolean validate(Pawn p) { return p.getOwner().equals(this_); }
		} );
	}
	
	Level getLevel() {
		return this.level;
	}
	
	public int getIndex() {
		return this.level.getIndex(this);
	}
	
	public void placePawn(Pawn p,Case c) {
		if(!p.ghost.player.equals(this)){
			throw new OwnerException();
		}
		p.place(c);
	}		
	
	
	public void movePawn(Pawn p,Case c) {
		if(!p.ghost.player.equals(this)){
			throw new OwnerException();
		}
		p.move(c);
	}
	
	List<Pawn> getInitialPawns() {
		return new ArrayList<Pawn>(this.initPawns);
	}
	
	Set<Case> getInitialCases() {
		return new TreeSet<Case>(this.initCases);
	}
	
}