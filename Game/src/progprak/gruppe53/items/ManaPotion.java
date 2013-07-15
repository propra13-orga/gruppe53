package progprak.gruppe53.items;

import progprak.gruppe53.game.Collidable;
import progprak.gruppe53.game.GameLogic;

public class ManaPotion extends Potion implements Collidable  {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @param x and y require coordinates to set the Potion.
	 * @param gameLogic Requires the gameLogic.
	 * Initializes the ManaPotion you can find on the field
	 */
	public ManaPotion(int x, int y,GameLogic gameLogic){
		super( x, y, "images/manapot1.png", gameLogic);
		price = 25;
	}
	/**
	 * 
	 * @param gameLogic Requires the gameLogic.
	 * Initializes the ManaPotion you can buy in the shop and set the price to 25.
	 */
	public ManaPotion(GameLogic gameLogic){
		super(0,0,"images/manapot1.png",gameLogic);
		price = 25;
	}
	
	@Override
	public void doLogic(long delta){
		
	} 
	@Override
	// Set the gain of Mana if you use a ManaPotion
	public void use() {
		super.use();
		gameLogic.getHero().drainMana(-250);
	}

}
