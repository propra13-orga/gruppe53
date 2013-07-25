package progprak.gruppe53.items.armor;

import progprak.gruppe53.game.GameLogic;


public class ClothArmor extends Armor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClothArmor(int x, int y,GameLogic gameLogic) {
		super(x, y, "images/clothArmorField.png",gameLogic);
		armorLVL = 1;
		blowResistance = 0.1;
		pierceResistance = 0.1;
		bluntResistance = 0.2;
		fireResistance = -0.3;
		arcaneResistance = -0.1;
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
	
	
	
	

}
