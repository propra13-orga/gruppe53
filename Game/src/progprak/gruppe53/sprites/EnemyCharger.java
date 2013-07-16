package progprak.gruppe53.sprites;

import progprak.gruppe53.game.CollisionEvent;
import progprak.gruppe53.game.GameLogic;

/** 
 * An Enemy that charges the Hero. Behaves similar to ChargingBoss
 * See ChargingBoss for Documentation
 */
public class EnemyCharger extends Enemy {
	private static final long serialVersionUID = 2838843254158087591L;
	
	private long pause;
		
	public EnemyCharger(int x, int y, GameLogic gameLogic) {
		super(x, y, "images/Monster1.png",gameLogic);
		health = 50;
		damageType = 3;
		blowResistance = 0.3;
		pierceResistance = 0.3;
		bluntResistance = 0.3;
		fireResistance = -0.2;
		arcaneResistance = -0.5;
		chargeAttack(gameLogic.getHero().getX(), gameLogic.getHero().getY());
		
	}
	
	private void chargeAttack(double xPosition, double yPosition) {
		double x = xPosition - getX();
		double y = yPosition - getY();
		double length = Math.pow((Math.pow(x,2) + Math.pow(y,2)),0.5);
		x = x/length;
		y = y/length;
		dx = x*5;		
		dy = y*5;		
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
		pause = System.nanoTime() + (long)((5L)*1e8);
	}
	
	public void doLogic(long delta) {
		if (dx == 0 && dy == 0) {
			if (System.nanoTime() >= pause)	chargeAttack(gameLogic.getHero().getX(),gameLogic.getHero().getY());
		}
	}
}
