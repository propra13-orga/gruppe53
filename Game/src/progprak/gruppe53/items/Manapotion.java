package progprak.gruppe53.items;

import progprak.gruppe53.game.Collidable;
import progprak.gruppe53.game.Game;

public class Manapotion extends Potion implements Collidable  {
	
	private static final long serialVersionUID = 1L;
	
	
	public Manapotion(int x, int y,Game game){
		super( x, y, "", game);
	}
	
	@Override
	public void doLogic(long delta){
		
	} 

}
