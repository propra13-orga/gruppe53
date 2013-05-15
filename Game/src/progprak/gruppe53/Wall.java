package progprak.gruppe53;


public class Wall extends Sprite implements Collidable{

	
	
	private static final long serialVersionUID = -8339793887949537005L;
	private CollisionEvent collisionEvent;

	public Wall(int x, int y) {
		super(x, y,"images/wall.png");
		collisionEvent = new CollisionEvent(CollisionEvent.MASSIVE);
	}

	@Override
	public void doLogic(long delta) {
		
	}

	@Override
	public CollisionEvent getCollisonEvent() {
		return collisionEvent;
	}


}
