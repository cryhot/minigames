package core.game;

public class PlayerViewer extends GlobalViewer {
	protected Player player;
	
	/** Appelé par Game. */
	final void setPlayer(Player p) {
		this.player = p;
	}
	
}