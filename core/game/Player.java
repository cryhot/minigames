package core.game;

import java.util.Set;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;
import core.exceptions.*;
import core.board.Case;
import util.Property;

/** Un <code>Player</code> est l'instance représentant un joueur au cours d'une {@link Game partie} de Ghost.
 * <br>
 * <code>Player</code> possède entre autre des méthodes permettant d'exécuter des opérations sur le jeu et les pion du joueur.
 * @see Game
 * @see Level
 * @see Pawn
 */
public class Player {
	final Level level;
	PlayerControler controler;
	private List<Pawn> initPawns;
	private Set<Case> initCases;
	
	/** Construit un  nouveau joueur.
	 * @param l  le niveau de jeu auquel appartient le joueur
	 */
	Player(Level l) {
		this.level = l;
		this.controler = null;
		this.initPawns = null;
		this.initCases = null;
	}
	
	/** Construit un  nouveau joueur.
	 * @param g  le de jeu auquel appartient le joueur
	 */
	Player(Game g) {
		this(g.getLevel());
	}
	
	/** Lie ce joueur à un controleur.
	 * Cette action ne devrait pas être exécutée n'importe quand, pour l'intégrité du programme.
	 * @param c  le controleur d'action à lier au joueur.
	 */
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
	 * @see Game#initialize()
	 */
	void placePawns() {
		if (this.initPawns==null||this.initCases==null)
			throw new RuntimeException("Erreur inattendue :p");
		this.controler.placePawns();
		this.initPawns = null;
		this.initCases = null;
	}
	
	/** Méthode appelée par la mécanique du jeu, déroulant un tour de jeu.
	 * @see Game#playTurn(Player)
	 */
	void playTurn() {
		this.controler.playTurn();
	}
	
	/** Méthode appelée par la mécanique du jeu, vérifiant une victoire active de ce joueur.
	 * Le joueur a gagné activement s'il possède un pion pouvant gagner activement et situé sur une case de sortie.
	 * @return  <code>true</code> si le joueur gagne activement.
	 * @see Pawn#induceVictory()
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
	 * @see Pawn#inhibitVictory()
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
	 * @see Pawn#inhibitDefeat()
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
	
	/** Effectue le positionnement d'un pion sur la case indiquée.
	 * Cette méthode est notemment utilisée pour placer les pions initialement.
	 * @param p  le pion à positionner
	 * @param c  la case où positionner le pion
	 * @throws OwnerException  si le pion n'appartient pas à ce joueur
	 * @throws PawnStateException  si le pion est déjà positionné ou éliminé
	 * @throws OutOfBoardException  si la case est interdite
	 * @throws ObstructedLocationException  si la case est occupée
	 * @see Pawn#place(Case)
	 * @see PlayerControler#placePawns()
	 */
	void placePawn(Pawn p,Case c) {
		if(!p.ghost.player.equals(this))
			throw new OwnerException();
		p.place(c);
	}		
	
	/** Effectue le déplacement d'un pion vers la case indiquée.
	 * Si un autre pion est déjà présent sur cette case, celui-ci est capturé.
	 * @param p  le pion à déplacer
	 * @param c  la case de destination du mouvement
	 * @throws OwnerException  si le pion n'appartient pas à ce joueur
	 * @throws PawnStateException  si le pion est éliminé ou pas encore placé
	 * @throws OutOfBoardException  si la case est interdite
	 * @throws MoveException  si le déplacement est interdit
	 * @throws PawnCaptureException  si la capture de pion est interdite et qu'un pion va être capturé
	 * @see Pawn#move(Case)
	 * @see PlayerControler#playTurn()
	 */
	void movePawn(Pawn p,Case c) {
		if(!p.ghost.player.equals(this))
			throw new OwnerException();
		p.move(c);
	}
	
	/** Renvoie le niveau auquel apparient ce joueur.
	 * @return  le niveau de jeu auquel appartient ce joueur
	 */
	Level getLevel() {
		return this.level;
	}
	
	/** Renvoie l'ordre de passage de ce joueur.
	 * @return  l'ordre de passage, qui est numéroté à partir de 0
	 */
	public int getIndex() {
		return this.level.getIndex(this);
	}
	
	/** Renvoie la liste ordonnée des pions à placer au début du jeu.
	 * @return  la liste des pions à placer, ou <code>null</code> si le placement a déjà été exécuté.
	 * @see PlayerControler#getInitialPawns()
	 */
	List<Pawn> getInitialPawns() {
		if (this.initPawns==null)
			return null;
		return new ArrayList<Pawn>(this.initPawns);
	}
	
	/** Renvoie l'ensemble des cases de départ où placer les pions au début du jeu.
	 * @return  l'ensemble des cases de départ, ou <code>null</code> si le placement a déjà été exécuté.
	 * @see PlayerControler#getInitialCases()
	 */
	Set<Case> getInitialCases() {
		if (this.initCases==null)
			return null;
		return new TreeSet<Case>(this.initCases);
	}
	
}