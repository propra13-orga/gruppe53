package progprak.gruppe53.game;

abstract public class Weapon extends Item implements Collidable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected int damage;
	
	protected int ww = 0;
	protected int wh = 0;
	
	public Weapon(int x, int y, String imagePath) {
		super(x, y, imagePath);
		// TODO Auto-generated constructor stub
	}

	public int getdamage(){
		return damage;
	}
	abstract public void attack(boolean attack);
	
}
