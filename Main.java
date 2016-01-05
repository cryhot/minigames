import java.util.Scanner;
import core.game.*;
import core.board.*;
import core.movement.*;
import core.stage.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main{
	
	
	public static void main(String[]args){
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("_______________________________________________");
		System.out.println("*     ****  *  * **** ***** ******* *****     *");
		System.out.println("*     *     *  * *  * *        *    *         *");
		System.out.println("*     * *** **** *  * *****    *    *****     *");		
		System.out.println("*     * *   *  * *  *     *    *        *     *");		
		System.out.println("*     ***   *  * **** *****    *    *****     *");		
		System.out.println("_______________________________________________");
		
		System.out.println("\tBonjour et bienvenue dans Ghosts !");
		System.out.println("\n\tVeuillez choisir votre mode de jeu : ");
		System.out.println("\to 1 : Mode Normal (Joueur vs Joueur)");
		System.out.println("\to 2 : Mode Automatique");
		System.out.println("\to 3 : Mode Normal + TRICHE !");
		System.out.println("\to 4 : Mode Normal + GUI TRICHE !");
		
		int n=sc.nextInt();
		if(n==1)
			tranche1(askForBoard());
		if(n==2)
			GhostsReader.play();
		if(n==3)
			tranche2(askForBoard());
		if(n==4)
			tranche3(askForBoard());
		
	}
	
	// demande à l'utilisateur un plateau
	public static StageBoard askForBoard() {
		Scanner sc = new Scanner(System.in);
		StageBoard[] boards = new StageBoard[]{new BoardSquare(), new BoardCross(),new BoardSquare(4),};
		System.out.println("\nQuel plateau voulez-vous utiliser ? :");
		System.out.println("1 : Plateau Normal");
		System.out.println("2 : Plateau Modifié");
		System.out.println("3 : Mini Plateau");
		int n = sc.nextInt();
		if(n>0&&n<=boards.length)
			return boards[n-1];
		System.exit(0);
		return null;
	}
	
	/** Mode Normal */
	public static void tranche1(StageBoard board){
		Game game = new Game( board );
		PlayerControler j1 = new userinterface.textualinterface.Interface();
		PlayerControler j2 = new userinterface.textualinterface.Interface();
		game.subscribe(j1,0);
		game.subscribe(j2,1);
		createGUI(j1,"Player 1's View"); // GUI joueur 1
		createGUI(j2,"Player 2's View"); // GUI joueur 2
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
	
	/** Mode Triche */
	public static void tranche2(StageBoard board){
		Game game = new Game( board );
		PlayerControler j1 = new userinterface.textualinterface.Interface(new AdminViewer(game)); // mode triche
		PlayerControler j2 = new userinterface.textualinterface.Interface(new AdminViewer(game));
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
	
	/** Mode GUI Triche */
	public static void tranche3(StageBoard board){
		Game game = new Game( board );
		PlayerControler j1 = new userinterface.textualinterface.Interface();
		PlayerControler j2 = new userinterface.textualinterface.Interface();
		game.subscribe(j1,0);
		game.subscribe(j2,1);
		createGUI(new AdminViewer(game),"Game Master's View"); // GUI
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
	
	public static void createGUI(GlobalViewer viewer,String title) {
		JPanel view = new userinterface.graphicalinterface.GraphicalView(viewer);
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		frame.setTitle(title);
		frame.setResizable(false);
		frame.getContentPane().add(view);
		frame.pack();
		frame.setVisible(true);
	}
	
	
}