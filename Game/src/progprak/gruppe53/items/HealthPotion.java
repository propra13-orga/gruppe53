package progprak.gruppe53.items;

import progprak.gruppe53.game.Collidable;
import progprak.gruppe53.game.GameLogic;

public class HealthPotion extends Potion implements Collidable  {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @param x and y require the coordinates to set the Potion.
	 * @param gameLogic Requires the gameLogic.
	 * Initializes the HealthPotion you find on the field.
	 */
	public HealthPotion(int x, int y,GameLogic gameLogic){
		super( x, y, "images/healthpot1.png",gameLogic);
		price = 25;
	}
	/**
	 * 
	 * @param gameLogic Requires the gameLogic.
	 * Initializes the Healthpotion you can buy in the shop and set the price to 25.
	 */
	public HealthPotion(GameLogic gameLogic) {
		super(0,0,"images/healthpot1.png",gameLogic);
		price = 25;
	}

	@Override
	public void doLogic(long delta){
		
	}
	@Override
	// addHealth set the gain of Health if you use a HealthPotion.
	public void use() {
		super.use();
		gameLogic.getHero().addHealth(20);
	}

}
