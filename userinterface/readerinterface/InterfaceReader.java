package userinterface.readerinterface;

import java.util.List;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.Iterator;

import core.game.*;
import core.board.Case;

/** Controleur de joueur utilisé par {@link Reader}.
 * @see Reader
 */
class InterfaceReader extends PlayerControler {
	private Reader reader;
	
	/** Construit un nouveau controleur de joueur.
	 * @param r  le lecteur de jeu originaire
	 */
	public InterfaceReader(Reader r){
		this.reader = r;
	}
	
	@Override
	protected List<Case> initialCases() {
		List<Soul> souls = this.reader.getInitialSoul(this.getPlayer().getIndex());
		List<Pawn> pawns = this.getInitialPawns();
		List<Case> cases = new ArrayList<Case>(pawns.size());
		for (Pawn p : pawns){
			cases.add(null);
		}
		Iterator<Soul> it = souls.iterator(); // ordonne les cases selon l'ordre des soul
		for(Case c : this.getInitialCases()) { // voir Case#compareTo(Case)
			Soul s = it.next();
			for(int i=0;i<pawns.size();i++) {
				Pawn p = pawns.get(i);
				if (p==null)
					continue;
				Soul soul = this.getSoul(p);
				if (soul.equals(s)) {
					cases.set(i,c);
					pawns.set(i,null);
					break;
				}
			}
		}
		this.waitABit(2.2);
		return cases;
	}
	
	@Override
	protected Case selectCase() {
		this.waitABit(0.9);
		return this.reader.getNextCase();
	}
	
	@Override
	protected Pawn selectPawn(){
		return this.getLevel().getPawnAt(this.selectCase());
	}
	
	/** Instaure un temps de latence volontaire, simulant un joueur.
	 * Sans cela, il serait impossible d'afficher à une vitesse correcte le déroulement du jeu.
	 * @param time  le temps, en seconde dans une échelle de temps standard
	 */
	private void waitABit(double time) {
		try {
			Thread.sleep((int)(this.reader.getSpeed()*time));
		} catch (InterruptedException e) {}
	}
	
}