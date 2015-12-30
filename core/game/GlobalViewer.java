package core.game;

import core.board.Case;

public class GlobalViewer {
	protected Level level;
	
	/** Appelé par Game. */
	final void setLevel(Level l) {
		this.level = l;
	}
	
	public Pawn getPawnAt(Case c) {
		return this.level.getPawnAt(c);
	}
	
}