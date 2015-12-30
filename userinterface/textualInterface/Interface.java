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
		for(Pawn p : pawns){
			Case c;
			while(true){
				System.out.println("Veuillez placer votre pion (donner une coordonnées de la forme nombre/nombre\n correspondant respectivement à la ligne et à la colonne; ex : 1/4) :\n");
				Scanner sc = new Scanner(System.in);
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
	
	
}
