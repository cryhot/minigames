package core.game;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import core.exceptions.ActionException;
import core.board.Case;

public abstract class PlayerControler extends PlayerViewer {
	
	// place les pions
	final void placePawns() {
		Set<Case> initCases = this.getInitialCases();
		while (true) {
			List<Case> cases = this.initialCases();
			if (cases.size()!=initCases.size() || !initCases.containsAll(cases))
				continue;
			try {
				Iterator<Case> c = cases.iterator();
				for (Pawn p:this.getInitialPawns()) {
					this.player.placePawn(p,c.next());
				}
				return;
			} catch (NullPointerException e) {
			} catch (ActionException e) {
			}
		}
	}
	
	// bouge 1 et un seul pion
	final void playTurn() {
		while (true) {
			try {
				this.player.movePawn(this.selectPawn(),this.selectCase());
				return;
			} catch (NullPointerException e) {
			} catch (ActionException e) {
			}
		}
	}
	
	protected abstract List<Case> initialCases();
	
	protected abstract Pawn selectPawn();
	protected abstract Case selectCase();
	
	public final List<Pawn> getInitialPawns() {
		return this.player.getInitialPawns();
	}
	
	public final Set<Case> getInitialCases() {
		return this.player.getInitialCases();
	}
	
}