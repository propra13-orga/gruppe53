package progprak.gruppe53.sprites;

import progprak.gruppe53.game.Collidable;
import progprak.gruppe53.game.CollisionEvent;
import progprak.gruppe53.game.DamageCollisionEvent;
import progprak.gruppe53.game.GameLogic;
import progprak.gruppe53.game.Shooter;

public class GreenFireball extends Projectile implements Collidable{
	
	private static final long serialVersionUID = 1L;


	
	public GreenFireball(int x, int y, GameLogic gameLogic, Shooter shooter, double dx, double dy, int faction){
		super(x,y,"images/FireballGreen.png", gameLogic,shooter);
		this.setWidth(16);
		this.setHeight(16);
		this.dx = dx;
		this.dy = dy;
		this.faction = faction;
		damageType = 4;
		doInitalizations();
	}
	
	@Override
	public void doLogic(long delta){
		

	}
	@Override
	protected void handleMassiveEvent(CollisionEvent ce) {
		super.handleMassiveEvent(ce);
		gameLogic.removeSprite(this);
		shooter.shootRemoved();
	}
	@Override
	protected void handleDamageEvent(CollisionEvent ce) {
		DamageCollisionEvent de = (DamageCollisionEvent) ce ;
		if (de.getFaction() == 1){
			collisionEvent.setActor(de.getActor());
			dx *= -1;
		}
		super.handleDamageEvent(ce);
	}
	@Override
	protected void doneDamage() {
		super.doneDamage();
		gameLogic.removeSprite(this);
		shooter.shootRemoved();
	}
	@Override
	protected void doneKill(CombatObject combatObject) {
		shooter.doneKill(combatObject);
	}
}