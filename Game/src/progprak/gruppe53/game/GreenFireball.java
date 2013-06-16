package progprak.gruppe53.game;

public class GreenFireball extends Projectile implements Collidable{
	
	private static final long serialVersionUID = 1L;


	
	public GreenFireball(int x, int y, Game game, Shooter shooter, double dx, double dy, int faction){
		super(x,y,"images/FireballGreen.png", game,shooter);
		this.width = 16;
		this.height = 16;
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
		game.getGameLogic().removeSprite(this);
		shooter.shootRemoved();
	}
	@Override
	protected void handleDamageEvent(CollisionEvent ce) {
		dx *= -1;
	}
	@Override
	protected void doneDamage() {
		super.doneDamage();
		game.getGameLogic().removeSprite(this);
		shooter.shootRemoved();
	}
	@Override
	protected void doneKill(CombatObject combatObject) {
		shooter.doneKill(combatObject);
	}
}