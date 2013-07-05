package progprak.gruppe53.sprites;

import progprak.gruppe53.game.CollisionEvent;
import progprak.gruppe53.game.GameLogic;

public class EnemyOldManNPC extends Enemy {


	private static final long serialVersionUID = 2838843254158087591L;
	
	
	public EnemyOldManNPC(int x, int y, GameLogic gameLogic) {
		super(x, y, "images/OldManNPC.png",gameLogic);
		dx = 1;
		health = 2;

	}
	@Override
	protected void handleMassiveEvent(CollisionEvent ce){
		if(ce.getDirection() == CollisionEvent.DIRECTION_VERTICAL && dy == -1){
			dx = 1;
			dy = 0;
		}
		if(ce.getDirection() == CollisionEvent.DIRECTION_VERTICAL && dy == 1){
			dx = -1;
			dy = 0;
		}
		if(ce.getDirection() == CollisionEvent.DIRECTION_HORIZONTAL && dx == 1 ){
			dx = 0;
			dy = 1;
		}
		if(ce.getDirection() == CollisionEvent.DIRECTION_HORIZONTAL && dx == -1 ){
			dx = 0;
			dy = -1;
		}
		handleEvents = false;
	}
	
	@Override
	protected void handleDie() {
		gameLogic.addSprite(new EnemyGhost((int)gameLogic.getHero().getX(),(int)gameLogic.getHero().getY(), gameLogic));
		gameLogic.removeSprite(this);
	}
}
