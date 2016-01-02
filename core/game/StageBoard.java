package core.game;

import java.util.Set;
import java.util.List;

import core.board.*;

/** Un <code>StageBoard</code> est un plateau de jeu avec une position initiale pour les pions des joueurs.
 * Les cases de sorties se trouvent dans les coins ( 2 pour chaque joueur ).
 * @see Board
 * @see Game
 */
public abstract class StageBoard extends Board {
	public final int players;
	
	/** Construit un plateau de paradigme donné pour un certain nombre de joueur.
	 * @param p  le paradigme du plateau à créer
	 * @param players  le nombre de joueurs
	 * @throws NullPointerException  si le paradigme donné est <code>null</code>
	 * @throws IllegalArgumentException  si le nombre de joueur est négatif
	 */
	protected StageBoard(Paradigm p,int players) {
		super(p);
		if (players<0)
			throw new IllegalArgumentException(Integer.toString(players));
		this.players = players;
	}
	
	/** Renvoie l'ensemble des cases initiales pour les pions du joueur donné.
	 * @param player  l'index du joueur spécifié
	 * @return  l'ensemble des cases initiales
	 * @throws IndexOutOfBoundsException  si l'index du joueur est invalide
	 */
	protected abstract Set<Case> initialCases(int player);
	
	/** Renvoie l'ensemble des âmes des pions à distribuer parmi les cases initiales pour un joueur donné.
	 * @param player  l'index du joueur spécifié
	 * @return  l'ensemble des âmes des pions à distribuer
	 * @throws IndexOutOfBoundsException  si l'index du joueur est invalide
	 */
	protected abstract List<Soul> initialSouls(int player);
	
}