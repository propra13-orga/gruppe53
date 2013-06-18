package progprak.gruppe53.game;

public abstract class FriendlyNPC extends CombatObject implements Collidable{
	
	private static final long serialVersionUID = 1L;

	
	public FriendlyNPC(int x, int y,String imagePath,GameLogic gameLogic){
		super(x,y,imagePath,gameLogic);
		faction = 1;
		collisionEvent = new CollisionEvent(CollisionEvent.EVENT_MASSIVE,this);
		doInitalizations();
	}

	@Override
	protected void handleDamageEvent(CollisionEvent ce) {
	}

}
