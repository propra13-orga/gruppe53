package progprak.gruppe53.items;

import progprak.gruppe53.game.Game;
import progprak.gruppe53.game.GameLogic;


public class PlateArmor extends Armor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PlateArmor(int x, int y,GameLogic gameLogic) {
		super(x, y, "",gameLogic);
		armorLVL = 4;
		// TODO Auto-generated constructor stub
	}
	
	public PlateArmor(GameLogic gameLogic)
	{
		super(0,0,"",gameLogic);
		armorLVL = 4;
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
