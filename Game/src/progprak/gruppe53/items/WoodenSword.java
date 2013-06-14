package progprak.gruppe53.items;

import progprak.gruppe53.game.CollisionEvent;
import progprak.gruppe53.game.DamageCollisionEvent;
import progprak.gruppe53.game.Game;

public class WoodenSword extends MeleeWeapons {

	private static final long serialVersionUID = 123456789L;





	public WoodenSword(int x, int y,Game game){
		super( x, y, "images/sword.png", game);
		
		doInitalizations();
	}
	public WoodenSword(Game game){
		super(0,0, "images/sword.png", game);
		collisionEvent = new CollisionEvent(CollisionEvent.EVENT_NOTHING,this);
		draw = false;
		doInitalizations();
	}
	private void doInitalizations(){
		price = 50;
		ww = 16;
		wh = 7;
		xOffset = 26;
		yOffset = 22;
		notAttackCe = collisionEvent;
		damage = 1;
	}
	@Override
	public void doLogic(long delta){
		
	}

}
