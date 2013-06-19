package progprak.gruppe53.items;

import progprak.gruppe53.game.Collidable;
import progprak.gruppe53.game.GameLogic;

public class ManaPotion extends Potion implements Collidable  {
	
	private static final long serialVersionUID = 1L;
	
	
	public ManaPotion(int x, int y,GameLogic gameLogic){
		super( x, y, "images/manapot1.png", gameLogic);
		price = 25;
	}
	public ManaPotion(GameLogic gameLogic){
		super(0,0,"images/manapot1.png",gameLogic);
		price = 25;
	}
	
	@Override
	public void doLogic(long delta){
		
	} 
	@Override
	public void use() {
		super.use();
		gameLogic.getHero().drainMana(-250);
	}

}
