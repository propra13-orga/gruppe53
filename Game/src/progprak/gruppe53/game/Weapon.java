package progprak.gruppe53.game;

abstract public class Weapon extends Items implements Collidable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected int damage;
	
	public Weapon(int x, int y, String imagePath) {
		super(x, y, imagePath);
		// TODO Auto-generated constructor stub
	}

	public int getdamage(){
		return damage;
	}
	
}
