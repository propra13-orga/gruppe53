package progprak.gruppe53.items;

import progprak.gruppe53.game.Game;
import progprak.gruppe53.game.GameLogic;


abstract public class Armor extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected int armorLVL;

	public Armor(int x, int y, String imagePath,GameLogic gameLogic) {
		super(x, y, imagePath, gameLogic);
		// TODO Auto-generated constructor stub
	}
	
	public int getarmorLVL(){
		
		return armorLVL;
	}
}
