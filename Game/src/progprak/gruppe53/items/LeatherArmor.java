package progprak.gruppe53.items;

import progprak.gruppe53.game.Game;


public class LeatherArmor extends Armor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LeatherArmor(int x, int y,Game game) {
		super(x, y, "",game);
		armorLVL = 2;
		// TODO Auto-generated constructor stub
	}
	
	public LeatherArmor(Game game)
	{
		super(0,0,"",game);
		armorLVL = 2;
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
