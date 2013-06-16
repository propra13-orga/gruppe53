package progprak.gruppe53.game;

public class ChargingBoss extends Enemy {
	private static final long serialVersionUID = 2838843254158087591L;
	
	private long pause;
		
	public ChargingBoss(int x, int y, Game game) {
		super(x, y, "images/Monster1.png",game);
		health = 5;
		chargeAttack(game.getGameLogic().getHero().getX(), game.getGameLogic().getHero().getY());
		
	}
	
	private void chargeAttack(double xPosition, double yPosition) {
		double x = xPosition - getX();
		double y = yPosition - getY();
		double length = Math.pow((Math.pow(x,2) + Math.pow(y,2)),1/2);
		x = x/length;
		y = y/length;
		dx = x/60;		
		dy = y/60;		
	}
	
	protected void handleMassiveEvent(CollisionEvent ce){
		if(ce.getDirection() == CollisionEvent.DIRECTION_HORIZONTAL){
			dx = 0;
			dy = 0;
		}
		else if(ce.getDirection() == CollisionEvent.DIRECTION_VERTICAL){
			dy = 0;
			dx = 0;
		}
		pause = System.nanoTime() + (long)((0L)*1e9);	
	}
	
	public void doLogic(long delta) {
		if (dx == 0 && dy == 0) {
			if (System.nanoTime() >= pause)	chargeAttack(game.getGameLogic().getHero().getX(),game.getGameLogic().getHero().getY());
		}
		super.doLogic();
	}
}
