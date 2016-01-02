package userinterface.textualInterface;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.NoSuchElementException;

import core.game.*;
import core.board.Case;


public class Interface extends PlayerControler {
	
	protected List<Case> initialCases() {
		List<Pawn> pawns = this.getInitialPawns();
		List<Case> cases = new ArrayList<Case>();
		Set<Case> initCases = this.getInitialCases();
		Scanner sc = new Scanner(System.in);
		for(Pawn p : pawns){
			String errorCode = "";
			Case c;
			while(true) {
				String s = this.printAndAsk(errorCode+"\n\n("+pawnToChar(p,this)+") Veuillez placer ce pion... (ex : a2)");
				c = coordonnates(s);
				if(c==null)
					errorCode = "Votre format de coordonnees est incorrect.";
				else if(!initCases.contains(c)) {
					if (c.isInside())
						errorCode = "Vous ne pouvez pas placer de pion ici pour le moment.";
					else
						errorCode = "Cette case est interdite.";
				}
				else if(cases.contains(c))
					errorCode = "Vous avez deja place un pion sur cette case.";
				else
					break;
			} 
			cases.add(c);
		}
		return cases;
	}
	
	protected Pawn selectPawn() {
		String s = printAndAsk("\n\nSelectionnez un pion a bouger (ex : a2)");
		return this.getPawnAt(coordonnates(s));
	}
	
	protected Case selectCase() {
		String s = printAndAsk("\n\n(?) Selectionnez une destination (ex : a2)");
		return coordonnates(s);
	}
	
	private String printAndAsk(String desc) {
		printGame(this);
		System.out.print("\n ");
		System.out.println(desc);
		System.out.print(">>> ");
		try {
			return new Scanner(System.in).nextLine();
		} catch (NoSuchElementException e) {
			System.out.println("\nLE PROGRAMME A ETE INTERROMPU");
			System.exit(0);
		}
		return null;
	}
	
	private static void printGame(GlobalViewer g) {
		printGameSquare(g);
	}
	
	private static void printGameSquare(GlobalViewer g) {
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
				System.out.print(" ");
				System.out.print(caseToString(g,x,y));
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
	
	private static String caseToString(GlobalViewer g,int x,int y) {
		StringBuilder str = new StringBuilder(3);
		Case c = g.getBoard().getCase(x,y);
		boolean inside = c.isInside();
		str.append(inside?'[':' ');
		Pawn p = g.getPawnAt(c);
		if (p!=null)
			str.append(pawnToChar(p,g));
		else if (c.isEscape())
			str.append('x');
		else
			str.append(' ');
		str.append(inside?']':' ');
		return str.toString();
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

	private Case coordonnates(String s){
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