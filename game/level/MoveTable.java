//JR
package game.level;

import game.level.board.Case;

/**
 * Un MoveTable est une carte de déplacement, relative à un pion.
 * @see Soul
 * @see Move
 */
final class MoveTable implements Iterable<Move> {
	private final java.util.Set<Move> moves;
	
	public MoveTable(Move... moves) {
		this.moves = new java.util.TreeSet<Move>();
		for (Move m:moves)
			this.moves.add(m);
	}
	
	/**
	 * Valide un déplacement selon la carte de déplacement.
	 * @param move le mouvement à valider
	 * @return true si le déplacement est valide
	 */
	public boolean validate(Move move) {
		return this.moves.contains(move);
	}
	
	/**
	 * Valide un déplacement selon la carte de déplacement.
	 * @param from la case de départ du déplacement
	 * @param to la case d'arrivée du déplacement
	 * @return true si le déplacement est valide
	 */
	public boolean validate(Case from, Case to) {
		return this.moves.contains(new Move(from,to));
	}
	
	@Override
	public java.util.Iterator<Move> iterator() {
		return this.moves.iterator();
	}
	
}