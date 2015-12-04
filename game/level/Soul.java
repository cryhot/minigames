//Louis

package game.level;

public abstract class Soul{
	
	public final boolean activVictory;//si la victoire peut etre remportée en allant sur une case de sortie
	public final boolean passivVictory;//si la victoire peut etre en remportée en mangeant les 4 bons ghosts de l'adversaire
	public final boolean canEat;//si ce ghost peut manger un ghost adverse;
	public final MoveTable mv;//carte de déplacement du pion
}