package progprak.gruppe53;

public class LevelSwitch extends Sprite implements Collidable{

	private CollisionEvent collisionEvent;

	public LevelSwitch(int x, int y, String newLevel) {
		super(x,y,"images/entrance.png");
		collisionEvent = new CollisionEvent(CollisionEvent.SWITCH_LEVEL);
		collisionEvent.setNewLevel(newLevel);
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
