//JR
package game.level;

import game.level.board.Case;

/**
 * Un <code>MoveTable</code> est une carte de déplacement, décrivant des possibilités de déplacement.
 * @see game.level.Soul
 * @see game.level.Move
 */
final class MoveTable implements Iterable<Move> {
	private final java.util.Set<Move> moves;
	
	/**
	 * Construit une carte de déplacement à partir d'une table de déplacement.
	 * @param moves la liste des déplacements à inclure dans cette carte de déplacement. 
	 */
	public MoveTable(Move... moves) {
		this.moves = new java.util.TreeSet<Move>();
		for (Move m:moves)
			this.moves.add(m);
	}
	
	/**
	 * Valide un déplacement. <br><br>
	 * Un déplacement est considéré comme valide s'il appartient à la liste des déplacements possibles spécifiés par cette carte de déplacement.
	 * @param move  le mouvement à valider
	 * @return  <code>true</code> si le déplacement est valide
	 */
	public boolean validate(Move move) {
		return this.moves.contains(move);
	}
	
	/**
	 * Valide le déplacement d'une case à une autre. <br><br>
	 * Un déplacement d'une case à une autre est considéré comme valide s'il appartient à la liste des déplacements possibles spécifiés par cette carte de déplacement.
	 * @param from  la case de départ du déplacement
	 * @param to  la case d'arrivée du déplacement
	 * @return  <code>true</code> si le déplacement est valide
	 */
	public boolean validate(Case from, Case to) {
		return this.moves.contains(new Move(from,to));
	}
	
	/**
	 * Itère sur tous les déplacements possibles de cette carte de déplacement
	 * @return un itérateur sur les déplacements possibles.
	 */
	@Override
	public java.util.Iterator<Move> iterator() {
		return this.moves.iterator();
	}
	
}