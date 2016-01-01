package core.movement;

import core.board.Case;
import core.board.Board; // javadoc only

/** Un <code>Move</code> est un vecteur correspondant à un déplacement.
 * Un vecteur de déplacement n'est pas lié à un plateau, il existe par lui même.
 * @see Board
 * @see MoveTable
 */
public final class Move implements Comparable<Move> {
	public final int x;
	public final int y;
	
	/** Construit un déplacement en fonction de ses coordonées.
	 * Les coordonnées sont indiquées de manières relative.
	 * @param x  la première coordonnée
	 * @param y  la deuxième coordonnée
	 */
	public Move(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/** Construit un déplacement correspondant au mouvement d'une case vers une autre.
	 * @param from  la case de départ du déplacement
	 * @param to  la case d'arrivée du déplacement
	 */
	public Move(Case from, Case to) {
		this.x = to.x-from.x;
		this.y = to.y-from.y;
	}
	
	/** Applique ce déplacement sur une case.
	 * @param c  la case de départ du déplacement
	 * @return  la case obtenu par translation de la case spécifiée par ce vecteur de déplacement
	 */
	public Case apply(Case c) {
		return c.board.getCase(c.x+this.x,c.y+this.y);
	}
	
	/** Teste l'égalité entre l'objet spécifié et ce déplacement.
	 * Deux déplacements sont considérés comme égaux si ils décrivent le même déplacement ( ils ont les mêmes coordonnées  ).
	 * @param o  l'objet à comparer par égalité avec ce déplacement
	 * @return  <code>true</code> si l'objet spécifié est égal à ce déplacement
	 */
	@Override
	public boolean equals(Object o) {
		if (o==null||!this.getClass().equals(o.getClass()))
			return false;
		Move m = (Move) o;
		return this.x==m.x && this.y==m.y;
	}
	
	@Override
	public int hashCode() {
		return this.x*11+this.y*13;
	}
	
	/** Compare l'objet spécifié avec ce déplacement.
	 * La comparaison se fait par ordre lexicographique, sur les abscisses puis sur les ordonnées.
	 * @return  un entier négatif, nul ou positif si ce déplacement est respectivement plus petit, égal ou plus grand que l'objet spécifié
	 */
	@Override
	public int compareTo(Move m) {
		if (this.x!=m.x)
			return this.x<m.x?-1:1;
		if (this.y!=m.y)
			return this.y<m.y?-1:1;
		return 0;
	}
	
}