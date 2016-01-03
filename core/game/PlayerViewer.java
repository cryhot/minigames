package core.game;

import java.util.Set;
import java.util.List;
import java.util.Iterator;

import core.board.Case;

/** Observateur prenant le point de vue d'un joueur dans une {@link Game partie de Ghost}.
 * Un observateur de joueur établit une restriction des fonctionnalités par rapport à un {@link GlobalViewer observateur} de manière générale.
 * <br><br>
 * Il est recommandé mais pas nécessaire d'hériter de cette classe pour définir un interface.
 * @see Game
 */
public class PlayerViewer extends GlobalViewer {
	Player player;
	
	/** Définit le point de vue de cet observateur.
	 * @param p  le joueur dont est pris le point de vue
	 * @see Game
	 */
	final void setPlayer(Player p) {
		this.player = p;
	}
	
	/** Simule le placement initial des pions des autres joueurs, au début du jeu.
	 * @return  la version désynchronisée, éditable à souhait.
	 * @see GlobalViewer#desynchronize()
	 */
	public GameCapture simulatePlacement() {
		GameCapture capt = this.desynchronize();
		for (Player player:this.getLevel().players) {
			List<Pawn> initPawns = player.getInitialPawns();
			if (initPawns!=null && !this.player.equals(player)) {
				Iterator<Case>cases = player.getInitialCases().iterator();
				for (Pawn p:initPawns)
					capt.relocatePawn(p,cases.next());
			}
		}
		return capt;
	}
	
	@Override
	public Soul getSoul(Pawn p) {
		for (Pawn p2:this.getOriginalLevel().pawns)
			if (p2.equals(p)) {
				if (!p2.isCaptured())
					this.checkOwner(p);
				return super.getSoul(p);
			}
		throw new UnsupportedOperationException("pion non reconnu");
	}
	
	@Override
	public boolean canPlace(Pawn p,Case c) {
		this.checkOwner(p);
		return super.canPlace(p,c);
	}
	
	@Override
	public boolean canMove(Pawn p,Case c) {
		this.checkOwner(p);
		return super.canMove(p,c);
	}
	
	/** Procède au traitement des erreurs dûes à une violation du point de vue.
	 * Le point de vue est violé lorsque le pion n'appartient pas au joueur.
	 * @param p  le pion à vérifier
	 * @throws UnsupportedOperationException  si le pion n'appartient pas au joueur
	 */
	private void checkOwner(Pawn p) {
		if (!p.getOwner().equals(this.player))
			throw new UnsupportedOperationException("tricheur !!!");
	}
	
	/** Renvoie le joueur observé par cet observateur de joueur.
	 * @return  le joueur observé
	 */
	public Player getPlayer() {
		return this.player;
	}
	
}