package progprak.gruppe53.game;

public abstract class Trap extends CombatObject implements Collidable{
	
	private static final long serialVersionUID = 1L;

	
	public Trap(int x, int y,String imagePath,Game game){
		super(x,y,imagePath,game);
		faction = 2;
		doInitalizations();
	}

	@Override
	public void doLogic(long delta){
		
	}

	@Override
	public CollisionEvent getCollisionEvent() {
		return collisionEvent;
	}
	@Override
	protected void handleCollisionEvent(CollisionEvent ce) {
		// TODO Auto-generated method stub
		
	}
}

