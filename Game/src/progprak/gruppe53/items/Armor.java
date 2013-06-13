package progprak.gruppe53.items;


abstract public class Armor extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected int armorLVL;

	public Armor(int x, int y, String imagePath) {
		super(x, y, imagePath);
		// TODO Auto-generated constructor stub
	}
	
	public int getarmorLVL(){
		
		return armorLVL;
	}
}
