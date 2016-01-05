import java.io.File;

import core.game.Game;
import core.game.AdminViewer;
import userinterface.readerinterface.Reader;
import userinterface.graphicalinterface.GraphicalView;

import javax.swing.JFrame;

/** Programme annexe permettant de jouer automatiquement un fichier texte.
 * @see #main(String[])
 */
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
	
	/** Lance une partie en mode lecture sur le fichier spécifié en argument.
	 * Si aucun argument n'est donné, lit le fichier par défaut.
	 * @param args  les arguments
	 */
	public static void main(String[] args) {
		if (args.length>=1)
			play(new File(args[0]));
		else
			play();
	}
	
}