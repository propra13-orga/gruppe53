package progprak.gruppe53.items;

import progprak.gruppe53.game.CollisionEvent;
import progprak.gruppe53.game.Game;

public class PinkGlitterWand extends RangeWeapon{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4274376584235570942L;
	

	public PinkGlitterWand(int x, int y,Game game) {
		super(x, y, "images/pinkwand.png", game);
		this.game = game;
		
		doInitalizations();

	}
	
	public PinkGlitterWand(Game game){
		super(0,0, "images/pinkwand.png", game);
		collisionEvent = new CollisionEvent(CollisionEvent.EVENT_NOTHING,this);
		draw = false;
		doInitalizations();
	}
	
	private void doInitalizations(){
		price = 100;
		manaCost = 50;
		ww = 16;
		wh = 7;
		xOffset = 11;
		yOffset = 7;
		recharge = (long) ((4L)*1e8);
	}

	@Override
	public void doLogic(long delta) {

		
	}


}