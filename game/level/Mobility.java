//JR
package game.level;

import game.level.board.Paradigm;

/**
 * Une <code>Mobility</code> est une carte de mobilité, qui associe une carte de déplacement à chaque paradigme.
 * @see MoveTable
 * @see Paradigm
 */
final class Mobility {
	/** Une mobilité sur les cases adjacentes. */
	public static final Mobility ADJACENT = new Mobility()
		.setMoveTable( Paradigm.SQUARE, new MoveTable(
			new Move(-1, 0),
			new Move( 0,-1),
			new Move( 0, 1),
			new Move( 1, 0)
		) )
		.setMoveTable( Paradigm.HEXAGON, new MoveTable(
			new Move(-1,-1),
			new Move(-1, 0),
			new Move( 0,-1),
			new Move( 0, 1),
			new Move( 1, 0),
			new Move( 1, 1)
		) )
	;
	/** Une mobilité sur les cases adjacentes et diagonales. */
	public static final Mobility DIAGONAL = new Mobility()
		.setMoveTable( Paradigm.SQUARE, new MoveTable(
			new Move(-1,-1),
			new Move(-1, 0),
			new Move(-1, 1),
			new Move( 0,-1),
			new Move( 0, 1),
			new Move( 1,-1),
			new Move( 1, 0),
			new Move( 1, 1)
		) )
		.setMoveTable( Paradigm.HEXAGON, new MoveTable(
			new Move(-2,-1),
			new Move(-1,-2),
			new Move(-1,-1),
			new Move(-1, 0),
			new Move(-1, 1),
			new Move( 0,-1),
			new Move( 0, 1),
			new Move( 1,-1),
			new Move( 1, 0),
			new Move( 1, 1),
			new Move( 1, 2),
			new Move( 2, 1)
		) )
	;
	/** Une mobilité comparable à celle du cavalier aux échecs. */
	public static final Mobility KNIGHT = new Mobility()
		.setMoveTable( Paradigm.SQUARE, new MoveTable(
			new Move(-2,-1),
			new Move(-2, 1),
			new Move(-1,-2),
			new Move(-1, 2),
			new Move( 1,-2),
			new Move( 1, 2),
			new Move( 2,-1),
			new Move( 2, 1)
		) )
		.setMoveTable( Paradigm.HEXAGON, new MoveTable(
			new Move(-3,-2),
			new Move(-3,-1),
			new Move(-2,-3),
			new Move(-2, 1),
			new Move(-1,-3),
			new Move(-1, 2),
			new Move( 1,-2),
			new Move( 1, 3),
			new Move( 2,-1),
			new Move( 2, 3),
			new Move( 3, 1),
			new Move( 3, 2)
		) )
	;
	
	private final java.util.Map<Paradigm,MoveTable> moveTables;
	
	/**
	 * Construit une carte de mobilité vide.
	 * La carte de mobilité vide associe à chaque paradigme la {@link MoveTable#NO_MOVE carte de déplacement nulle}.
	 */
	public Mobility() {
		this.moveTables = new java.util.EnumMap<Paradigm,MoveTable>(Paradigm.class);
	}
	
	/**
	 * Attribue une carte de déplacement à un paradigme.
	 * Si la carte de déplacement associée est nulle, cette carte de mobilité réagira comme si c'était la {@link MoveTable#NO_MOVE carte de déplacement nulle}.
	 * @param p  le paradigme auquel est associé la carte de déplacement
	 * @param mvT  la carte de déplacement associée
	 * @return  cette carte de mobilité ( pour un enchainement de méthodes plus pratique )
	 * @throws NullPointerException  si le paradigme fourni est <code>null</code>
	 */
	protected Mobility setMoveTable(Paradigm p, MoveTable mvT) {
		if (mvT!=null)
			this.moveTables.put(p,mvT);
		else
			this.moveTables.remove(p);
		return this;
	}
	
	/**
	 * Renvoie la carte de déplacement associé à un paradigme.
	 * @param p  le paradigme utilisé
	 * @return  la carte de déplacement associée
	 * @throws NullPointerException  si le paradigme utilisé est <code>null</code>
	 */
	public MoveTable getMoveTable(Paradigm p) {
		MoveTable mvT = this.moveTables.get(p);
		if (mvT==null)
			mvT = MoveTable.NO_MOVE;
		return mvT;
	}
	
}