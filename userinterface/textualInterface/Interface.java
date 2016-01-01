package userinterface.textualInterface;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import core.game.*;
import core.board.Case;


public class Interface extends PlayerControler{
	
	
	protected List<Case> initialCases(){
		
		List<Pawn> pawns = this.getInitialPawns();
		List<Case> cases = new ArrayList<Case>();
		Set<Case> initCases = this.getInitialCases();
		Scanner sc = new Scanner(System.in);
		for(Pawn p : pawns){
			Case c;
			while(true){
				printGame(this);
				System.out.println("["+pawnToChar(p,this)+"] Ou placer ce pion ? (ex : 1/4)");
				System.out.print(">>> ");
				String s = sc.nextLine();
				c = coordonnates(s);
				if(!initCases.contains(c)){
					System.out.println("Votre Case est invalide veuillez recommencer");
				}
				else if(cases.contains(c)){
					System.out.println("Vous avez déjà placé un pion sur cette case");
				}
				else{
					break;
				}
			} 
			cases.add(c);	
			
		}
		sc.close();
		return cases;
	}
	
	
	private Case coordonnates(String s){
		int x,y;
		String[] str = s.split("/");
		x = Integer.parseInt(str[0]);
		y = Integer.parseInt(str[1]);
		return this.getBoard().getCase(x,y);
	}
	
	protected Pawn selectPawn() {
		System.out.println("Quel pion bouger ? (ex : 1/4)");
		System.out.print(">>> ");
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		sc.close();
		return this.getPawnAt(coordonnates(s));
	}
	
	protected Case selectCase() {
		System.out.println("Ou le bouger ? (ex : 1/4)");
		System.out.print(">>> ");
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		sc.close();
		return coordonnates(s);
	}
	
	public static void printGame(GlobalViewer g) {
		System.out.println();
		int minX = g.getBoard().getXMin();
		int minY = g.getBoard().getYMin();
		int maxX = g.getBoard().getXMax();
		int maxY = g.getBoard().getYMax();
		char[] marginC = new char[Integer.toString(maxY).length()+1];
		for (int i=0;i<marginC.length;i++)
			marginC[i]=' ';
		String margin = new String(marginC);
		for(int y=maxY;y>=minY;y--) {
			String row = Integer.toString(y-minY+1);
			System.out.print(margin.concat(row).substring(row.length()));
				System.out.print(" ");
			for(int x=minX;x<=maxX;x++) {
				Case c = g.getBoard().getCase(x,y);
				boolean inside = c.isInside();
				System.out.print(" ");
				System.out.print(inside?'[':' ');
				Pawn p = g.getPawnAt(c);
				if (p!=null)
					System.out.print(pawnToChar(p,g));
				else if (c.isEscape())
					System.out.print('x');
				else
					System.out.print(' ');
				System.out.print(inside?']':' ');
			}
			System.out.println("\n");
		}
		System.out.print(margin);
		System.out.print(" ");
		for(int x=1;x<=maxX-minX+1;x++) {
			System.out.print(" ");
			String col = intToAlpha(x);
			switch (col.length()) {
				case 0:
					col = " - "; break;
				case 1:
					col = " "+col+" "; break;
				case 2:
					col = " "+col; break;
				case 3:
					break;
				default:
					col = "-"+col.charAt(col.length()-2)+col.charAt(col.length()-1);
			}
			System.out.print(col);
		}
		System.out.println("\n");
	}
	
	private static char pawnToChar(Pawn p,GlobalViewer g) {
		Soul s;
		try {
			s = g.getSoul(p);
		} catch(UnsupportedOperationException e) {
			return 'O';
		}
		if(s.equals(s.SOUL_GOOD)) 
			return 'G';
		if(s.equals(s.SOUL_SGOOD))
			return 'G';
		if(s.equals(s.SOUL_BAD))
			return 'B';
		if(s.equals(s.SOUL_SBAD))
			return 'B';
		if(s.equals(s.SOUL_KNIGHT))
			return 'K';
		return '?';
	}
	
	private static String intToAlpha(int n) {
    if (n<=0)
      throw new IndexOutOfBoundsException(String.valueOf(n));
    int length = 0;
    long w = 1;
    while (n>=w) {
      n -= w;
      w *= 26;
      length++;
    }
    char[] letters = new char[length];
    for (int l=length-1;l>=0;l--) {
      letters[l] = (char)('a'+n%26);
      n /= 26;
    }
		return new String(letters);
  }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}