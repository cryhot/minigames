package core.stage;

import java.util.SortedSet;
import java.util.TreeSet;
import java.util.List;
import java.util.ArrayList;

import core.board.*;
import core.game.StageBoard;
import core.game.Soul;

/** Un <code>BoardSquare</code> est un plateau de jeu pour Ghost de forme carrée.
 * Les cases de sorties se trouvent dans les coins ( 2 pour chaque joueur ).
 * @see Board
 * @see Paradigm#SQUARE
 */
public class BoardSquare extends StageBoard {
	private final int size;
	
	/** Construit un plateau de taille spécifiée.
	 * @param size  le nombre de cases en largeur et en hauteur
	 * @throws IllegalArgumentException  si la taille est inférieure à 4 cases
	 */
	public BoardSquare(int size) {
		super(Paradigm.SQUARE,2);
		if (size<4)
			throw new IllegalArgumentException(Integer.toString(size));
		this.size = size;
		this.limit[0] = 0;
		this.limit[1] = 0;
		this.limit[2] = size-1;
		this.limit[3] = size-1;
	}
	
	/** Construit un plateau correspondant à un plateau classique de Ghost.
	 * Un plateau classique fait 6 cases de largeur et de hauteur.
	 */
	public BoardSquare() {
		this(6);
	}
	
	@Override
	protected int getContent(int x, int y) {
		if (x<0 || this.size<=x || y<0 || this.size<=y) // hors plateau
			return -1;
		if ( y==size-1 && (x==0 || x==size-1) ) // joueur 1
			return 1;
		if ( y==0 && (x==0 || x==size-1) ) // joueur 2
			return 2;
		return 0; // case praticable
	}
	
	@Override
	protected SortedSet<Case> initialCases(int player) {
		if (player<0||player>=2)
			throw new IndexOutOfBoundsException(Integer.toString(player));
		SortedSet<Case> cases = new TreeSet<Case>();
		for (int y=player*(this.size-2);y<=player*(this.size-2)+1;y++)
			for (int x=1;x<this.size-1;x++)
				cases.add(this.getCase(x,y));
		return cases;
	}
	
	@Override
	protected List<Soul> initialSouls(int player) {
		if (player<0||player>=2)
			throw new IndexOutOfBoundsException(Integer.toString(player));
		List<Soul> souls = new ArrayList<Soul>();
		for (int i=2;i<this.size;i++)
			souls.add(Soul.SOUL_GOOD);
		for (int i=2;i<this.size;i++)
			souls.add(Soul.SOUL_BAD);
		return souls;
	}
	
}