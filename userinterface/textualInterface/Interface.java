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
				System.out.println(printSoul(p,this) + " Veuillez placer votre pion (donner une coordonnées de la forme nombre/nombre\n correspondant respectivement à la ligne et à la colonne; ex : 1/4) :\n");
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
	
	protected Pawn selectPawn(){
		
		System.out.println("Entrez les coordonnées du pion que vous voulez bouger (ex : 1/4) :\n");
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		return this.getPawnAt(coordonnates(s));
		
	}
	
	protected Case selectCase(){
		
		System.out.println("Entrez les coordonnées de la case sur laquelle vous voulez bouger votre pion (ex : 1/4) :\n");
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		return coordonnates(s);
		
	}
	
	public static void printGame(GlobalViewer g){
		
		for(int y=g.getBoard().getYMin();y<g.getBoard().getYMax();y++){
			for(int x=g.getBoard().getXMin();x<g.getBoard().getXMax();x++){
				
				Pawn p = g.getPawnAt(g.getBoard().getCase(x,y));
				if(p!=null){
				System.out.print("[" + printSoul(p,g) + "]");
				}
				else{
					if(g.getBoard().getCase(x,y).isInside())
						System.out.print(" ");
					if(g.getBoard().getCase(x,y).isEmpty())
						System.out.print("[-]");
					if(g.getBoard().getCase(x,y).isEscape())
						System.out.print("[x]");
				}
			}
			System.out.print("\n+--+--+--+--+\n");
			
		}
	}
	
	
	
	private static String printSoul(Pawn p, GlobalViewer g){
				
		try{
			g.getSoul(p);
		}
		catch(Exception E){
			System.out.println("O");
		}		
		Soul s = g.getSoul(p);
		if(s.equals(s.SOUL_GOOD)) 
			return "G";
		if(s.equals(s.SOUL_SGOOD))
			return "G";
		if(s.equals(s.SOUL_BAD))
			return "B";
		if(s.equals(s.SOUL_SBAD))
			return "B";
		if(s.equals(s.SOUL_KNIGHT))
			return "K";
		return "?";						
		
					
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}