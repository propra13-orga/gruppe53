package progprak.gruppe53.items;

import progprak.gruppe53.game.Collidable;
import progprak.gruppe53.game.Game;

public class HealthPotion extends Potion implements Collidable  {
	
	private static final long serialVersionUID = 1L;
	
	
	public HealthPotion(int x, int y,Game game){
		super( x, y, "images/healthpot1.png",game);
	}
	
	@Override
	public void doLogic(long delta){
		
	}
	@Override
	public void use() {
		super.use();
		game.getGameLogic().getHero().addHealth(20);
	}

}
