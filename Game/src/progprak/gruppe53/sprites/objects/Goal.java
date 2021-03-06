package progprak.gruppe53.sprites.objects;

import progprak.gruppe53.game.Collidable;
import progprak.gruppe53.game.CollisionEvent;
import progprak.gruppe53.sprites.Sprite;

public class Goal extends Sprite implements Collidable {

	private static final long serialVersionUID = 7586740939024486489L;

	private transient CollisionEvent collisionEvent;
	
	public Goal(int x, int y) {
		super(x, y, "images/exit.png");
		collisionEvent = new CollisionEvent(CollisionEvent.EVENT_GOAL,this);
		
	}

	@Override
	public void doLogic(long delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public CollisionEvent getCollisionEvent() {
		return collisionEvent;
	}

}
