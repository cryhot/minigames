package core.game;

import java.util.Set;
import java.util.HashSet;

import core.board.Case;
import util.Property;

/** Cette classe établit une capture instantanée d'un {@link Level niveau}.
 * Les fonctionnalités inplémentées ici sont à des fins de vues personnalisées du plateau.
 * @see PawnCapture
 * @see GlobalViewer
 */
public class GameCapture extends Level {
	public final Set<PawnCapture> pawnsCapt;
	
	/** Crée une capture d'un niveau à un instant donné.
	 * @param l  le niveau à l'origine de la capture
	 */
	public GameCapture(Level l) {
		super(l.board);
		this.players.addAll(l.players);
		this.pawnsCapt = new HashSet<PawnCapture>(l.pawns.size());
		for (Pawn p:l.pawns)
			this.pawnsCapt.add(new PawnCapture(p));
		this.pawns.addAll(this.pawnsCapt);
	}
	
	/** Replace un pion d'une manière arbitraire.
	 * @param p  le pion à replacer
	 * @param c  la case où situer le pion
	 * @see PawnCapture#setCase(Case)
	 */
	public void relocatePawn(Pawn p,Case c) {
		for (PawnCapture pc:this.pawnsCapt)
			if (pc.equals(p)) {
				pc.setCase(c);
				return;
			}
		throw new UnsupportedOperationException("pion non trouve");
	}
	
	/** Renvoie toutes les captures de pions du jeu.
	 * @return  l'ensemble des pions du jeu
	 * @see Level#getPawns()
	 */
	public Set<PawnCapture> getPawnCaptures() {
		return new HashSet<PawnCapture>(this.pawnsCapt);
	}
	
	/** Renvoie toutes les captures de pions du jeu vérifiant une certaine propriété.
	 * @param p  la propriété utilisée
	 * @return  l'ensemble des pions du jeu vérifiant la propriété
	 * @see Level#getPawns(Property)
	 */
	public Set<PawnCapture> getPawnCaptures(Property<Pawn> p){
		return p.select(this.pawnsCapt);
	}
	
	/** Renvoie le pion situé sur une case.
	 * @param c  la case de recherche
	 * @return  le pion s'il existe, ou <code>null</code> sinon
	 * @see Level#getPawnAt(Case)
	 */
	public PawnCapture getPawnCaptureAt(final Case c) {
		return new Property<Pawn>() {
			protected boolean validate(Pawn p) { return c==null?false:c.equals(p.getCase()); }
		}.find(this.pawnsCapt);
	}
	
}