//Louis & JR
package game;

import movement.Mobility;

/** 
 * Une <code>Soul</code> correspond au comportement d'un {@link Ghost pion} ( que l'on nomme âme ).
 * Le comportement d'un pion ne doit pas être visible par les adversaires du {@link Player joueur} possédant ce pion.
 * C'est pourquoi tous les paramètres "cachés" d'un pion se situent ici.
 */
public final class Soul {
	public static final Soul SOUL_GOOD = new Soul(null,true,false,true);
	public static final Soul SOUL_BAD = new Soul(null,false,true,true);
	public static final Soul SOUL_SGOOD = new Soul(null,true,false,true);
	public static final Soul SOUL_SBAD = new Soul(null,false,true,true);
	public static final Soul SOUL_KNIGHT = new Soul(null,true,false,false);
	
	/** 
	 * la carte de mobilité pour les pions de ce type
	 */
	public final Mobility mobility;
	/** 
	 * <code>true</code> si la victoire peut être remportée par un joueur en plaçant un pion de ce type sur une {@link board.Case case} de sortie
	 * @see board.Case#isEscape(int)
	 */
	public final boolean activVictory;
	
	/** 
	 * <code>true</code> si la victoire peut être remportée par un joueur en se faisant capturer tous les pions de ce type
	 */
	public final boolean passivVictory;
	
	/** 
	 * <code>true</code> si les pions de ce type peuvent capturer des adversaires
	 */
	public final boolean canEat;
	
	/** 
	 * Construit une âme de paramètres donnés.
	 * @param mob  la carte de mobilité utilisée
	 * @param aV  <code>true</code> si le pion peut {@link #activVictory gagner activement}
	 * @param pV  <code>true</code> si le pion peut {@link #passivVictory gagner passivement}
	 * @param eat  <code>true</code> si le pion peut capturer des adversaires
	 */
	Soul(Mobility mob,boolean aV, boolean pV, boolean eat) {
		this.mobility = mob;
		this.activVictory = aV;
		this.passivVictory = pV;
		this.canEat = eat;
	}
	
}