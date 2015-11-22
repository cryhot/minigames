public abstract class Board {

	public abstract Case getCase(int x,int y);




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
			return this.getCase(this.x+m.x,this.y+m.y);		
		}
		
	}

}
