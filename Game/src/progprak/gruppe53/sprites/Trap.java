package progprak.gruppe53.sprites;

import progprak.gruppe53.game.Collidable;
import progprak.gruppe53.game.CollisionEvent;
import progprak.gruppe53.game.GameLogic;

public abstract class Trap extends CombatObject implements Collidable{
	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor of our Traps.
	 * @param String imagePath: Determines the images of Traps.
	 * @param GameLogic: Requires the GameLogic
	 */
	public Trap(int x, int y,String imagePath,GameLogic gameLogic){
		super(x,y,imagePath,gameLogic);
		faction = 2;
		doInitalizations();
	}

	@Override
	public void doLogic(long delta){
		
	}

	@Override
	// Determines the collisionEvent if you collide with a Trap.
	public CollisionEvent getCollisionEvent() {
		return collisionEvent;
	}
	@Override
	protected void handleCollisionEvent(CollisionEvent ce) {
		// TODO Auto-generated method stub
		
	}
	
}
