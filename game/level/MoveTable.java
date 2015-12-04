//JR
package game.level;

import game.level.board.Case;

/**
 * Un MoveTable est une carte de d�placement, relative � un pion.
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
	 * Valide un d�placement selon la carte de d�placement.
	 * @param move le mouvement � valider
	 * @return true si le d�placement est valide
	 */
	public boolean validate(Move move) {
		return this.moves.contains(move);
	}
	
	/**
	 * Valide un d�placement selon la carte de d�placement.
	 * @param from la case de d�part du d�placement
	 * @param to la case d'arriv�e du d�placement
	 * @return true si le d�placement est valide
	 */
	public boolean validate(Case from, Case to) {
		return this.moves.contains(new Move(from,to));
	}
	
	@Override
	public java.util.Iterator<Move> iterator() {
		return this.moves.iterator();
	}
	
}