package progprak.gruppe53.items;

import progprak.gruppe53.game.GameLogic;


public class ChainArmor extends Armor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ChainArmor(int x, int y,GameLogic gameLogic) {
		super(x, y, "images/chainArmorField.png",gameLogic);
		armorLVL = 3;
		// TODO Auto-generated constructor stub
	}
	
	public ChainArmor(GameLogic gameLogic)
	{
		super(0,0,"",gameLogic);
		armorLVL = 3;
		blowResistance = 0.3;
		pierceResistance = 0.4;
		bluntResistance = 0.5;
		fireResistance = -0.5;
		arcaneResistance = -0.4;
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
