package progprak.gruppe53.game;

public class PassiveCombatObject extends CombatObject {

	private static final long serialVersionUID = 4695830786688943610L;

	public PassiveCombatObject(int x, int y, String imagePath,
			GameLogic gameLogic) {
		super(x, y, imagePath, gameLogic);

	}
	@Override
	protected void handleCollisionEvent(CollisionEvent ce) {
	}
	@Override
	protected void initCollisionEvent() {
		collisionEvent = new CollisionEvent(CollisionEvent.EVENT_NOTHING, this);
	}

}
