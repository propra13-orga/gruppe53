package progprak.gruppe53.items;

import progprak.gruppe53.game.GameLogic;


public class LeatherArmor extends Armor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LeatherArmor(int x, int y,GameLogic gameLogic) {
		super(x, y, "",gameLogic);
		armorLVL = 2;
		// TODO Auto-generated constructor stub
	}
	
	public LeatherArmor(GameLogic gameLogic)
	{
		super(0,0,"",gameLogic);
		armorLVL = 2;
	}

	@Override
	public void doLogic(long delta) {
		// TODO Auto-generated method stub
		
	}
	

}
