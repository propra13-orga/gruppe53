package progprak.gruppe53.items;

import progprak.gruppe53.game.Game;


abstract public class Armor extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected int armorLVL;

	public Armor(int x, int y, String imagePath,Game game) {
		super(x, y, imagePath, game);
		// TODO Auto-generated constructor stub
	}
	
	public int getarmorLVL(){
		
		return armorLVL;
	}
}
