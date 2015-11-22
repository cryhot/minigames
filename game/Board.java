package game;

public abstract class Board {
  protected int[] limit; // [minX,minY,maxX,maxY]
  
	public final Case getCase(int x, int y) {
    return this.new Case(x,y);
  }
  
  // -1 : case hors plateau
  // 0 : case vide ( traversable )
  // n>0 : case de sortie du joueur (n-1)
  protected abstract int getContent(int x,int y);
  
  public final class Case {
    public final int x;
    public final int y;
    
    public Case(int x, int y){
      this.x=x;
      this.y=y;
    }
    
    private int getContent() {
      return Board.this.getContent(this.x,this.y);
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
    
    // public boolean equals(Object o) {
      // if(this.getClass()!=o.getClass())
        // return false;
      // Board.Case c = (Board.Case) o;
      // returnthis.x==c.x && this.y==c.y && Board.this==Board.c;
    // }
    
    public Board.Case apply(Move m) {
      return Board.this.getCase(this.x+m.x,this.y+m.y);
    }
    
  }
  
}
