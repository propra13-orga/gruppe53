package progprak.gruppe53.sprites.enemies;

import progprak.gruppe53.game.CollisionEvent;
import progprak.gruppe53.game.GameLogic;

/** 
 * The boss that attacks by ramming the hero
 */
public class ChargingBoss extends BossEnemy {
	private static final long serialVersionUID = 2838843254158087591L;
	
	private long pause;
	
	/** 
	 * The constructor for the ChargingBoss class
	 * @param x The x-coordinate the boss starts at
	 * @param y The y-coordinate the boss starts at
	 * @param gameLogic The game loop
	 */
	public ChargingBoss(int x, int y, GameLogic gameLogic) {
		super(x, y, "images/Monster1.png",gameLogic);
		health = 100;
		nextLevel = "levels/Level7.xml";
		damageType = 3;
		blowResistance = 0.3;
		pierceResistance = 0.3;
		bluntResistance = 0.3;
		fireResistance = -0.3;
		arcaneResistance = -0.5;
		chargeAttack(gameLogic.getHero().getX(), gameLogic.getHero().getY());
		
	}
	
	/** 
	 * The method that makes the Boss charge to the heros position
	 * @param xPosition The hero's current x-coordinate
	 * @param yPosition The hero's current y-coordinate
	 */
	private void chargeAttack(double xPosition, double yPosition) {
		double x = xPosition - getX();
		double y = yPosition - getY();
		double length = Math.pow((Math.pow(x,2) + Math.pow(y,2)),0.5);
		x = x/length;
		y = y/length;
		dx = x*5;		
		dy = y*5;		
	}
	
	/** 
	 * The method that makes the Boss stop if he hits a wall
	 * @param ce The collisionevent that tells the boss he hit a wall 
	 */
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
	
	/** 
	 * The game loop
	 * @param delta The time the last loop took
	 */
	public void doLogic(long delta) {
		if (dx == 0 && dy == 0) {
			if (System.nanoTime() >= pause)	chargeAttack(gameLogic.getHero().getX(),gameLogic.getHero().getY());
		}
	}
}
