package progprak.gruppe53.sprites;

import progprak.gruppe53.game.Collidable;
import progprak.gruppe53.game.CollisionEvent;

public class PortalEntrance extends Sprite implements Collidable{

	private static final long serialVersionUID = 1L;

	private transient CollisionEvent collisionEvent;
	
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
