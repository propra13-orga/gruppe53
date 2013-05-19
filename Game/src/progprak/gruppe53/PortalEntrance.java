package progprak.gruppe53;

public class PortalEntrance extends Sprite implements Collidable{

	private static final long serialVersionUID = 1L;

	private CollisionEvent collisionEvent;
	
	public PortalEntrance(int x, int y,int newX, int newY)
	{
		super(x,y,"images/entrance.png");
		collisionEvent = new CollisionEvent(CollisionEvent.TELEPORT);
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
