package progprak.gruppe53.sprites;

import progprak.gruppe53.game.Collidable;
import progprak.gruppe53.game.CollisionEvent;


public class Wall extends Sprite implements Collidable{

	
	
	private static final long serialVersionUID = -8339793887949537005L;
	private transient CollisionEvent collisionEvent;
	private boolean visible = true;

	public Wall(int x, int y) {
		super(x, y,"images/wall.png");
		collisionEvent = new CollisionEvent(CollisionEvent.EVENT_MASSIVE,this);
	}

	@Override
	public void doLogic(long delta) {
		if(visible){
			
		}
	}

	@Override
	public CollisionEvent getCollisionEvent() {
		return collisionEvent;
	}

	public void setStatus(){
		visible = !visible;
	}
}
