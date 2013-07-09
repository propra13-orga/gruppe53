package progprak.gruppe53.items;

import progprak.gruppe53.game.CollisionEvent;
import progprak.gruppe53.game.GameLogic;

public class StoneSword extends MeleeWeapons {

	private static final long serialVersionUID = 123456789L;

	
	public StoneSword(int x, int y,GameLogic gameLogic){
		super( x, y, "images/sword.png", gameLogic);
		
		doInitalizations();
	}
	public StoneSword(GameLogic gameLogic){
		super(0,0, "images/sword.png", gameLogic);
		collisionEvent = new CollisionEvent(CollisionEvent.EVENT_NOTHING,this);
		draw = false;
		doInitalizations();
	}
	private void doInitalizations(){
		price = 50;
		ww = 16;
		wh = 7;
		xOffset = 11;
		yOffset = 7;
		notAttackCe = collisionEvent;
		damage = 10;
	}
	@Override
	public void doLogic(long delta){
		
	}
}
