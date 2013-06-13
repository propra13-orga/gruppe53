package progprak.gruppe53.items;


public class LeatherArmor extends Armor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LeatherArmor(int x, int y) {
		super(x, y, "");
		armorLVL = 2;
		// TODO Auto-generated constructor stub
	}
	
	public LeatherArmor()
	{
		super(0,0,"");
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
