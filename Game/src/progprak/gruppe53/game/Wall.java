package progprak.gruppe53.game;


public class Wall extends Sprite implements Collidable{

	
	
	private static final long serialVersionUID = -8339793887949537005L;
	private CollisionEvent collisionEvent;

	public Wall(int x, int y) {
		super(x, y,"images/wall.png");
		collisionEvent = new CollisionEvent(CollisionEvent.EVENT_MASSIVE,this);
	}

	@Override
	public void doLogic(long delta) {
		
	}

	@Override
	public CollisionEvent getCollisionEvent() {
		return collisionEvent;
	}


}
