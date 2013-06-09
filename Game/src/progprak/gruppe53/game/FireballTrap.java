package progprak.gruppe53.game;

public class FireballTrap extends Sprite implements Collidable{
	
	private static final long serialVersionUID = 1L;

	private CollisionEvent collisionEvent;
	
	public FireballTrap(int x, int y, Game game, int dx, int dy, int reSpX, int reSpY){
		super(x,y,"images/FireballRed.png");
			this.width = 16;
			this.height = 16;
			this.dx = dx;
			this.dy = dy;
	}
	
	@Override
	public void doLogic(long delta){
		
		
		/*Vector<Sprite> cs = game.testForCollision(getMaxX(),getMinX(),getMaxY(),getMinY(),dx,dy);
		for (ListIterator<Sprite> it = cs.listIterator(); it.hasNext();) {
			Sprite s = it.next();
			CollisionEvent ce = ((Collidable)s).getCollisionEvent();
			if(ce.getEvent() == CollisionEvent.EVENT_MASSIVE){
				x = reSpX;
				y = reSpY;
				break;
			}
		}*/
	}

	@Override
	public CollisionEvent getCollisionEvent() {
		return collisionEvent;
	}
	
}
