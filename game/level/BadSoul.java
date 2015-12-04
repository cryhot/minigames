//Louis

package game.level;

public class BadSoul extends Soul{
	
	public BadSoul(){
		
		activVictory = false;
		passivVictory = true; // cest un mauvais fantome il est concentré sur le mangeage des autres fantomes
		canEat = true;
	}

}