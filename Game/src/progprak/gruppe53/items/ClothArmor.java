package progprak.gruppe53.items;


public class ClothArmor extends Armor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClothArmor(int x, int y) {
		super(x, y, "");
		armorLVL = 1;
		// TODO Auto-generated constructor stub
	}
	
	public ClothArmor()
	{
		super(0,0,"");
		armorLVL = 1;
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
