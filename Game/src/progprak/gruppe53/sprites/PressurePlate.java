package progprak.gruppe53.sprites;

import progprak.gruppe53.game.Collidable;
import progprak.gruppe53.game.CollisionEvent;

public class PressurePlate extends Sprite implements Collidable{

	private static final long serialVersionUID = -8792805825772150907L;
	
	private transient CollisionEvent collisionEvent;


	public PressurePlate(int x, int y) {
		super(x,y,"images/entrance2.png");
		collisionEvent = new CollisionEvent(CollisionEvent.EVENT_PLATE_PRESSED,this);
	}

	public PressurePlate(int x, int y, String imagePath) {
		super(x,y,imagePath);
		collisionEvent = new CollisionEvent(CollisionEvent.EVENT_PLATE_PRESSED,this);
		collisionEvent.setNewStatus( null, null, null);
	}

	@Override
	public void doLogic(long delta) {
	}

	@Override
	public CollisionEvent getCollisionEvent() {
		return collisionEvent;
	}

}

