package core.game;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

import core.exceptions.*;

/** <code>Game</code> est une partie de jeu de Ghost à part entière.
 * Cette classe gère essentiellement le déroulement du jeu.
 * @see Level
 * @see GlobalViewer
 * @see PlayerControler
 */
public class Game {
	private Level level;
	private boolean started;
	private int turn;
	
	/** Crée une nouvelle partie de Ghost à partir d'un "terrain de jeu".
	 * @param board  le plateau et ses conditions de jeu
	 */
	public Game(StageBoard board) {
		if (board==null)
			throw new NullPointerException();
		
		this.started = false;
		this.turn = -1;
		this.level = new Level(board);
		
		List<Pawn> pawns = new ArrayList<Pawn>();
		for (int p=0;p<board.players;p++) {
			Player player = new Player(this);
			this.level.players.add(player);
			for (Soul s:board.initialSouls(p))
				pawns.add( new Ghost(s,player).invokePawn() );
			player.setInitialConfig(pawns,board.initialCases(p));
		}
		this.level.pawns.addAll(pawns);
	}
	
	public void subscribe(PlayerControler pc,int player) {
		this.level.players.get(player).setControler(pc);
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
		for(Player p : this.level.players)
			p.placePawns();
	}
	
	/** Exécute la boucle de jeu, tour par tour.
	 * @return  le joueur gagnant la partie, ou <code>null</code> s'il y a match nul
	 * @see #play()
	 */
	private Player playTurns() {
		Player winner = null;
		while (true) {
			for (Player p: this.level.players) {
				this.turn++;
				try {
					winner = this.playTurn(p);
					if (winner!=null)
						return winner;
				} catch (DrawException e) {
					return null;
				}
			}
			this.turn = -1;
		}
	}
	
	/** Exécute le tour d'un joueur.
	 * Vérifie les conditions de victoire avant.
	 * @param player  le joueur dont est exécuté le tour
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
	
	/** Renvoie le niveau utilisé dans cette partie de Ghost.
	 * @return  le niveau utilisé dans cette partie
	 */
	Level getLevel() {
		return this.level;
	}
	
	/** Renvoie <code>true</code> si le jeu a commencé.
	 * @return  <code>true</code> si le jeu a commencé
	 */
	public boolean started() {
		return this.started;
	}
	
	/** Renvoie l'ordre passage du joueur dont c'est le tour actuellement.
	 * @return  le joueur dont le tour est en train d'être joué, ou <code>-1</code> si le placement des pions n'est pas terminé.
	 */
	public int getTurn() {
		return this.turn;
	}
	
}