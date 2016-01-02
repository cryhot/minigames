package core.game;

import core.movement.Mobility;
import core.board.Case; // javadoc only

/** Une <code>Soul</code> correspond au comportement d'un {@link Ghost pion} ( que l'on nomme âme ).
 * Le comportement d'un pion ne doit pas être visible par les adversaires du {@link Player joueur} possédant ce pion.
 * C'est pourquoi tous les paramètres "cachés" d'un pion se situent ici.
 * @see Ghost
 */
public final class Soul {
	
	/** L'âme du "gentil" fantôme standard. */
	public static final Soul SOUL_GOOD   = new Soul(Mobility.ADJACENT,true ,false,true );
	/** L'âme du "méchant" fantôme standard. */
	public static final Soul SOUL_BAD    = new Soul(Mobility.ADJACENT,false,true ,true );
	/** L'âme du "gentil" super-fantôme, pouvant se déplacer en diagonale. */
	public static final Soul SOUL_SGOOD  = new Soul(Mobility.DIAGONAL,true ,false,true );
	/** L'âme du "méchant" super-fantôme, pouvant se déplacer en diagonale. */
	public static final Soul SOUL_SBAD   = new Soul(Mobility.DIAGONAL,false,true ,true );
	/** L'âme du cavalier-fantôme, étant si gentil qu'il ne peut pas manger d'adversaire. */
	public static final Soul SOUL_KNIGHT = new Soul(Mobility.KNIGHT  ,true ,false,false);
	
	/** la carte de mobilité pour les pions de ce type
	 */
	public final Mobility mobility;
	/** <code>true</code> si la victoire peut être remportée par un joueur en plaçant un pion de ce type sur une {@link Case case} de sortie
	 * @see Case#isEscape(int)
	 */
	public final boolean activeVictory;
	
	/** <code>true</code> si la victoire peut être remportée par un joueur en se faisant capturer tous les pions de ce type
	 */
	public final boolean passiveVictory;
	
	/** <code>true</code> si les pions de ce type peuvent capturer des adversaires
	 */
	public final boolean canEat;
	
	/** Construit une âme de paramètres donnés.
	 * @param mob  la carte de mobilité utilisée
	 * @param aV  <code>true</code> si le pion peut {@link #activeVictory gagner activement}
	 * @param pV  <code>true</code> si le pion peut {@link #passiveVictory gagner passivement}
	 * @param eat  <code>true</code> si le pion peut capturer des adversaires
	 */
	Soul(Mobility mob,boolean aV, boolean pV, boolean eat) {
		this.mobility = mob;
		this.activeVictory = aV;
		this.passiveVictory = pV;
		this.canEat = eat;
	}
	
}