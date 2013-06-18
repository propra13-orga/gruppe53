package progprak.gruppe53.items;

import progprak.gruppe53.game.Collidable;
import progprak.gruppe53.game.Game;
import progprak.gruppe53.game.GameLogic;

public class HealthPotion extends Potion implements Collidable  {
	
	private static final long serialVersionUID = 1L;
	
	
	public HealthPotion(int x, int y,GameLogic gameLogic){
		super( x, y, "images/healthpot1.png",gameLogic);
		price = 25;
	}
	
	public HealthPotion(GameLogic gameLogic) {
		super(0,0,"images/healthpot1.png",gameLogic);
		price = 25;
	}

	@Override
	public void doLogic(long delta){
		
	}
	@Override
	public void use() {
		super.use();
		gameLogic.getHero().addHealth(20);
	}

}
