package progprak.gruppe53.sprites;

import progprak.gruppe53.game.CollisionEvent;
import progprak.gruppe53.game.GameLogic;

public class EnemyGhost extends Enemy {


	private static final long serialVersionUID = 2838843254158087591L;

	public EnemyGhost(int x, int y, GameLogic gameLogic) {
		super(x, y, "images/ghost1.png",gameLogic);
		dx = Math.random()*3-1.5;
		dy = Math.random()*3-1.5;
		health = 2;
	}
	@Override
	protected void handleMassiveEvent(CollisionEvent ce){
		if(ce.getDirection() == CollisionEvent.DIRECTION_HORIZONTAL)dx *= -1;
		if(ce.getDirection() == CollisionEvent.DIRECTION_VERTICAL)dy *= -1;
		handleEvents = false;
	}
}
