package game;

public class Move {
	public final int x;
	public final int y;
	
	public Move(int x, int y) {
		this.x = x;
		this.y = y;
	}
  public Move(Board.Case from,Board.Case to) {
    this.x = to.x-from.y;
		this.y = to.y-from.y;
  }
  
  public boolean equals(Object o) {
    if (!this.getClass().equals(o.getClass()))
      return false;
    Move m = (Move) o;
    return this.x==m.x && this.y==m.y;
  }
	
}