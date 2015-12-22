package core.movement;

import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;

import core.board.Case;

/** Un <code>MoveTable</code> est une carte de déplacement, décrivant des possibilités de déplacement.
 * @see Move
 * @see Mobility
 */
public final class MoveTable implements Iterable<Move> {
	/** La carte de déplacement nulle, infirmant tout déplacement. */
	public static final MoveTable NO_MOVE = new MoveTable();
	/** La carte de déplacement autorisant seulement du sur-place. */
	public static final MoveTable STAY = new MoveTable( new Move(0,0) );
	
	private final Set<Move> moves;
	
	/** Construit une carte de déplacement à partir d'une table de déplacement.
	 * @param moves la liste des déplacements à inclure dans cette carte de déplacement. 
	 */
	public MoveTable(Move... moves) {
		this.moves = new TreeSet<Move>();
		for (Move m:moves)
			this.moves.add(m);
	}
	
	/** Valide un déplacement.
	 * <br><br>
	 * Un déplacement est considéré comme valide s'il appartient à la liste des déplacements possibles spécifiés par cette carte de déplacement.
	 * @param move  le mouvement à valider
	 * @return  <code>true</code> si le déplacement est valide
	 */
	public boolean validate(Move move) {
		return this.moves.contains(move);
	}
	
	/** Valide le déplacement d'une case à une autre.
	 * <br><br>
	 * Un déplacement d'une case à une autre est considéré comme valide s'il appartient à la liste des déplacements possibles spécifiés par cette carte de déplacement.
	 * @param from  la case de départ du déplacement
	 * @param to  la case d'arrivée du déplacement
	 * @return  <code>true</code> si le déplacement est valide
	 */
	public boolean validate(Case from, Case to) {
		return this.moves.contains(new Move(from,to));
	}
	
	/** Renvoie l'ensemble des cases accessibles à partir de la case spécifiée, selon cette carte de déplacement.
	 * @param c  la case de départ des déplacements
	 * @return  l'ensemble des cases obtenues par translation de la case spécifiée par chacun des vecteurs de déplacement
	 * @see Move#apply(Case)
	 */
	public Set<Case> apply(Case c) {
		Set<Case> set = new TreeSet<Case>();
		for (Move m:this.moves)
			set.add(m.apply(c));
		return set;
	}
	
	/** Itère sur tous les déplacements possibles de cette carte de déplacement
	 * @return un itérateur sur les déplacements possibles.
	 */
	@Override
	public Iterator<Move> iterator() {
		return this.moves.iterator();
	}
	
	/** Teste l'égalité entre l'objet spécifié et cette carte de déplacement.
	 * Deux cartes de déplacement sont considérées comme égales si elles autorisent exactement les mêmes déplacements.
	 * @param o  l'objet à comparer par égalité avec ce déplacement
	 * @return  <code>true</code> si l'objet spécifié est égal à cette carte de déplacement
	 */
	@Override
	public boolean equals(Object o) {
		if (!this.getClass().equals(o.getClass()))
			return false;
		MoveTable m = (MoveTable) o;
		return this.moves.equals(m.moves);
	}
	
	@Override
	public int hashCode() {
		return this.moves.hashCode();
	}
	
}