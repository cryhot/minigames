package core.game;

import core.board.Case;
import core.board.Board;

import core.exceptions.*;

public final class Pawn {
	public final Ghost ghost;
	Case location;
	private boolean captured;
	
	Pawn(Ghost g) {
		if (g==null)
			throw new NullPointerException();
		this.ghost = g;
		this.location = null;
		this.captured = false;
	}
	
	private boolean validatePlacement(Case c) { // valide une position
		return c!=null && c.isInside();
	}
	
	private boolean validateMove(Case c) { // valide un déplacement
		return this.ghost.soul.mobility.getMoveTable( this.getLevel().board.paradigm ).validate( this.location,c );
	}
	
	private boolean validateTarget(Case c) { // valide la capture d'un pion
		return this.validateTarget(this.getLevel().getPawnAt(c));
	}
	
	private boolean validateTarget(Pawn p) { // valide la capture d'un pion
		if (p==null)
			return true; // aucun pion à capturer
		if(!this.ghost.soul.canEat)
			return false; // ne peut pas capturer d'autre pion
		if (this.getOwner().equals(p.getOwner()))
			return false; // ne peut pas capturer un allié
		return true;
	}
	
	/** Procède au traitement des erreurs dûes à un mauvais positionnement.
	 * @param c  la case où positionner le pion
	 * @throws PawnStateException  si le pion est déjà positionné ou éliminé
	 * @throws OutOfBoardException  si la case est interdite
	 * @throws ObstructedLocationException  si la case est occupée
	 * @see #place(Case)
	 */
	private void attemptPlacement(Case c) throws ActionException {
		if (this.captured)
			throw new PawnStateException("pion éliminé");
		if (this.location!=null)
			throw new PawnStateException("pion déjà en jeu");
		if (!this.validatePlacement(c))
			throw new OutOfBoardException();
		if (this.getLevel().getPawnAt(c)!=null)
			throw new ObstructedLocationException("case déjà occupée");
	}
	
	/** Effectue le positionnement de ce pion sur la case indiquée.
	 * Cette méthode est notemment utilisée pour placer les pions initialement.
	 * @param c  la case où positionner le pion
	 * @throws PawnStateException  si le pion est déjà positionné ou éliminé
	 * @throws OutOfBoardException  si la case est interdite
	 * @throws ObstructedLocationException  si la case est occupée
	 * @see #attemptPlacement(Case)
	 */
	void place(Case c) throws ActionException {
		this.attemptPlacement(c);
		this.location = c;
	}
	
	/** Vérifie un positionnement de ce pion sur une case indiquée.
	 * @param c  la case où positionner le pion
	 * @return  <code>true</code> si la position est valide
	 * @see #attemptPlacement(Case)
	 */
	boolean canPlace(Case c) { 
		try {
			this.attemptPlacement(c);
		} catch (ActionException e) {
			return false;
		}
		return true;
	}
	
	/** Procède au traitement des erreurs dûes à un mauvais déplacement.
	 * @param c  la case de destination du mouvement
	 * @throws PawnStateException  si le pion est éliminé ou pas encore placé
	 * @throws OutOfBoardException  si la case est interdite
	 * @throws MoveException  si le déplacement est interdit
	 * @throws PawnCaptureException  si la capture de pion est interdite et qu'un pion va être capturé
	 * @see #move(Case)
	 */
	private void attemptMove(Case c) throws ActionException {
		if (this.captured)
			throw new PawnStateException("pion éliminé");
		if (this.location==null)
			throw new PawnStateException("pion non placé");
		if (!this.validatePlacement(c))
			throw new OutOfBoardException();
		if (!this.validateMove(c))
			throw new MoveException();
		if (!this.validateTarget(c))
			throw new PawnCaptureException();
	}
	
	/** Effectue le déplacement de ce pion vers la case indiquée.
	 * Si un autre pion est déjà présent sur cette case, celui-ci est capturé.
	 * @param c  la case de destination du mouvement
	 * @throws PawnStateException  si le pion est éliminé ou pas encore placé
	 * @throws OutOfBoardException  si la case est interdite
	 * @throws MoveException  si le déplacement est interdit
	 * @throws PawnCaptureException  si la capture de pion est interdite et qu'un pion va être capturé
	 * @see #attemptMove(Case)
	 */
	void move(Case c) throws ActionException {
		this.attemptMove(c);
		capture(this.getLevel().getPawnAt(c));
		this.location = c;
	}
	
	/** Vérifie un déplacement de ce pion vers une case indiquée.
	 * @param c  la case de destination du mouvement
	 * @return  <code>true</code> si le déplacement est valide
	 * @see #attemptMove(Case)
	 */
	boolean canMove(Case c) { 
		try {
			this.attemptMove(c);
		} catch (ActionException e) {
			return false;
		}
		return true;
	}
	
	/** Elimine ce pion du plateau de jeu.
	 */
	void capture() { 
		this.location = null;
		this.captured = true;
	}
	
	static void capture(Pawn p) {
		if (p!=null)
			p.capture();
	}
	
	boolean induceVictory() {
		return this.ghost.soul.activeVictory && this.getCase().isEscape(this.getOwner().getIndex());
	}
	
	boolean inhibitVictory() {
		return this.ghost.soul.passiveVictory && !this.captured;
	}
	
	boolean inhibitDefeat() {
		return this.ghost.soul.activeVictory && !this.captured;
	}
	
	public boolean isCaptured() {
		return this.captured;
	}
	
	public boolean isPlaced() {
		return !this.captured && this.location!=null;
	}
	
	public boolean isNotYetPlaced() {
		return !this.captured && this.location==null;
	}
	
	public Case getCase() {
		return this.location;
	}
	
	public Player getOwner() {
		return this.ghost.getOwner();
	}
	
	Soul getSoul() {
		return this.ghost.soul;
	}
	
	Level getLevel(){
		return this.ghost.getLevel();
	}
	
	public Board getBoard() {
		return this.getLevel().board;
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

