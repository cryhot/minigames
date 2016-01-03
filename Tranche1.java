//Louis && JR
import core.game.*;
import core.board.*;
import core.movement.*;
import core.stage.*;

public class Tranche1 {
	
	public static void main(String... args) {
		Game game = new Game( new BoardCross() );
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
	
}