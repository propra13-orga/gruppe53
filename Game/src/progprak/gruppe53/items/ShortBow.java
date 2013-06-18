package progprak.gruppe53.items;

import progprak.gruppe53.game.CollisionEvent;
import progprak.gruppe53.game.Game;
import progprak.gruppe53.game.GameLogic;

public class ShortBow extends RangeWeapon{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4274376584235570942L;
	

	public ShortBow(int x, int y,GameLogic gameLogic) {
		super(x, y, "", gameLogic);
		
		doInitalizations();

	}
	
	public ShortBow(GameLogic gameLogic){
		super(0,0, "", gameLogic);
		collisionEvent = new CollisionEvent(CollisionEvent.EVENT_NOTHING,this);
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
