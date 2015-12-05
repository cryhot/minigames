//JR
package game.level.board;

/**
 * Un <code>Board</code> est un plateau de jeu pour Ghost.
 * Il correspond à la partie statique du jeu ( basiquement, le plateau sur lequel les pions se meuvent ).
 * On s'y repère suivant un système à deux coordonnées, l'objet {@link game.level.board.Case} implémentant cette fonctionnalité.
 * @see game.level.board.Case
 */
public abstract class Board {
	protected int[] limit; // [minX,minY,maxX,maxY]
	
	/**
	 * Renvoie la case située aux coordonnées précisées.
	 * @param x  la première coordonnée
	 * @param y  la deuxième coordonnée
	 * @return  une case de coordonnées <code>x</code>, <code>y</code>
	 */
	public final Case getCase(int x, int y) {
		return new Case(this,x,y);
	}
	
	/**
	 * Renvoie un entier symbolisant le contenu de la case. <br><br>
	 * Le contenu d'une case doit rester constant au cours du temps, étant donné qu'un plateau reste fixe.
	 * @param x  la première coordonnée
	 * @param y  la deuxième coordonnée
	 * @return  le contenu de la case, dont la valeur est :
	 * <ul>
	 * 	<li>un entier négatif (<code>-1</code>) : case hors plateau</li>
	 * 	<li><code>0</code> : case vide ( traversable )</li>
	 * 	<li><code>n</code> un entier positif : case de sortie pour le {@link game.level.Player} <code>n-1</code></li>
	 * </ul>
	 * @see game.level.board.Case#getContent()
	 */
	abstract int getContent(int x,int y);
	
}
