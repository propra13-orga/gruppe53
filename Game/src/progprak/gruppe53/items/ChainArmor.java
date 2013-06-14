package progprak.gruppe53.items;

import progprak.gruppe53.game.Game;


public class ChainArmor extends Armor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ChainArmor(int x, int y,Game game) {
		super(x, y, "",game);
		armorLVL = 3;
		// TODO Auto-generated constructor stub
	}
	
	public ChainArmor(Game game)
	{
		super(0,0,"",game);
		armorLVL = 3;
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
