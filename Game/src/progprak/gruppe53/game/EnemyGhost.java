package progprak.gruppe53.game;

import java.util.Random;

public class EnemyGhost extends Enemy {


	private static final long serialVersionUID = 2838843254158087591L;

	public EnemyGhost(int x, int y, Game game) {
		super(x, y, "images/ghost1.png",game);
		Random rnd = new Random();
		dx = rnd.nextFloat()/4;
		dy = rnd.nextFloat()/4;
		System.out.println(dx);
	}
	protected void handleMassiveEvent(CollisionEvent ce){
		if(ce.getDirection() == CollisionEvent.DIRECTION_HORIZONTAL)dx *= -1;
		else if(ce.getDirection() == CollisionEvent.DIRECTION_VERTICAL)dy *= -1;
		handleEvents = false;
	}

}
