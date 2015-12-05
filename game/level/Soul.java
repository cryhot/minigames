//Louis
package game.level;

/** 
 * Une <code>Soul</code> correspond au comportement d'un {@link game.level.Ghost pion}.
 * Le comportement d'un pion ne doit pas être visible par les adversaires du {@link game.level.Player joueur} possédant ce pion.
 * C'est pourquoi tous les paramètres "cachés" d'un pion se situent ici.
 */
public final class Soul {
	public static final Soul SOUL_GOOD = new Soul(null,true,false,true);
	public static final Soul SOUL_BAD = new Soul(null,false,true,true);
	public static final Soul SOUL_SGOOD = new Soul(null,true,false,true);
	public static final Soul SOUL_SBAD = new Soul(null,false,true,true);
	public static final Soul SOUL_KNIGHT = new Soul(null,true,false,false);
	
	/** 
	 * la carte des déplacements possibles pour un pion de ce type
	 */
	public final MoveTable mvTable;
	/** 
	 * <code>true</code> si la victoire peut être remportée en plaçant un pion de ce type sur une {@link game.level.board.Case} de sortie
	 * @see game.level.board.Case#isEscape(int)
	 */
	public final boolean activVictory;
	
	/** 
	 * <code>true</code> si la victoire peut être remportée se faisant capturer tous les pions de ce type
	 */
	public final boolean passivVictory;
	
	/** 
	 * <code>true</code> si tous les pions de ce type peuvent capturer des adversaires
	 */
	public final boolean canEat;
	
	/** 
	 * crée une âme de paramètres donnés.
	 * @param mvT  la carte de déplacement utilisée
	 * @param aV  <code>true</code> si le pion peut {@link #activVictory gagner activement}
	 * @param pV  <code>true</code> si le pion peut {@link #passivVictory gagner passivement}
	 * @param eat  <code>true</code> si le pion peut capturer des adversaires
	 */
	Soul(MoveTable mvT,boolean aV, boolean pV, boolean eat) {
		this.mvTable = mvT;
		this.activVictory = aV;
		this.passivVictory = pV;
		this.canEat = eat;
	}
	
}