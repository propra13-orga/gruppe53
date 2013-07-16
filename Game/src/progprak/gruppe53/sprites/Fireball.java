package progprak.gruppe53.sprites;

import progprak.gruppe53.game.Collidable;
import progprak.gruppe53.game.CollisionEvent;
import progprak.gruppe53.game.GameLogic;
import progprak.gruppe53.game.Shooter;

/** 
 * A fireball projectile
 */
public class Fireball extends Projectile implements Collidable{
	
	private static final long serialVersionUID = 1L;
	
	/** 
	 * The constructor for the Fireball class
	 * @param x the x-coordinate for the fireball
	 * @param y the y-coordinate for the fireball
	 * @param gameLogic the game loop
	 * @param shooter The shooter of the fireball
	 * @param dx The x-speed of the fireball
	 * @param dy The y-speed of the fireball
	 */
	public Fireball(int x, int y, GameLogic gameLogic, Shooter shooter, double dx, double dy, int faction){
		super(x,y,"images/FireballRed.png", gameLogic,shooter);
		this.setWidth(16);
		this.setHeight(16);
		this.dx = dx;
		this.dy = dy;
		this.faction = faction;
		damageType = 4;
		doInitalizations();		
	}
	
	/** 
	 * The game loop
	 * @param delta The time the last loop took
	 */
	@Override
	public void doLogic(long delta){
		

	}
	
	/** 
	 * The method that removes the fireball if it hits the wall
	 * @param ce The collisionevent that tells the fireball it hit a wall
	 */
	@Override
	protected void handleMassiveEvent(CollisionEvent ce) {
		super.handleMassiveEvent(ce);
		gameLogic.removeSprite(this);
		shooter.shootRemoved();
	}
	
	/** 
	 * The method that tells the fireball it dealt damage
	 */
	@Override
	protected void doneDamage() {
		super.doneDamage();
		gameLogic.removeSprite(this);
		shooter.shootRemoved();
	}
	
	/** 
	 * The method that tells the fireball it killed something
	 * @param The combatobject that was killed by the fireball
	 */
	@Override
	protected void doneKill(CombatObject combatObject) {
		shooter.doneKill(combatObject);
	}
	
}
