
public class Case {
	
	public final int x;
	public final int y;
	private int content;
	
	public Case(int x, int y, int content){
		this.x=x;
		this.y=y;
		this.content=content;
	}
	
	
	public Case apply(Move m){
		return this.Case(this.x+m.x,this.y+m.y);		
	}
	
}
