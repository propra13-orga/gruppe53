package progprak.gruppe53.game;

public abstract class Trap extends Sprite implements Collidable{
	
	private static final long serialVersionUID = 1L;

	private CollisionEvent collisionEvent;
	
	public Trap(int x, int y,String imagePath){
		super(x,y,imagePath);
			collisionEvent = new CollisionEvent(CollisionEvent.EVENT_DAMAGE);
	}

	@Override
	public void doLogic(long delta){
		
	}

	@Override
	public CollisionEvent getCollisionEvent() {
		return collisionEvent;
	}
}

