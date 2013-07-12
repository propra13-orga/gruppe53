package progprak.gruppe53.sprites;

import progprak.gruppe53.game.CollisionEvent;
import progprak.gruppe53.game.GameLogic;
import progprak.gruppe53.game.Shooter;

abstract public class Projectile extends CombatObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5780290745821615323L;
	Shooter shooter;

	public Projectile(int x, int y, String imagePath, GameLogic gameLogic, Shooter shooter) {
		super(x, y, imagePath, gameLogic);
		this.shooter = shooter;
	}

	@Override
	public void doLogic(long delta) {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void handleDamageEvent(CollisionEvent ce) {
	}
	@Override
	protected void handleTeleportEvent(int newX, int newY) {
		// TODO Auto-generated method stub
		super.handleTeleportEvent(newX, newY);
	}
	
}
