package core.game;

public class PlayerViewer extends GlobalViewer {
	Player player;
	
	/** Appelé par Game. */
	final void setPlayer(Player p) {
		this.setLevel(p.level);
		this.player = p;
	}
	
}