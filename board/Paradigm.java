//JR
package board;

/**
 * Un <code>Paradigm</code> est une manière d'interpréter des coordonnées.
 * Il correspond à la partie statique du jeu ( basiquement, le plateau sur lequel les pions se meuvent ).
 * On s'y repère suivant un système à deux coordonnées, l'objet {@link Case} implémentant cette fonctionnalité.
 * @see Board
 */
public enum Paradigm {
	/** Interprétation des coordonnées comme décrivant des cases carrées.
	 * <br> <img src="./doc-files/Paradigm-1.png" alt="représentation des coordonnées" style="width:500px;"> */
	SQUARE(),
	/** Interprétation des coordonnées comme décrivant des cases hexagonales.
	 * <br> <img src="./doc-files/Paradigm-2.png" alt="représentation des coordonnées" style="width:500px;"> */
	HEXAGON();
	
	Paradigm() {
		// nothing here
	}
	
}