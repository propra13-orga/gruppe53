package progprak.gruppe53;

public class FireballTrap extends Sprite implements Collidable{
	
	private Game game;
	
	private static final long serialVersionUID = 1L;

	private CollisionEvent collisionEvent;
	
	private int startX, startY;
		
	public FireballTrap(int x, int y, Game game, int dx, int dy){
		super(x,y,"images/FireballRed.png");
			collisionEvent = new CollisionEvent(CollisionEvent.DAMAGE);
			this.game = game;
			this.dx = dx;
			this.dy = dy;
			startX = x;
			startY = y;
	}
	
	@Override
	public void doLogic(long delta){
		
	CollisionEvent ce;
		if((ce = game.testForCollision(getMaxX()+dx,getMinX()+dx,getMaxY()+dy,getMinY()+dy)) != null){
			if(ce.getEvent() == CollisionEvent.MASSIVE){
					x = startX;
						y = startY;
			}
		}
	}

	@Override
	public CollisionEvent getCollisionEvent() {
		return collisionEvent;
	}
	
}
