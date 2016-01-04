import java.util.*;

import core.game.*;
import core.board.Case;


public class InterfaceReader extends PlayerControler{
	
	private Reader reader;
	private ArrayList<String> coordonnates = reader.getMoveOfReader();
	private int position = 0;
	
	protected ArrayList<Case> initialCases(){
		ArrayList<Soul> a1 = reader.getInitialPosition(1);
		ArrayList<Soul> a2 = reader.getInitialPosition(2);
		List<Pawn> pawns = this.getInitialPawns();
		ArrayList<Case> cases = new ArrayList<Case>();
		SortedSet<Case> initCases = this.getInitialCases();
		for(Pawn p : pawns){
			cases.add(null);
		}
		Iterator<Soul> it1 = a1.iterator();
		Iterator<Soul> it2 = a2.iterator();
		for(Case c : initCases ) {
			if(it1.hasNext()){
				Soul soul1 = it1.next();
				for(int i=0;i<pawns.size();i++){
					Soul s1 = this.getSoul(pawns.get(i));
					if(s1.equals(soul1)){
						cases.remove(i);
						cases.add(i,c);
						pawns.remove(i);
						pawns.add(i,null);
						break;
					}
				}	
			}
			else if(it2.hasNext()){
				Soul soul2 = it2.next();
				for(int i=0;i<pawns.size();i++){
					Soul s2 = this.getSoul(pawns.get(i));
					if(s2.equals(soul2)){
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
		if(coordonnates.get(this.position).equals("1")||coordonnates.get(this.position).equals("2"))
			this.position++;
		return coordonnates(coordonnates.get(this.position));
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