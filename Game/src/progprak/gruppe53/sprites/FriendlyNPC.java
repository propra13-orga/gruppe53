package progprak.gruppe53.sprites;

import progprak.gruppe53.game.Collidable;
import progprak.gruppe53.game.CollisionEvent;
import progprak.gruppe53.game.GameLogic;

/** 
 * The class for friendly NPCs
 */
public abstract class FriendlyNPC extends CombatObject implements Collidable{
	
	private static final long serialVersionUID = 1L;

	/** 
	 * The constructor for the FriendlyNPC class
	 * @param x The x-coordinate of the NPC
	 * @param y The y-coordinate of the NPC
	 * @param imagePath The image of the NPC
	 * @param gameLogic The game loop
	 */
	public FriendlyNPC(int x, int y,String imagePath,GameLogic gameLogic){
		super(x,y,imagePath,gameLogic);
		faction = 1;
		collisionEvent = new CollisionEvent(CollisionEvent.EVENT_MASSIVE,this);
		doInitalizations();
	}

	/** 
	 * Makes the NPC not deal or take any damage
	 * @param ce The collisionevent that tells the NPC a damageevent has happened
	 */
	@Override
	protected void handleDamageEvent(CollisionEvent ce) {
	}

}
