package progprak.gruppe53.game;

public class EnemyGhost extends Enemy {


	private static final long serialVersionUID = 2838843254158087591L;

	public EnemyGhost(int x, int y, Game game) {
		super(x, y, "images/ghost1.png",game);
		dx = Math.random()*3-1.5;
		dy = Math.random()*3-1.5;
	}
	protected void handleMassiveEvent(CollisionEvent ce){
		if(ce.getDirection() == CollisionEvent.DIRECTION_HORIZONTAL)dx *= -1;
		else if(ce.getDirection() == CollisionEvent.DIRECTION_VERTICAL)dy *= -1;
		handleEvents = false;
	}

}
