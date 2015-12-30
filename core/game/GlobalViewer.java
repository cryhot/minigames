package core.game;

import core.board.Case;
import core.board.Board;

public class GlobalViewer {
	Level level;
	
	/** Appelé par Game. */
	final void setLevel(Level l) {
		this.level = l;
	}
	
	public Pawn getPawnAt(Case c) {
		return this.level.getPawnAt(c);
	}
	
	public Board getBoard() {
		return this.level.board;
	}
	
}