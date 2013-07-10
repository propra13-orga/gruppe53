package progprak.gruppe53.sprites;

import progprak.gruppe53.game.Collidable;
import progprak.gruppe53.game.CollisionEvent;
import progprak.gruppe53.game.DamageCollisionEvent;
import progprak.gruppe53.game.GameLogic;
import progprak.gruppe53.game.Shooter;

public class SpiderWebBall extends Projectile implements Collidable{
	
	private static final long serialVersionUID = 1L;


	
	public SpiderWebBall(int x, int y, GameLogic gameLogic, Shooter shooter, double dx, double dy, int faction){
		super(x,y,"images/spiderweb.png", gameLogic,shooter);
		this.setWidth(16);
		this.setHeight(16);
		this.dx = dx;
		this.dy = dy;
		this.faction = faction;
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
	protected void doneDamage() {
		super.doneDamage();
		
		gameLogic.removeSprite(this);
		shooter.shootRemoved();
	}
	@Override
	protected void initCollisionEvent() {
		collisionEvent = new DamageCollisionEvent(5,faction,this);
	}
	@Override
	protected void doneKill(CombatObject combatObject) {
		shooter.doneKill(combatObject);
	}
	
}