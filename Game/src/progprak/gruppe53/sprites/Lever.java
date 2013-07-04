package progprak.gruppe53.sprites;

import progprak.gruppe53.game.Collidable;
import progprak.gruppe53.game.CollisionEvent;
import progprak.gruppe53.game.GameLogic;
import progprak.gruppe53.game.LeverCollisionEvent;

public class Lever extends Sprite implements Collidable{

	private static final long serialVersionUID = 4963724950755414037L;

	private transient CollisionEvent collisionEvent;
	
	private boolean leverStatus;
	
	protected boolean handleEvents = true;
	
	protected transient GameLogic gameLogic;
	
	public Lever(int x, int y, String imagePath, GameLogic gameLogic, Wall wall1, Wall wall2, Wall wall3) {
		super(x, y, imagePath);
		collisionEvent = new CollisionEvent(CollisionEvent.EVENT_USE_LEVER,this);
		collisionEvent.setNewLeverStatus(this,wall1,wall2,wall3);
	}




	@Override
	public void doLogic(long delta){

	}

	@Override
	public CollisionEvent getCollisionEvent() {
		return collisionEvent;
	}
	
	public void setStatus(){
		leverStatus = !leverStatus;
	}
}
