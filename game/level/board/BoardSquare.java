package game.level.board;

public class BoardSquare extends Board {
  private final int size;
  
  public BoardSquare(int size) {
    this.size = size;
  }
  public BoardSquare() {
    this(6);
  }
  
	protected int getContent(int x, int y) {
    if (!( 0<=x && x<this.size && 0<=y && y<this.size )) // hors plateau
      return -1;
    if ( y==size-1 && (x==0 || x==size-1) ) // joueur 1
      return 1;
    if ( y==0 && (x==0 || x==size-1) ) // joueur 2
      return 2;
    return 0;
  }
  
}