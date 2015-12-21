package core.game;




public class Game {
	
	private Level level;
	private boolean started;
	
	public Game(Level l){
		this.started = false;
		this.level = l;
	}
	
	Level getLevel(){
		return this.level;
	}
	
	public subscribe(Player p){
		
		if(this.started){
			throw new RuntimeException();
		}
		this.level.players.add(p);
	}
	
	private void initialise(Player p){
		
		for(Player p : this.level.players){
			p.placePawns;		
		}
	}
	
	public Player checkDefeat(){
		
		Player winner = null;
		for(Player p : this.level.players){
				if(!p.checkDefeat()){
					
					if(winner==null){
						winner = p;
					}
					else {
						return null;
					}
				}
		}
		
		return winner;
		
	}
	
	
	private Player playTurn(Player player){
				
		for(Player p : this.level.players){
			if(p.checkPassiveVictory()){
				return p;
		}
					
		if(player.checkActiveVictory()){
			return player;
		}
		
		Player winnerByDefeat = checkDefeat();
		if(winnerByDeafeat!=null){
			return winnerByDeafeat;
		}
			
		player.playTurn();
			
		return null;
	}
	
	
	
	
	private Player turns(){
		Player winner = null;
		while (true){
			for (Player p: this.level.players) {
				winner = this.playTurn(p);
				if (winner!=null)
					return winner;
			}
		}
		return null;
	}	
	
	
		
	public Player play(){
		
		if(this.started){
			throw new RuntimeException();
		}
		this.started = true;
		this.initialise();
		return turns();
		
	}

	
	
}