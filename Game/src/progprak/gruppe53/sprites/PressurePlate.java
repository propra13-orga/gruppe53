package progprak.gruppe53.sprites;

import java.awt.geom.Point2D;

import progprak.gruppe53.game.Collidable;
import progprak.gruppe53.game.CollisionEvent;

public class PressurePlate extends Sprite implements Collidable {

	private static final long serialVersionUID = -8792805825772150907L;

	private transient CollisionEvent collisionEvent;

	private boolean pressed=false;
	private boolean press;
	
	/**
	 * PressurePlates are used to remove or create Walls in predefined Areas
	 * @param x X Position of the PressurePlate
	 * @param y Y Position of the PressurePlate
	 */
	public PressurePlate(int x, int y) {
		super(x, y, "images/entrance2.png");
		collisionEvent = new CollisionEvent(CollisionEvent.EVENT_PLATE_PRESSED,
				this);
	}
	
	/**
	 * PressurePlate are used to remove or create Walls in predefined Areas
	 * @param x X Position of the PressurePlate
	 * @param y Y Position of the PressurePlate
	 * @param walls Coordinates of the Areas where the Walls are switched
	 */
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

	/**
	 * sets pressed to true
	 * @param b
	 */
	public void setPressed(boolean b) {
		pressed = true;
	}

	/**
	 * 
	 * @return pressed
	 */
	public boolean isPressed() {
		return pressed;
	}

	/**
	 * sets press to true
	 */
	public void press() {
		press = true;
	}
	

}
