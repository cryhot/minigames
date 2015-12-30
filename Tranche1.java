//Louis && JR
import core.game.*;
import core.board.*;
import core.movement.*;
import core.stage.*;

public class Tranche1 {
	
	public static void main(String... args) {
		
		StageBoard plateau = new BoardSquare(6);
		Game game = new Game(plateau);
		PlayerControler j1 = new userinterface.textualInterface.Interface();
		PlayerControler j2 = new userinterface.textualInterface.Interface();
		game.subscribe(j1,0);
		game.subscribe(j2,1);
		game.start();
		
	}
	
}