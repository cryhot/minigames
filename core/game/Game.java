package core.game;

import core.exceptions.*;

public class Game {
	private Level level;
	private boolean started;
	
	public Game(Level l) {
		this.started = false;
		this.level = l;
	}
	
	public void subscribe(Player p) { // DEL ?
		if(this.started)
			throw new GameStateException("le jeu à déjà commencé");
		if(p==null)
			throw new NullPointerException();
		if(this.level.players.contains(p))
			throw new IllegalArgumentException("joueur déjà inscrit");
		this.level.players.add(p);
	}
	
	/** lance la partie de Ghost.
	 * @return  le joueur gagnant la partie, ou <code>null</code> s'il y a match nul
	 */
	public Player play() {
		if(this.started)
			throw new GameStateException("le jeu a déjà commencé");
		this.started = true;
		initialize();
		return playTurns();
	}
	
	/** Avertit tous les joueurs pour placer leurs pions.
	 * @see #play()
	 */
	private void initialize() {
		// for(Player p : this.level.players)
			// p.placePawns(); // à compléter en temps voulu
	}
	
	/** Exécute la boucle de jeu, tour par tour.
	 * @return  le joueur gagnant la partie, ou <code>null</code> s'il y a match nul
	 * @see #play()
	 */
	private Player playTurns() {
		Player winner = null;
		while (true)
			for (Player p: this.level.players) {
				try {
					winner = this.playTurn(p);
					if (winner!=null)
						return winner;
				} catch (DrawException e) {
					return null;
				}
			}
	}
	
	/** Exécute le tour d'un joueur.
	 * Vérifie les conditions de victoire avant.
	 * @return  le joueur gagnant au début de ce tour s'il existe, <code>null</code> sinon
	 * @throws DrawException  s'il y a match nul.
	 * @see #play()
	 */
	private Player playTurn(Player player) throws DrawException {
		Player winnerByDefeat = this.checkDefeat();
		if (winnerByDefeat!=null)
			return winnerByDefeat;
				
		for(Player p : this.level.players)
			if(p.checkPassiveVictory())
				return p;
		
		if(player.checkActiveVictory())
			return player;
		
		player.playTurn();
		
		return null;
	}
	
	/** Vérifie la condition de victoire par élimination des adversaires.
	 * Celle-ci est validée s'il ne reste plus qu'un seul joueur n'ayant pas perdu.
	 * @return  le joueur n'ayant pas encore perdu, s'il est unique, <code>null</code> sinon
	 * @throws DrawException  si tous les joueurs ont perdu
	 * @see #play()
	 */
	private Player checkDefeat() throws DrawException {
		Player winner = null;
		for(Player p : this.level.players)
			if(!p.checkDefeat()) {
				if(winner==null || winner.equals(p))
					winner = p;
				else
					return null; // au moins 2 joueurs en jeu
			}
		if (winner==null) // tout le monde a perdu
			throw new DrawException();
		return winner; // un seul joueur encore en jeu
	}
	
	Level getLevel() {
		return this.level;
	}
	
}