package progprak.gruppe53.game;

abstract public class Armor extends Items {

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
