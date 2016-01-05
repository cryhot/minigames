import java.io.File;

import core.game.Game;
import core.game.AdminViewer;
import userinterface.readerinterface.Reader;
import userinterface.graphicalinterface.GraphicalView;

import javax.swing.JFrame;

public class GhostsReader {
	
	public static void play(File file) {
		Game game = new Reader(file,1000);
		
		GraphicalView view = new GraphicalView(new AdminViewer(game));
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(file.toString());
		frame.setResizable(false);
		frame.getContentPane().add(view);
		frame.pack();
		frame.setVisible(true);
		
		game.play();
	}
	
	public static void play() {
		play(new File("Reader.txt"));
	}
	
	public static void main(String[] args) {
		if (args.length>=1)
			play(new File(args[0]));
		else
			play();
	}
	
}