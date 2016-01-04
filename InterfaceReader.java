import java.util.*;

import core.game.*;
import core.board.Case;


public class InterfaceReader extends PlayerControler{
	
	private Reader reader;
	private int position1 = 1;
	private int position2 = 2;
	private int player;
	
	public InterfaceReader(Reader r, int n){
		this.reader = r;
		this.player = n;
	}
	
	protected ArrayList<Case> initialCases(){
		ArrayList<Soul> a = new ArrayList<Soul>();
		if(player==0)
			a = reader.getInitialPosition(1);
		if(player==1)
			a = reader.getInitialPosition(2);
		List<Pawn> pawns = this.getInitialPawns();
		ArrayList<Case> cases = new ArrayList<Case>();
		SortedSet<Case> initCases = this.getInitialCases();
		for(Pawn p : pawns){
			cases.add(null);
		}
		Iterator<Soul> it = a.iterator();
	
		for(Case c : initCases ) {
			
				if(it.hasNext()){
					Soul soul = it.next();
					for(int i=0;i<pawns.size();i++){
						Soul s = this.getSoul(pawns.get(i));
						if(s.equals(soul)){
							cases.remove(i);
							cases.add(i,c);
							pawns.remove(i);
							pawns.add(i,null);
							break;
						}
					}	
				}
		}
			
		return cases;
	}
	
	
	

	protected Case selectCase(){
		if(player==0){
			try{
				position1++;
				return coordonnates(reader.coordonnates1.get(this.position1));
				
			}
			catch(Exception e){
				return null;
			}
			
		}
		if(player==1){
			
			try{
				position2++;
				return coordonnates(reader.coordonnates2.get(this.position2));
			}
			catch(Exception e){
				return null;
			}
			
		}
		return null;
	}
	
	
	protected Pawn selectPawn(){
		return this.getLevel().getPawnAt(this.selectCase());
	}
	
	private Case coordonnates(String s) {
		if (s==null)
			return null;
		int x = 0;
		int y = 0;
		int hash1 = 1;
		int hash2 = 0;
		boolean figureFound = false;
		for (int i=0;i<s.length();i++) {
			char c = s.charAt(i);
			if (c>='A' && c<='Z')
				c += 'a'-'A';
			if (c>='a' && c<='z' && !figureFound){
				hash1 *= 26;
				hash2 *= 26;
				hash2 += c-'a';				
			}
			else if (c>='0' && c<='9'){
				y= 10*y + (int)(c - '0');
				figureFound = true;				
			}
			else{
				return null;
			}
		}
		hash1 = (hash1-1)/(25);
		x = hash1+hash2;
		if(x == 0 || y == 0){
			return null;
		}
		return this.getBoard().getCase(this.getBoard().getXMin()+x-1,this.getBoard().getYMin()+y-1);
	}
	
	
	
	
	
	
	
	
	
	
	
	
}