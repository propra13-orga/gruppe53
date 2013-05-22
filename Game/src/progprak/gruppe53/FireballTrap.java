package progprak.gruppe53;

import java.util.ListIterator;
import java.util.Vector;

public class FireballTrap extends Sprite implements Collidable{
	
	private Game game;
	
	private static final long serialVersionUID = 1L;

	private CollisionEvent collisionEvent;
	
	private int startX, startY;
		
	public FireballTrap(int x, int y, Game game, int dx, int dy){
		super(x,y,"images/FireballRed.png");
			collisionEvent = new CollisionEvent(CollisionEvent.EVENT_DAMAGE);
			this.game = game;
			this.dx = dx;
			this.dy = dy;
			startX = x;
			startY = y;
	}
	
	@Override
	public void doLogic(long delta){
		
		
		Vector<Sprite> cs = game.testForCollision(getMaxX(),getMinX(),getMaxY(),getMinY(),dx,dy);
		for (ListIterator<Sprite> it = cs.listIterator(); it.hasNext();) {
			Sprite s = it.next();
			CollisionEvent ce = ((Collidable)s).getCollisionEvent();
			if(ce.getEvent() == CollisionEvent.EVENT_MASSIVE){
				x = startX;
				y = startY;
				break;
			}
		}
	}

	@Override
	public CollisionEvent getCollisionEvent() {
		return collisionEvent;
	}
	
}
