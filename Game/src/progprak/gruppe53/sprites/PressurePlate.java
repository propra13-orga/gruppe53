package progprak.gruppe53.sprites;

import java.awt.geom.Point2D;

import progprak.gruppe53.game.Collidable;
import progprak.gruppe53.game.CollisionEvent;

public class PressurePlate extends Sprite implements Collidable {

	private static final long serialVersionUID = -8792805825772150907L;

	private transient CollisionEvent collisionEvent;

	private boolean pressed=false;
	private boolean press;
	
	public PressurePlate(int x, int y) {
		super(x, y, "images/entrance2.png");
		collisionEvent = new CollisionEvent(CollisionEvent.EVENT_PLATE_PRESSED,
				this);
	}

	public PressurePlate(int x, int y,Point2D walls[]) {
		super(x, y, "images/entrance2.png");
		collisionEvent = new CollisionEvent(CollisionEvent.EVENT_PLATE_PRESSED,this);
		collisionEvent.setNewStatus(walls);

	}

	@Override
	public void doLogic(long delta) {
		if(!press){
			pressed = false;
		}
		press = false;
	}

	@Override
	public CollisionEvent getCollisionEvent() {
		return collisionEvent;
	}

	public void setPressed(boolean b) {
		pressed = true;
	}

	public boolean isPressed() {
		return pressed;
	}

	public void press() {
		press = true;
	}
	

}
