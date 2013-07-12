package progprak.gruppe53.items;

import progprak.gruppe53.game.Collidable;
import progprak.gruppe53.game.GameLogic;

public class HealthPotion extends Potion implements Collidable  {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Initializes HealthPotion, set coordinates 
	 * and the price if you want to sell it.
	 * @param HealthPotion:
	 */
	public HealthPotion(int x, int y,GameLogic gameLogic){
		super( x, y, "images/healthpot1.png",gameLogic);
		price = 25;
	}
	/**
	 * Initialization of the HealthPotion for the shop.
	 * Set the price if you want to buy it.
	 * @param gameLogic
	 */
	public HealthPotion(GameLogic gameLogic) {
		super(0,0,"images/healthpot1.png",gameLogic);
		price = 25;
	}

	@Override
	public void doLogic(long delta){
		
	}
	@Override
	// addHealth sets the value of Health you get if you use the HealthPotion
	public void use() {
		super.use();
		gameLogic.getHero().addHealth(20);
	}

}
