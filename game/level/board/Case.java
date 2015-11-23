package game.level.board;

public final class Case implements Comparable<Case> {
	private Board board;
	public final int x;
	public final int y;
	
	Case(Board board, int x, int y) {
		this.board = board;
		this.x = x;
		this.y = y;
	}
	
	private int getContent() {
		return this.board.getContent(this.x,this.y);
	}
	
	public boolean isInside() {
		return this.getContent()>=0;
	}
	
	public boolean isEmpty() {
		return this.getContent()==0;
	}
	
	public boolean isEscape() {
		return this.getContent()>0;
	}
	
	public boolean isEscape(int player) {
		if (!this.isEscape())
			return false;
		return this.getContent()==player+1;
	}
	
	@Override
	public boolean equals(Object o) {
		if(!this.getClass().equals(o.getClass()))
			return false;
		Case c = (Case) o;
		return this.x==c.x && this.y==c.y && this.board==c.board;
	}
	
	@Override
	public int compareTo(Case c) {
		int diff = this.board.hashCode()-c.board.hashCode();
		if (diff!=0)
			return diff<0?-1:1;
		if (this.x!=c.x)
			return this.x<c.x?-1:1;
		if (this.y!=c.y)
			return this.y<c.y?-1:1;
		return 0;
	}
	
	public Board getBoard() {
		return this.board;
	}
	
}