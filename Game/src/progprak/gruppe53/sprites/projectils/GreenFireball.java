package progprak.gruppe53.sprites.projectils;

import progprak.gruppe53.game.Collidable;
import progprak.gruppe53.game.CollisionEvent;
import progprak.gruppe53.game.DamageCollisionEvent;
import progprak.gruppe53.game.GameLogic;
import progprak.gruppe53.game.Shooter;
import progprak.gruppe53.sprites.CombatObject;

/** 
 * A special kind of fireball that can be bounced back by the hero
 */
public class GreenFireball extends Projectile implements Collidable{
	
	private static final long serialVersionUID = 1L;
	private double dx2;

	/** 
	 * The constructor of the GreenFireball class
	 * @param x The x-coordinate of the fireball
	 * @param y The y-coordinate of the fireball
	 * @param gameLogic The game loop
	 * @param shooter The shooter of the fireball
	 * @param dx The x-speed of the fireball
	 * @param dy The y-speed of the fireball
	 * @param faction The faction of the fireball (influences who is dealt damage by it and who is not)
	 */
	public GreenFireball(int x, int y, GameLogic gameLogic, Shooter shooter, double dx, double dy, int faction){
		super(x,y,"images/FireballGreen.png", gameLogic,shooter);
		this.setWidth(16);
		this.setHeight(16);
		this.dx = dx;
		dx2 = -1 * dx;
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
	 * Removes the fireball if it hit a wall
	 * @param ce The collisionevent that tells the fireball it hit a wall
	 */
	@Override
	protected void handleMassiveEvent(CollisionEvent ce) {
		super.handleMassiveEvent(ce);
		gameLogic.removeSprite(this);
		shooter.shootRemoved();
	}
	
	/** 
	 * Bounces the fireball back if the hero hits it and allows it to damage enemies
	 * @param ce The collisionevent that tells the fireball it got hit by the hero
	 */
	@Override
	protected void handleDamageEvent(CollisionEvent ce) {
		
		DamageCollisionEvent de = (DamageCollisionEvent) ce ;
		System.out.println(de.getFaction());
		System.out.println(faction);
		if (de.getFaction() == 1){
			faction = 3;
			collisionEvent.setActor(de.getActor());
			dx = dx2;
			faction = 3;
			initCollisionEvent();
		}
		super.handleDamageEvent(ce);
	}
	
	/** 
	 * Tells the fireball it dealt damage
	 */
	@Override
	protected void doneDamage() {
		super.doneDamage();
		gameLogic.removeSprite(this);
		shooter.shootRemoved();
	}
	
	/** 
	 * Tells the fireball it killed something
	 * @param combatObject The combatobject that got killed by the fireball
	 */
	@Override
	protected void doneKill(CombatObject combatObject) {
		shooter.doneKill(combatObject);
	}
}
