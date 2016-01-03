package core.game;

import java.util.List;
import java.util.ArrayList;

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
	private Game game;
	private Level level;
	
	/** Définit le jeu observé.
	 * @param g  le jeu observé
	 * @see Game
	 */
	final void setGame(Game g) {
		this.game = g;
		this.synchronize();
	}
	
	/** Désynchronise la version du niveau vue avec le modèle.
	 * Si la version interne était déjà désynchronisée, elle est resynchonisée au cours de cette opération.
	 * @return  la version désynchronisée, éditable à souhait.
	 * @see GameCapture
	 */
	public final GameCapture desynchronize() {
		this.synchronize();
		GameCapture levelCapt = new GameCapture(this.level);
		this.level = levelCapt;
		return levelCapt;
	}
	
	/** Synchronise la version du niveau vue avec le modèle.
	 */
	public final void synchronize() {
		this.level = this.game.getLevel();
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
	
	/** Renvoie le niveau actuellement observé par cet observateur de jeu.
	 * @return  le niveau observé
	 */
	public Level getLevel() {
		return this.level;
	}
	
	/** Renvoie le niveau observé par défaut par cet observateur de jeu.
	 * @return  le niveau observé par défaut
	 */
	Level getOriginalLevel() {
		return this.game.getLevel();
	}
	
	/** Renvoie le plateau de jeu utilisé dans cette partie.
	 * @return  le plateau de jeu utilisé
	 */
	public Board getBoard() {
		return this.getLevel().board;
	}
	
}