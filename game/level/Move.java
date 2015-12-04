//JR
package game.level;

import game.level.board.Case;

final class Move implements Comparable<Move> {
	public final int x;
	public final int y;
	
	public Move(int x, int y) {
		this.x = x;
		this.y = y;
	}
	public Move(Case from, Case to) {
		this.x = to.x-from.x;
		this.y = to.y-from.y;
	}
	
	public Case apply(Case c) {
		return c.getBoard().getCase(c.x+this.x,c.y+this.y);
	}
	
	@Override
	public boolean equals(Object o) {
		if (!this.getClass().equals(o.getClass()))
			return false;
		Move m = (Move) o;
		return this.x==m.x && this.y==m.y;
	}
	
	@Override
	public int compareTo(Move m) {
		if (this.x!=m.x)
			return this.x<m.x?-1:1;
		if (this.y!=m.y)
			return this.y<m.y?-1:1;
		return 0;
	}
	
}