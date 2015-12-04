//Louis && JR
import game.level.*;
import game.level.board.*;

public class Tranche1 {
	
	public static void main(String... args) {
		
		Board plateau = new BoardSquare(6);
		System.out.println(plateau.getCase(0,6).equals(plateau.getCase(0,6)));
		
	}
	
}