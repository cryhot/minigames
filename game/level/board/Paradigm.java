//JR
package game.level.board;

/**
 * Un <code>Paradigm</code> est une manière d'interpréter des coordonnées.
 * Il correspond à la partie statique du jeu ( basiquement, le plateau sur lequel les pions se meuvent ).
 * On s'y repère suivant un système à deux coordonnées, l'objet {@link Case} implémentant cette fonctionnalité.
 * @see Board
 */
public enum Paradigm {
	/** Interprétation des coordonnées comme décrivant des cases carrées. */
	SQUARE(),
	/** Interprétation des coordonnées comme décrivant des cases hexagonales. */
	HEXAGON();
	
	Paradigm() {
		// nothing here
	}
	
}