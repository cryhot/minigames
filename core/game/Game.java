package core.game;

import core.exceptions.*;

public class Game {
	private Level level;
	private boolean started;
	
	public Game(Level l) {
		this.started = false;
		this.level = l;
	}
	
	public void subscribe(Player p) {
		if(this.started)
			throw new GameStateException("le jeu à déjà commencé");
		if(p==null)
			throw new NullPointerException();
		if(this.level.players.contains(p))
			throw new IllegalArgumentException("joueur déjà inscrit");
		this.level.players.add(p);
	}
	
	public Player play() {
		if(this.started)
			throw new GameStateException("le jeu a déjà commencé");
		this.started = true;
		initialize();
		return playTurns();
	}
	
	private void initialize() {
		// for(Player p : this.level.players)
			// p.placePawns(); // à compléter en temps voulu
	}
	
	private Player playTurns() {
		Player winner = null;
		while (true)
			for (Player p: this.level.players) {
				winner = this.playTurn(p);
				if (winner!=null)
					return winner;
			}
	}
	
	private Player playTurn(Player player) {
		
		try {
			Player winnerByDefeat = this.checkDefeat();
			if (winnerByDefeat!=null)
				return winnerByDefeat;
		} catch (DrawException e) {
			return null;
		}
		
		for(Player p : this.level.players)
			if(p.checkPassiveVictory())
				return p;
		
		if(player.checkActiveVictory())
			return player;
		
		player.playTurn();
		
		return null;
	}
	
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