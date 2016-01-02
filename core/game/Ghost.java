package core.game;

/** Un <code>Ghost</code> représente un fantôme du jeu de Ghost, de manière fixe.
 * Un fantôme est ainsi la partie statique d'un pion au cours d'une partie.
 * Une instance de cette classe définit par la même occasion l'identité propre du fantome/pion.
 * @see Pawn
 */
public final class Ghost {
	final Soul soul;
	public final Player player;
	
	/** Construit un nouveau fantôme.
	 * @param s  l'âme du fantôme créé, correspondant au comportement privé d'un fantôme
	 * @param p  le joueur en possession du pion
	 */
	Ghost(Soul s,Player p){
		this.soul = s;
		this.player = p;
	}
	
	/** Construit un nouveau pion représentant ce fantôme.
	 * @return  le pion créé
	 */
	Pawn invokePawn() {
		return new Pawn(this);
	}
	
	/** Renvoie le joueur qui possède ce fantôme.
	 * @return  le joueur en possession de ce fantôme
	 */
	public Player getOwner() {
		return this.player;
	}
	
	/** Renvoie le niveau auquel apparient ce fantôme.
	 * @return  le niveau de ce fantôme
	 */
	Level getLevel() {
		return this.player.getLevel();
	}
	
}