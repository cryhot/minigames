package core.game;

import java.util.Set;
import java.util.TreeSet;
import java.util.List;
import java.util.Iterator;

import core.exceptions.ActionException;
import core.board.Case;

/** Controleur de joueur, faisant le lien entre un joueur d'une {@link Game partie de Ghost} et des interfaces externes.
 * Cette classe implémente des fonctions d'éditions, en addition des fonctions d'observation de l'{@link PlayerViewer observateur de joueur} dont cette classe hérite.
 * <br><br>
 * Il est nécessaire d'hériter de cette classe pour définir un interface.
 * En effet il faudra redéfinir les méthodes abtraites {@link #initialCases()}, {@link #selectPawn()} et {@link #selectCase()}.
 * @see Game
 */
public abstract class PlayerControler extends PlayerViewer {
	
	/** Cette méthode, appelée par la mécanique du jeu, demmande au controleur de placer les pions du joueur controlé.
	 * Cette fonction se termine à tous les coups et se doit de rattraper les exceptions, pour exécuter l'action correctement à terme.
	 * @see Player#placePawns()
	 * @see Player#placePawn(Pawn,Case)
	 */
	final void placePawns() {
		Set<Case> initCases = this.getInitialCases();
		List<Pawn> initPawns = this.getInitialPawns();
		while (true) {
			List<Case> cases = this.initialCases();
			if (cases.size()!=initPawns.size() || new TreeSet<Case>(cases).size()!=cases.size() || !initCases.containsAll(cases))
				continue;
			try {
				Iterator<Case> c = cases.iterator();
				for (Pawn p:initPawns) {
					this.player.placePawn(p,c.next());
				}
				return;
			} catch (NullPointerException e) {
			} catch (ActionException e) {
			}
		}
	}
	
	/** Cette méthode, appelée par la mécanique du jeu, demmande au controleur de bouger un et un seul pion, lors de son tour de jeu.
	 * Cette fonction se termine à tous les coups et se doit de rattraper les exceptions, pour exécuter l'action correctement à terme.
	 * @see Player#playTurn()
	 * @see Player#movePawn(Pawn,Case)
	 */
	final void playTurn() {
		while (true) {
			try {
				this.player.movePawn(this.selectPawn(),this.selectCase());
				return;
			} catch (NullPointerException e) {
			} catch (ActionException e) {
			}
		}
	}
	
	/** Cette méthode, appelée lorsque nécéssaire par le jeu, renvoie la position initiale des pions choisie par le controleur.
	 * La liste ordonnée des cases renvoyée doit être une version réorganisée de l'ensemble des cases initiales,
	 * de telle sorte qu'elle définisse la case où placer chaqu'un des pions de la liste ordonnée des pions à placer initialement.
	 * @return  la liste ordonnée des cases
	 * @see #getInitialPawns()
	 * @see #getInitialCases()
	 */
	protected abstract List<Case> initialCases();
	
	/** Cette méthode, appelée lorsque nécéssaire par le jeu, renvoie le pion à déplacer séléctionné par le controleur.
	 * @return  le pion à déplacer
	 * @see #selectCase()
	 */
	protected abstract Pawn selectPawn();
	
	/** Cette méthode, appelée lorsque nécéssaire par le jeu, renvoie la case où déplacer le pion séléctionné par le controleur.
	 * @return  la case de destination du mouvement
	 * @see #selectPawn()
	 */
	protected abstract Case selectCase();
	
	/** Renvoie la liste ordonnée des pions à placer au début du jeu.
	 * @return  la liste des pions à placer, ou <code>null</code> si le placement a déjà été exécuté.
	 */
	public List<Pawn> getInitialPawns() {
		return this.player.getInitialPawns();
	}
	
	/** Renvoie l'ensemble des cases de départ où placer les pions au début du jeu.
	 * @return  l'ensemble des cases de départ, ou <code>null</code> si le placement a déjà été exécuté.
	 */
	public Set<Case> getInitialCases() {
		return this.player.getInitialCases();
	}
	
}