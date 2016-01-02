package core.game;

import core.board.Case;
import core.board.Board;

/** Observateur faisant le lien entre une {@link Game partie de Ghost} et des interfaces externes.
 * Un observateur simple ne possède que des fonctions de "lecture", et pas d'"écriture", sur le jeu.
 * Cet observateur est général et offre le plus de visibilité sur le jeu (on peut parler d'observateur administrateur).
 * <br><br>
 * Il est recommandé mais pas nécessaire d'hériter de cette classe pour définir un interface.
 * @see Game
 */
public class GlobalViewer {
	Level level;
	
	/** Définit le niveau observé.
	 * @param l  le niveau observé
	 * @see Game
	 */
	final void setLevel(Level l) {
		this.level = l;
	}
	
	/** Renvoie le pion situé sur une case du plateau.
	 * @param c  la case observée
	 * @return  le pion situé sur cette case
	 */
	public Pawn getPawnAt(Case c) {
		return this.level.getPawnAt(c);
	}
	
	/** Renvoie l'âme d'un pion.
	 * @param p  le pion observée
	 * @return  l'âme de ce pion
	 * @see Pawn#getSoul()
	 */
	public Soul getSoul(Pawn p) {
		return p.getSoul();
	}
	
	/** Vérifie un positionnement de ce pion sur une case indiquée.
	 * @param p  le pion à placer
	 * @param c  la case où positionner le pion
	 * @return  <code>true</code> si la position est valide
	 * @see Pawn#canPlace(Case)
	 */
	public boolean canPlace(Pawn p,Case c) {
		return p.canPlace(c);
	}
	
	/** Vérifie un déplacement d'un pion vers une case indiquée.
	 * @param p  le pion à déplacer
	 * @param c  la case de destination du mouvement
	 * @return  <code>true</code> si le déplacement est valide
	 * @see Pawn#canMove(Case)
	 */
	public boolean canMove(Pawn p,Case c) {
		return p.canMove(c);
	}
	
	/** Renvoie le plateau de jeu utilisé dans cette partie.
	 * @return  le plateau de jeu utilisé
	 */
	public Board getBoard() {
		return this.level.board;
	}
	
}