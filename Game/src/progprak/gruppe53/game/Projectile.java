package progprak.gruppe53.game;

abstract public class Projectile extends CombatObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5780290745821615323L;
	Shooter shooter;

	public Projectile(int x, int y, String imagePath, Game game, Shooter shooter) {
		super(x, y, imagePath, game);
		this.shooter = shooter;
	}

	@Override
	public void doLogic(long delta) {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void handleDamageEvent(CollisionEvent ce) {
	}

}
