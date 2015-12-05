//JR
package game.level.board;

/**
 * Une <code>Case</code> représente une case d'un {@link game.level.board.Board plateau}.
 * @see game.level.board.Board
 */
public final class Case implements Comparable<Case> {
	/** Le plateau auquel se réfère cette case */
	public final Board board;
	public final int x;
	public final int y;
	
	/**
	 * Construit une case d'un plateau en fonction de ses coordonnées.
	 * @param board  le case auquel la case se réfère
	 * @param x  la première coordonnée
	 * @param y  la deuxième coordonnée
	 * @throws NullPointerException  si le plateau fourni est <code>null</code>
	 */
	Case(Board board, int x, int y) {
		if (board==null)
			throw new NullPointerException();
		this.board = board;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Renvoie un entier symbolisant le contenu de cette case.
	 * @return  le contenu de la case
	 * @see game.level.board.Board#getContent(int,int)
	 */
	private int getContent() {
		return this.board.getContent(this.x,this.y);
	}
	
	/**
	 * Renvoie <code>true</code> si cette case est dans le plateau.
	 * Une case est considérée comme dans le plateau si elle n'est pas hors-plateau.
	 * @return  <code>true</code> si le code de cette case est négatif
	 * @see game.level.board.Board#getContent(int,int)
	 */
	public boolean isInside() {
		return this.getContent()>=0;
	}
	
	/**
	 * Renvoie <code>true</code> si cette case est une case praticable normale.
	 * Une case est considérée comme praticable si elle est dans le plateau et qu'elle n'est pas une case de sortie.
	 * @return  <code>true</code> si le code de cette case est <code>0</code>
	 * @see game.level.board.Board#getContent(int,int)
	 */
	public boolean isEmpty() {
		return this.getContent()==0;
	}
	
	/**
	 * Renvoie <code>true</code> si cette case est une case de sortie.
	 * Une case est considérée comme une sortie si elle permet la victoire au joueur arrivant à y placer un pion.
	 * @return  <code>true</code> si le code de cette case est strictement positif
	 * @see game.level.board.Board#getContent(int,int)
	 */
	public boolean isEscape() {
		return this.getContent()>0;
	}
	
	/**
	 * Renvoie <code>true</code> si cette case est une case de sortie pour le joueur d'index précisé.
	 * Une case est considérée comme une sortie pour ce joueur si elle permet la victoire à celui-ci si il parvient à y placer un de ses pions. <br><br>
	 * Attention, les index de joueur commencent à <code>0</code>, alors que les index des cases de sorties sont numérotées à partir de <code>1</code>.
	 * Cette fonction gère le décalage d'index entre joueur et case de sortie.
	 * @param player  l'index du joueur spécifié
	 * @return  <code>true</code> si le code de cette case est égal à l'index du joueur + <code>1</code>
	 * 	ou si l'index spécifié n'est pas valide pour un index de joueur
	 * @see game.level.board.Board#getContent(int,int)
	 */
	public boolean isEscape(int player) {
		if (!this.isEscape())
			return false;
		return this.getContent()==player+1;
	}
	
	/**
	 * Teste l'égalité l'objet spécifié avec cette case.
	 * Deux case sont considérées comme égales si elles appartiennent au même {@link #board plateau} et qu'elles ont les mêmes coordonnées.
	 * @param o  l'objet à comparer par égalité avec cette case
	 * @return  <code>true</code> si l'objet spécifié est égal à cette case
	 */
	@Override
	public boolean equals(Object o) {
		if(!this.getClass().equals(o.getClass()))
			return false;
		Case c = (Case) o;
		return this.x==c.x && this.y==c.y && this.board==c.board;
	}
	
	@Override
	public int hashCode() {
		return this.x*11+this.y*13;
	}
	
	/**
	 * Compare l'objet spécifié avec cette case.
	 * La comparaison se fait par ordre lexicographique, sur le {#board plateau}, sur les abscisses puis sur les ordonnées.
	 * @return  un entier négatif, nul ou positif si cette case est respectivement plus petit, égal ou plus grand que l'objet spécifié
	 */
	@Override
	public int compareTo(Case c) {
		int diff = this.board.hashCode()-c.board.hashCode();
		if (diff!=0)
			return diff<0?-1:1;
		if (this.x!=c.x)
			return this.x<c.x?-1:1;
		if (this.y!=c.y)
			return this.y<c.y?-1:1;
		return 0;
	}
	
}