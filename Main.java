import java.util.Scanner;
import core.game.*;
import core.board.*;
import core.movement.*;
import core.stage.*;


public class Main{
	
	
	public static void main(String[]args){
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("_______________________________________________");
		System.out.println("*	****  *  * **** ***** ******* *****       *");
		System.out.println("*	*     *  * *  * *        *    *           *");
		System.out.println("*	* *** **** *  * *****    *    *****       *");		
		System.out.println("*	* *   *  * *  *     *    *        *       *");		
		System.out.println("*	***   *  * **** *****    *    *****       *");		
		System.out.println("_______________________________________________");
		
		System.out.println("\tBonjour et bienvenue dans Ghosts !");
		System.out.println("\n\tVeuillez choisir votre mode de jeu : ");
		System.out.println("\to 1 : Mode Normal (Joueur vs Joueur)");
		System.out.println("\to 2 : Mode Automatique");
		System.out.println("\to 3 : Mode Extension(Pions modifiés !)");
		System.out.println("\to 4 : Mode Normal + TRICHE !");
		System.out.println("\to 5 : Mode Extension + TRICHE !");
		
		
		int n=sc.nextInt();
		if(n==1){
			Board[] boards = new Board{new BoardSquare(), new BoardCross()};
			System.out.println("\nQuel plateau voulez-vous utiliser ? :");
			System.out.println("1 : Plateau Normal");
			System.out.println("2 : Plateau Modifié");
			n=sc.nextInt();
			if(n==1){
				tranche1(boards[0]);
			}
			if(n==2){
				tranche1(boards[1]);
			}
			
		}
		if(n==2){
			tranche2();
		}
		if(n==3){
			tranche3();
		}
		if(n==4){
			tranche4();
		}
		if(n==5){
			tranche5();
		}
		
	}
	
	public static void tranche1(Board board){
		Game game = new Game( board );
		PlayerControler j1 = new userinterface.textualInterface.Interface();
		PlayerControler j2 = new userinterface.textualInterface.Interface();
		game.subscribe(j1,0);
		game.subscribe(j2,1);
		int winner = game.play();
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
		if (winner>=0)
			System.out.println(" FELICITATION, LE JOUEUR "+(winner+1)+" A GAGNE !");
		else
			System.out.println(" MATCH NUL ! TOUT LE MONDE A PERDU !");
		System.out.println();
		
	}
	
	public static void tranche2(){
		
		
	}
	
	public static void tranche3(){
		
	}
	
	public static void tranche4(){
		
	}
	
	public static void tranche5(){
		
	}
	
	
}