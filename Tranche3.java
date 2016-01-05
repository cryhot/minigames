import core.game.*;
import core.board.*;
import core.movement.*;
import core.stage.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Tranche3 {
	
	public static void main(String... args) {
		Game game = new Game( new BoardSquare() );
		PlayerControler j1 = new userinterface.textualinterface.Interface();
		PlayerControler j2 = new userinterface.textualinterface.Interface();
		game.subscribe(j1,0);
		game.subscribe(j2,1);
		
		JPanel view = new userinterface.graphicalinterface.GraphicalView(new AdminViewer(game));
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Tranche3");
		frame.setResizable(false);
		frame.getContentPane().add(view);
		frame.pack();
		frame.setVisible(true);
		
		game.play();
	}
	
}