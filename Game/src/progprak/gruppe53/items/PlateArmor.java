package progprak.gruppe53.items;

import progprak.gruppe53.game.Game;


public class PlateArmor extends Armor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PlateArmor(int x, int y,Game game) {
		super(x, y, "",game);
		armorLVL = 4;
		// TODO Auto-generated constructor stub
	}
	
	public PlateArmor(Game game)
	{
		super(0,0,"",game);
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
