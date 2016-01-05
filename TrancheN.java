import java.io.File;

import core.game.Game;
import core.game.AdminViewer;
import userinterface.graphicalinterface.GraphicalView;

import javax.swing.JFrame;

public class TrancheN {
	
	public static void main(String[] args) {
		Game game = new Reader(new File("Reader.txt"),1000);
		
		GraphicalView view = new GraphicalView(new AdminViewer(game));
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Tranche3");
		frame.setResizable(false);
		frame.getContentPane().add(view);
		frame.pack();
		frame.setVisible(true);
		
		game.play();
	}
	
}