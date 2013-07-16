package progprak.gruppe53.items;

import progprak.gruppe53.game.CollisionEvent;
import progprak.gruppe53.game.GameLogic;

public class CrossBow extends Weapon{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4274376584235570942L;
	

	public CrossBow(int x, int y,GameLogic gameLogic) {
		super(x, y, "", gameLogic);
		damageType = 2;
		doInitalizations();

	}
	
	public CrossBow(GameLogic gameLogic){
		super(0,0, "", gameLogic);
		collisionEvent = new CollisionEvent(CollisionEvent.EVENT_NOTHING, this);
		draw = false;
		doInitalizations();
	}
	
	private void doInitalizations(){
		price = 100;
	}

	@Override
	public void doLogic(long delta) {

		
	}


}
