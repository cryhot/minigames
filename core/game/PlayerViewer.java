package core.game;

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
	
	/** Renvoie l'âme d'un pion.
	 * @param p  le pion observée
	 * @return  l'âme de ce pion
	 * @throws UnsupportedOperationException  si le pion que l'on observe n'appartient pas au joueur
	 */
	@Override
	public Soul getSoul(Pawn p) {
		if (!p.getOwner().equals(this.player))
			throw new UnsupportedOperationException("tricheur !!!");
		return super.getSoul(p);
	}
	
}