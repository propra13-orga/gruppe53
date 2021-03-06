package progprak.gruppe53.sprites.objects;

import progprak.gruppe53.game.Collidable;
import progprak.gruppe53.game.CollisionEvent;
import progprak.gruppe53.sprites.Sprite;

public class PortalEntrance extends Sprite implements Collidable{

	private static final long serialVersionUID = 1L;

	private transient CollisionEvent collisionEvent;
	
	
	/**
	 * Portals are used to teleport the Hero in the same Level
	 * @param x   X Position of the Portal
	 * @param y   Y Position of the Portal
	 * @param newX  Heros target X Coordinate
	 * @param newY  Heros target Y Coordinate
	 */
	public PortalEntrance(int x, int y,int newX, int newY)
	{
		super(x,y,"images/entrance.png");
		collisionEvent = new CollisionEvent(CollisionEvent.EVENT_TELEPORT,this);
		collisionEvent.setNewX(newX);
		collisionEvent.setNewY(newY);
		
	}

	@Override
	public void doLogic(long delta) {
		
	}
	
	@Override
	public CollisionEvent getCollisionEvent()
	{
		return collisionEvent;
	}
	
}
