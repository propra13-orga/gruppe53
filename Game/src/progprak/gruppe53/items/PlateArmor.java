package progprak.gruppe53.items;


public class PlateArmor extends Armor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PlateArmor(int x, int y) {
		super(x, y, "");
		armorLVL = 4;
		// TODO Auto-generated constructor stub
	}
	
	public PlateArmor()
	{
		super(0,0,"");
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
