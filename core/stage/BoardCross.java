package core.stage;

import java.util.SortedSet;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;

import core.board.*;
import core.game.StageBoard;
import core.game.Soul;

/** Un <code>BoardCross</code> est une variante de plateau de jeu pour Ghost, en forme de croix.
 * Les cases de sorties se trouvent dans les coins ( 2 pour chaque joueur ).
 * @see Board
 * @see Paradigm#SQUARE
 */
public class BoardCross extends StageBoard {
	private final int size;
	
	/** Construit un plateau de taille spécifiée.
	 * @param size  le nombre de cases en largeur et en hauteur
	 * @throws IllegalArgumentException  si la taille est inférieure à 4 cases
	 */
	public BoardCross(int size) {
		super(Paradigm.SQUARE,2);
		if (size<6)
			throw new IllegalArgumentException(Integer.toString(size));
		this.size = size;
		this.limit[0] = 0;
		this.limit[1] = 0;
		this.limit[2] = size-1;
		this.limit[3] = size-1;
	}
	
	/** Construit un plateau correspondant à un plateau de Ghost en croix standard.
	 * Un plateau en croix standard fait 6 cases de largeur et de hauteur.
	 */
	public BoardCross() {
		this(8);
	}
	
	@Override
	protected int getContent(int x, int y) {
		if ( y==size-1 && (x==0 || x==size-1) ) // joueur 1
			return 1;
		if ( y==0 && (x==0 || x==size-1) ) // joueur 2
			return 2;
		if (x<=0 || this.size-1<=x || y<=0 || this.size-1<=y) // hors plateau
			return -1;
		if ((x==1||x==this.size-2) && (y==1||y==this.size-2)) // hors plateau
			return -1;
		return 0; // case praticable
	}
	
	@Override
	protected SortedSet<Case> initialCases(int player) {
		if (player<0||player>=2)
			throw new IndexOutOfBoundsException(Integer.toString(player));
		SortedSet<Case> cases = new TreeSet<Case>();
		for (int y=player*(this.size-4)+1;y<=player*(this.size-4)+2;y++)
			for (int x=2;x<this.size-2;x++)
				cases.add(this.getCase(x,y));
		return cases;
	}
	
	@Override
	protected List<Soul> initialSouls(int player) {
		if (player<0||player>=2)
			throw new IndexOutOfBoundsException(Integer.toString(player));
		List<Soul> souls = new ArrayList<Soul>();
		for (int i=0;i<4;i++)
			souls.add(Soul.SOUL_KNIGHT);
		for (int i=12;i<this.size*2;i++)
			souls.add(Soul.SOUL_SBAD);
		return souls;
	}
	
}