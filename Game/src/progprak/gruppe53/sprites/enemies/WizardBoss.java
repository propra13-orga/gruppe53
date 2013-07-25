package progprak.gruppe53.sprites.enemies;

import progprak.gruppe53.game.CollisionEvent;
import progprak.gruppe53.game.DamageCollisionEvent;
import progprak.gruppe53.game.GameLogic;
import progprak.gruppe53.game.Shooter;
import progprak.gruppe53.sprites.CombatObject;
import progprak.gruppe53.sprites.projectils.Fireball;
import progprak.gruppe53.sprites.projectils.GreenFireball;
import sun.net.www.content.text.plain;

/** 
 * A Boss that is immune to damage dealt by the hero and shoots fireballs
 */
public class WizardBoss extends BossEnemy implements Shooter {
	private static final long serialVersionUID = 2838843254158087591L;
	
	private long cooldown = 0;
	private int castOrder = 0;
	private double lastdy;
	
	/** 
	 * The constructor for the WizardBoss class
	 * @param x The x-coordinate for the boss
	 * @param y The y-coordinate for the boss
	 * @param gameLogic the game loop
	 */
	public WizardBoss(int x, int y, GameLogic gameLogic) {
		super(x, y, "images/bug.png",gameLogic);
		health = 30;
		nextLevel = "levels/Level4.xml";
		dx = 0;
		dy = 2;
		damageType = 5;
		blowResistance = -0.5;
		pierceResistance = -0.4;
		bluntResistance = -0.5;
		fireResistance = 0.2;
		arcaneResistance = 0.5;
	}
	
	/** 
	 * Shoots a green fireball that can be bounced back by the hero
	 */
	private void shootGreenFireball(){
		lastdy = dy;
		dy = 0;
		gameLogic.addSprite(new GreenFireball((int)getX()-20,(int)getY()+8,gameLogic,this,-2,0,2));
		castOrder = 0;
		cooldown = System.nanoTime() + (long)((6L)*1e9);
	}
	
	/** 
	 * Shoots fireballs
	 */
	private void shootFireballs(){
		gameLogic.addSprite(new Fireball((int)getX()-20,(int)getY()+8,gameLogic,this,-2,-0.5,2));
		gameLogic.addSprite(new Fireball((int)getX()-20,(int)getY()+8,gameLogic,this,-2.5,-0.25,2));
		gameLogic.addSprite(new Fireball((int)getX()-20,(int)getY()+8,gameLogic,this,-3,0,2));
		gameLogic.addSprite(new Fireball((int)getX()-20,(int)getY()+8,gameLogic,this,-2.5,0.25,2));
		gameLogic.addSprite(new Fireball((int)getX()-20,(int)getY()+8,gameLogic,this,-2,0.5,2));
		castOrder = castOrder +1;
		cooldown = System.nanoTime() + (long)((1L)*1e9);
	}
	
	/** 
	 * Changes the direction of the boss if he hits a wall
	 * @param ce The collisionevent that tells the boss he hit a wall
	 */
	@Override
	protected void handleMassiveEvent(CollisionEvent ce){
		if(ce.getDirection() == CollisionEvent.DIRECTION_HORIZONTAL)dx *= -1;
		else if(ce.getDirection() == CollisionEvent.DIRECTION_VERTICAL)dy *= -1;
		handleEvents = false;
	}
	
	@Override
	public void shootRemoved() {
		
	}

	@Override
	public void doneKill(CombatObject combatObject) {
	}
	
	/** 
	 * Makes the boss immune to damage dealt by the hero
	 * @param ce The collisionevent that tells the boss a damageevent has occured
	 */
	@Override
	protected void handleDamageEvent(CollisionEvent ce) {
		DamageCollisionEvent de = (DamageCollisionEvent) ce;
		if (de.getFaction() != 1) {
			super.handleDamageEvent(ce);
		}
	}
	
	/** 
	 * The game loop
	 * @param delta The time the last loop took
	 */
	public void doLogic(long delta) {
		if (System.nanoTime() >= cooldown) {
			if (dy == 0) dy = lastdy;
			if (castOrder == 3) {
				shootGreenFireball();
			}
			else {
				shootFireballs();
			}
			
		}
	}
	/** 
	 * Handles the death of the boss and the switch to the next level
	 */
	protected void handleDie() {
		super.handleDie();
		gameLogic.switchLevel(nextLevel);
	}
	
}