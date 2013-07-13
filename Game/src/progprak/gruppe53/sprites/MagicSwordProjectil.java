package progprak.gruppe53.sprites;

import progprak.gruppe53.game.Collidable;
import progprak.gruppe53.game.CollisionEvent;
import progprak.gruppe53.game.DamageCollisionEvent;
import progprak.gruppe53.game.GameLogic;
import progprak.gruppe53.game.Shooter;

public class MagicSwordProjectil extends Projectile implements Collidable{
	
	private static final long serialVersionUID = 1L;


	
	public MagicSwordProjectil(int x, int y, GameLogic gameLogic, Shooter shooter, double dx, double dy, int faction){
		super(x,y,"images/castSwordProjectile.png", gameLogic,shooter);
		this.setWidth(16);
		this.setHeight(16);
		this.dx = dx;
		this.dy = dy;
		this.faction = faction;
		damageType = 5;
		doInitalizations();		
	}
	
	@Override
	public void doLogic(long delta){
		
	}
	@Override
	protected void initCollisionEvent() {
		collisionEvent = new DamageCollisionEvent(15,faction,this);
	}

	@Override
	protected void handleMassiveEvent(CollisionEvent ce) {
		super.handleMassiveEvent(ce);
		gameLogic.removeSprite(this);
		shooter.shootRemoved();
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
