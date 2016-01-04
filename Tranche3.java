import core.game.*;
import core.board.*;
import core.movement.*;
import core.stage.*;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Tranche3 {
	
	public static void main(String... args) {
		Game game = new Game( new BoardSquare() );
		PlayerControler j1 = new userinterface.textualInterface.Interface();
		PlayerControler j2 = new userinterface.textualInterface.Interface();
		game.subscribe(j1,0);
		game.subscribe(j2,1);
		
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Tranche3");
		
		JPanel view = new userinterface.graphicalinterface.GraphicalView(new AdminViewer(game));
		frame.getContentPane().add(view);
		frame.setSize(view.getPreferredSize());
		
		// frame.add(new javax.swing.JButton("42"));
		frame.setVisible(true);
		
		game.play();
	}
	
}