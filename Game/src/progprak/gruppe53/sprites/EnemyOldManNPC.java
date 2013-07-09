package progprak.gruppe53.sprites;

import progprak.gruppe53.game.CollisionEvent;
import progprak.gruppe53.game.GameLogic;

public class EnemyOldManNPC extends Enemy {


	private static final long serialVersionUID = 2838843254158087591L;
	
	private double speed = 1;
	
	
	public EnemyOldManNPC(int x, int y, GameLogic gameLogic) {
		super(x, y, "images/OldManNPC.png",gameLogic);
		dx = speed;
		health = 2;

	}
	@Override
	protected void handleMassiveEvent(CollisionEvent ce){
		if(ce.getDirection() == CollisionEvent.DIRECTION_VERTICAL && Math.signum(dy) == -1){
			dx = speed;
			dy = 0;
		}
		if(ce.getDirection() == CollisionEvent.DIRECTION_VERTICAL && Math.signum(dy) == 1){
			dx = -speed;
			dy = 0;
		}
		if(ce.getDirection() == CollisionEvent.DIRECTION_HORIZONTAL && Math.signum(dx) == 1 ){
			dx = 0;
			dy = speed;
		}
		if(ce.getDirection() == CollisionEvent.DIRECTION_HORIZONTAL && Math.signum(dx) == -1 ){
			dx = 0;
			dy = -speed;
		}
		handleEvents = false;
	}
	
	@Override
	protected void handleDie() {
		if(getX() <= gameLogic.getHero().getX()){
			gameLogic.addSprite(new EnemyGhost((int)gameLogic.getHero().getX()-20,(int)gameLogic.getHero().getY(), gameLogic));
		}
		else {
			gameLogic.addSprite(new EnemyGhost((int)gameLogic.getHero().getX()+20,(int)gameLogic.getHero().getY(),gameLogic));
		}
		gameLogic.removeSprite(this);
	}
}
