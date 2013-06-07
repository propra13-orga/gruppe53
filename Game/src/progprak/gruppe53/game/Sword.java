package progprak.gruppe53.game;

public class Sword extends Items implements Collidable {

	private static final long serialVersionUID = 123456789L;

	private CollisionEvent collisionEvent;
	
	public Sword(int x, int y){
		super( x, y, "images/sword1.png");
		collisionEvent = new CollisionEvent(CollisionEvent.EVENT_DAMAGE);
	}
	
	@Override
	public void doLogic(long delta){
		
	}
	
}
