package progprak.gruppe53;

public abstract class Enemy extends Sprite implements Collidable{
	
	private static final long serialVersionUID = 1L;

	private CollisionEvent collisionEvent;
	
	public Enemy(int x, int y,String imagePath){
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
