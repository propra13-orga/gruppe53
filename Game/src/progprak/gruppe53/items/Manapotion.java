package progprak.gruppe53.items;

import progprak.gruppe53.game.Collidable;

public class Manapotion extends Potion implements Collidable  {
	
	private static final long serialVersionUID = 1L;
	
	
	public Manapotion(int x, int y){
		super( x, y, "");
	}
	
	@Override
	public void doLogic(long delta){
		
	} 

}
