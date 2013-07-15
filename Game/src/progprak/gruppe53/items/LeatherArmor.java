package progprak.gruppe53.items;

import progprak.gruppe53.game.GameLogic;


public class LeatherArmor extends Armor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LeatherArmor(int x, int y,GameLogic gameLogic) {
		super(x, y, "images/leatherArmorField",gameLogic);
		armorLVL = 2;
		// TODO Auto-generated constructor stub
	}
	
	public LeatherArmor(GameLogic gameLogic)
	{
		super(0,0,"",gameLogic);
		armorLVL = 2;
		blowResistance = 0.2;
		pierceResistance = 0.2;
		bluntResistance = 0.3;
		fireResistance = -0.2;
		arcaneResistance = 0;
	}

	@Override
	public void doLogic(long delta) {
		// TODO Auto-generated method stub
		
	}
	

}
