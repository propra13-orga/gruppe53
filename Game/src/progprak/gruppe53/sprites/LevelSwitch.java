package progprak.gruppe53.sprites;

import progprak.gruppe53.game.Collidable;
import progprak.gruppe53.game.CollisionEvent;

public class LevelSwitch extends Sprite implements Collidable{

	private static final long serialVersionUID = -8792805825772150907L;
	
	private transient CollisionEvent collisionEvent;


	public LevelSwitch(int x, int y, String newLevel) {
		super(x,y,"images/entrance2.png");
		collisionEvent = new CollisionEvent(CollisionEvent.EVENT_SWITCH_LEVEL,this);
		collisionEvent.setNewLevel(newLevel);
	}

	public LevelSwitch(int x, int y, String imagePath, String newLevel) {
		super(x,y,imagePath);
		collisionEvent = new CollisionEvent(CollisionEvent.EVENT_SWITCH_LEVEL,this);
		collisionEvent.setNewLevel(newLevel);
	}

	@Override
	public void doLogic(long delta) {
	}

	@Override
	public CollisionEvent getCollisionEvent() {
		return collisionEvent;
	}

}
