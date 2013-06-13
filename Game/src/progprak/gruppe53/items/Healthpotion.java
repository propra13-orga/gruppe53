package progprak.gruppe53.items;

import progprak.gruppe53.game.Collidable;

public class Healthpotion extends Potion implements Collidable  {
	
	private static final long serialVersionUID = 1L;
	
	
	public Healthpotion(int x, int y){
		super( x, y, "");
	}
	
	@Override
	public void doLogic(long delta){
		
	} 

}
