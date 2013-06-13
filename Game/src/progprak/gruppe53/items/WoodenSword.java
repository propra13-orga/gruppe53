package progprak.gruppe53.items;

import progprak.gruppe53.game.CollisionEvent;
import progprak.gruppe53.game.DamageCollisionEvent;

public class WoodenSword extends Weapon {

	private static final long serialVersionUID = 123456789L;

	private CollisionEvent notAttackCe;
	private CollisionEvent attackCe;
	
	public WoodenSword(int x, int y){
		super( x, y, "images/sword.png");
		
		doInitalizations();
	}
	public WoodenSword(){
		super(0,0, "images/sword.png");
		collisionEvent = new CollisionEvent(CollisionEvent.EVENT_NOTHING);
		draw = false;
		doInitalizations();
	}
	private void doInitalizations(){
		price = 50;
		ww = 16;
		wh = 7;
		notAttackCe = collisionEvent;
		attackCe = new DamageCollisionEvent(1, 1);
	}
	@Override
	public void doLogic(long delta){
		
	}
	@Override
	public void attack(boolean attack) {
		if(attack){
			x = owner.getX()+26;
			y = owner.getY()+22; 
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
