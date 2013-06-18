package progprak.gruppe53.items;

import progprak.gruppe53.game.Game;
import progprak.gruppe53.game.GameLogic;


public class ClothArmor extends Armor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClothArmor(int x, int y,GameLogic gameLogic) {
		super(x, y, "images/clothArmor.png",gameLogic);
		armorLVL = 1;
		// TODO Auto-generated constructor stub
	}
	
	public ClothArmor(GameLogic gameLogic)
	{
		super(0,0,"images/clothArmor.png",gameLogic);
		armorLVL = 1;
	}

	@Override
	public void doLogic(long delta) {
		// TODO Auto-generated method stub
		
	}
	
	
	public int isarmorLVL()
	{
		return armorLVL;
	}

}
