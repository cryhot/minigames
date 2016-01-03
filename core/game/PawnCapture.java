package core.game;

import core.board.Case;

/** Cette classe est une version de représentation éditable d'un {@link Pawn pion}.
 * Les fonctionnalités d'éditions inplémentées ici sont à des fins de vues personnalisées du plateau.
 * @see GameCapture
 * @see GlobalViewer
 */
public class PawnCapture extends Pawn {
	
	/** Crée une version éditable de représentation d'un pion.
	 * @param p  le pion d'origine de la capture
	 */
	public PawnCapture(Pawn p) {
		super(p);
	}
	
	/** Replace le pion d'une manière arbitraire.
	 * Ce changement de place n'affecte pas le vrai {@link Ghost pion}.
	 * @param c  la case où situer le pion, ou <code>null</code> si le pion est à sortir du plateau
	 */
	public void setCase(Case c) {
		this.location = c;
	}
	
}