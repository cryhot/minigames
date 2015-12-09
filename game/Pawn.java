//Louis
package game;

import board.Case;

public final class Pawn {
	
	public final Ghost ghost;
	Case location;
	private Level level;
	
	private Pawn() { //!\\ A MODIFIER ! CONSTRUCTEUR POUR LES TESTS
		this.ghost = null;
	}
	
	public Case getCase(){
		return this.location;
	}
	
	public Level getLevel(){
		return this.level;
	}
	
	boolean canMove(Case c) { /* EN MAJUSCULES ET ENTRE "*", LES COMMENTAIRES SUR LES TRUCS À CHANGER */
		if(!c.isInside()) // destination hors plateau
			return false;
		/* ATTENTION ! REGARDE LA DESCRIPTION DE Case.isEmpty() ! REGARDE PLUTOT Level.getPawnAt(Case c)... */
		if(!c.isEmpty() && this.ghost.player.equals(level.getPawnAt(c).ghost.player)) // le pion essaie de capturer un allié
			return false;
		/* NE COMPILE PAS : "canEat" ??
		   IL Y A AUSSI UN PEU DE CODE REDONDANT, RIEN DE GRAVE : "!c.isEmpty()" À L'ETAT ACTUEL */
		if(!c.isEmpty() && !this.ghost.soul.canEat) // le pion ne peut pas capturer d'autre pion
			return false;
		/* TU PEUX FAIRE UNE METHODE INTERMEDIAIRE DANS Pawn MEME
		   		private boolean validateMove(Case c)
		   QUI REPREND LE CODE ( this.ghost.soul. ... .validate(this.location,c) ) */
		if(!this.ghost.soul.mobility.getMoveTable( this.level.board.paradigm ).validate( this.location,c )) // mouvement invalide
			return false;
		/* NE COMPILE PAS : "isEscape" ??
		   ON DEVRAIT TESTER CETTE CONDITION ( FACILE A CALULER ) AVANT D'AUTRES ( PLUS COMPLIQUEES A CALCULER )
		   PENSE BIEN QUE LA SORTIE DOIT ETRE ASSIGNEE AU JOUEUR POUR QU'ELLE FONCTIONNE
			 cf. LA VARIABLE index DANS Player QUI DEFINIT LE NUMERO DU JOUEUR */
		if(c.isEscape && !this.ghost.soul.activVictory) // le pion essaie à tort d'aller sur une case gagnante
			return false;
		return true;
	}
	
	public void capture(Case c){ /* METHODE PUBLIQUE ? CA VEUT DIRE QUE N'IMPORTE QUI PEUT CAPTURER DES PIONS */
		// EN FAITE AUTANT FAIRE UNE METHODE POUR CAPTURER this
		level.getPawnAt(c).location = null;
		level.capturPawns.add(level.getPawnAt(c)); //inutile maintenant
		
	}
	
	void move(Pawn p, Case c) { /* POURQUOI PAWN P ? LE PION QUE L'ON BOUGE EST this */
		/* NE COMPILE PAS : "canMove" ?? */
		if(!canMove)
			/* ON DEVRAIT PLUTOT JETTER UNE EXCEPTION DU TYPE RunTimeException, QUI PERMET DE COMPILER */
			throw(new Exception ("La case n'est pas accessible par ce pion !"));
		else{
			if(this.ghost.player.equals(level.getPawnAt(c).ghost.player)) // level.getPawnAt(c) RENVOIE SOUVENT null !
				capture(c); // A MODIFIER ?
			this.location = c;
		}
	}	
	
	
	@Override
	public boolean equals(Object o) {
		if (!this.getClass().equals(o.getClass()))
			return false;
		return this.ghost.equals(((Pawn)o).ghost);
	}
	
	@Override
	public int hashCode() {
		return this.ghost.hashCode();
	}
}

