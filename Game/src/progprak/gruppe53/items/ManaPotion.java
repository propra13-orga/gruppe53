package progprak.gruppe53.items;

import progprak.gruppe53.game.Collidable;
import progprak.gruppe53.game.GameLogic;

public class ManaPotion extends Potion implements Collidable  {
	
	private static final long serialVersionUID = 1L;
	
	/*
	 * Initializes ManaPotion, set coordinates 
	 * and the price if you want to sell it.
	 */
	public ManaPotion(int x, int y,GameLogic gameLogic){
		super( x, y, "images/manapot1.png", gameLogic);
		price = 25;
	}
	/**
	 * Initialization of the ManaPotion for the shop.
	 * Set the price if you want to buy it.
	 * @param gameLogic
	 */
	public ManaPotion(GameLogic gameLogic){
		super(0,0,"images/manapot1.png",gameLogic);
		price = 25;
	}
	
	@Override
	public void doLogic(long delta){
		
	} 
	@Override
	//drainMana sets the value of Mana you get if you use the ManaPotion
	public void use() {
		super.use();
		gameLogic.getHero().drainMana(-250);
	}

}
