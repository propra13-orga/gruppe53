package progprak.gruppe53.game;

public class Sword extends Weapon {

	private static final long serialVersionUID = 123456789L;

	private CollisionEvent notAttackCe;
	private CollisionEvent attackCe;
	
	public Sword(int x, int y){
		super( x, y, "images/sword1.png");
		doInitalizations();
	}
	public Sword(){
		super(0,0, "images/sword1.png");
		draw = false;
		doInitalizations();
	}
	private void doInitalizations(){
		ww = wh = 20;
		notAttackCe = collisionEvent;
		attackCe = new DamageCollisionEvent(1, 1);
	}
	@Override
	public void doLogic(long delta){
		
	}
	@Override
	public void attack(boolean attack) {
		if(attack){
			x = owner.getX()+20;
			y = owner.getY(); 
			width = ww;
			height = wh;
			draw = true;
			collisionEvent = attackCe;
		}
		else {
			width = height = x = y = 0;
			draw = false;
			collisionEvent = notAttackCe;
		}
	}
	
}
