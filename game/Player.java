//Louis
package game;

import java.util.*;

public class Player {
	public final Level level; // j'ai ajouté ces 2 variables *COMMENTAIRE A SUPPRIMER*
	public final int index;
	// FAIRE UN CONSTRUCTEUR
	private Player(Error nimportequoi){this.level=null;this.index=-1;} // CONSTRUCTEUR BIDON POUR QUE LE PROGRAMME COMPILE !
	
	
	
	
	public Set<Pawn> getPawnsOfThisPlayer(){ //renvoie tous les pions de ce joueur
		
		Set<Pawn> pawnsOfThisPlayer = new Set<Pawn> ();
		
		for(Pawn p : this.level.getPawns()){
			if(this.equals(p.ghost.player){
				pawnsOfThisPlayer.add(p);
			}
		}
		
		return pawnsOfThisPlayer;
	}
	
	
	
	/**
	Soit je mets un attribut et une méthode :   --> EVITE DE METTRE UN AUTRE ATTRIBUT
	private Set<Pawn> pawnsOfThisPlayer;
	
	//méthode qui permet de trouver à partir du level tous les pions qui € à ce player
	public void getPawnsOfThisPlayer(Level l){
		
	}
	
	Soit il y a seulement une méthode qui retourne un Set<Pawn>    --> OUEP !
	public Set<Pawn> getPawnsOfThisPlayer(Level l){                --> DUCOUP VU QU'Y A UN ATTRIBUT level, "Level l" EST PLUS NECESSAIRE
		Set<Pawn> pawnsOfThisPlayer = new Set<Pawn>();
		....                                                         --> UNE PETITE BOUCLE for (Player p:...) ?  :)
		return pawnsOfThisPlayer;
	}
	*/
	// en fait on peut rajouter une méthode publique dans Level :
	// 		public Set<Pawn> getPawns(Player)
	// 		public Set<Pawn> getPawns() // qui renvoie TOUS les joueurs sans exception
	// 		public Set<Pawn> getPawns(Constraint c)
	// 	La dernière est plus générique mais implique une nouvelle interface Constraint
	//  avec une méthode : public boolean keep(Pawn p)
	//  pour prendre un élément ou non dans le Set<Pawn> retourné par getPawns
}