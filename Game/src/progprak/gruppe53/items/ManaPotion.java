package progprak.gruppe53.items;

import progprak.gruppe53.game.Collidable;
import progprak.gruppe53.game.Game;

public class ManaPotion extends Potion implements Collidable  {
	
	private static final long serialVersionUID = 1L;
	
	
	public ManaPotion(int x, int y,Game game){
		super( x, y, "images/manapot1.png", game);
		price = 25;
	}
	public ManaPotion(Game game){
		super(0,0,"images/manapot1.png",game);
		price = 25;
	}
	
	@Override
	public void doLogic(long delta){
		
	} 
	@Override
	public void use() {
		super.use();
		game.getGameLogic().getHero().drainMana(-250);
	}

}
