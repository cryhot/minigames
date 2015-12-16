//Louis
package game;

public final class Ghost {
	final Soul soul;
	public final Player player;
	
	Ghost(Soul s,Player p){
		this.soul = s;
		this.player = p;
	}
	
	public Player getOwner() {
		return this.player;
	}
	
	Level getLevel() {
		return this.player.getLevel();
	}
	
}