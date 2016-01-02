package core.game;

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
		this.setLevel(p.level);
		this.player = p;
	}
	
	@Override
	public Soul getSoul(Pawn p) {
		if (!p.isCaptured())
			this.checkOwner(p);
		return super.getSoul(p);
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
	
}