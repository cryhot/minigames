package game.level.board;

public abstract class Board {
	protected int[] limit; // [minX,minY,maxX,maxY]
	
	public final Case getCase(int x, int y) {
		return new Case(this,x,y);
	}
	
	// -1 : case hors plateau
	// 0 : case vide ( traversable )
	// n>0 : case de sortie du joueur (n-1)
	abstract int getContent(int x,int y);
	
}
