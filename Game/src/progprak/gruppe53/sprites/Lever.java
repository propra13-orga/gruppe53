package progprak.gruppe53.sprites;

import progprak.gruppe53.game.Collidable;
import progprak.gruppe53.game.CollisionEvent;
import progprak.gruppe53.game.GameLogic;
import progprak.gruppe53.game.LeverCollisionEvent;

public class Lever extends Sprite implements Collidable{

	private static final long serialVersionUID = 4963724950755414037L;

	private transient CollisionEvent collisionEvent;
	
	private boolean leverUsed;
	private long lastUsed;
	
	protected boolean handleEvents = true;
	
	protected transient GameLogic gameLogic;
	
	public Lever(int x, int y, String imagePath, GameLogic gameLogic) {
		super(x, y, imagePath);
		this.gameLogic = gameLogic;
		doInitialisation();
	}

	private void doInitialisation() {
		initCollisionEvent();
		
	}

	private void initCollisionEvent() {
		collisionEvent = new LeverCollisionEvent(0, this);
		
	}

	@Override
	public void doLogic(long delta){
		long current = System.nanoTime();
		if(leverUsed && current - lastUsed >= 2e9){
			leverUsed = false;
		}
	}

	@Override
	public CollisionEvent getCollisionEvent() {
		return collisionEvent;
	}
	
}
