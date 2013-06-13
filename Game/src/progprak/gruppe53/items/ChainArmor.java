package progprak.gruppe53.items;


public class ChainArmor extends Armor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ChainArmor(int x, int y) {
		super(x, y, "");
		armorLVL = 3;
		// TODO Auto-generated constructor stub
	}
	
	public ChainArmor()
	{
		super(0,0,"");
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
