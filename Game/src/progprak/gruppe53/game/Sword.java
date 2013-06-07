package progprak.gruppe53.game;

public class Sword extends Weapon implements Collidable {

	private static final long serialVersionUID = 123456789L;

	
	public Sword(int x, int y){
		super( x, y, "images/sword1.png");
		damage = 1;
	}
	
	@Override
	public void doLogic(long delta){
		
	}
	
}
