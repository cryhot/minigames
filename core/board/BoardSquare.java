package core.board;

/** Un <code>BoardSquare</code> est un plateau de jeu pour Ghost de forme carrée.
 * Les cases de sorties se trouvent dans les coins ( 2 pour chaque joueur ).
 * @see Board
 * @see Paradigm#SQUARE
 */
public class BoardSquare extends Board {
	private final int size;
	
	/** Construit un plateau de taille spécifiée.
	 * @param size  le nombre de cases en largeur et en hauteur
	 */
	public BoardSquare(int size) {
		super(Paradigm.SQUARE);
		this.size = size;
	}
	
	/** Construit un plateau correspondant à un plateau classique de Ghost.
	 * Un plateau classique fait 6 cases de largeur et de hauteur.
	 */
	public BoardSquare() {
		this(6);
	}
	
	@Override
	int getContent(int x, int y) {
		if (!( 0<=x && x<this.size && 0<=y && y<this.size )) // hors plateau
			return -1;
		if ( y==size-1 && (x==0 || x==size-1) ) // joueur 1
			return 1;
		if ( y==0 && (x==0 || x==size-1) ) // joueur 2
			return 2;
		return 0; // case praticable
	}
	
}