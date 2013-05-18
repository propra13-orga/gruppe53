package progprak.gruppe53;

public class Trap extends Sprite implements Collidable{
	
	private static final long serialVersionUID = 1L;

	private CollisionEvent collisionEvent;
	
	public Trap(int x, int y){
		super(x,y,"images/trap.png");
			collisionEvent = new CollisionEvent(CollisionEvent.DAMAGE);
	}

	@Override
	public void doLogic(long delta){
		
	}

	@Override
	public CollisionEvent getCollisionEvent() {
		return collisionEvent;
	}
}

