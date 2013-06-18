package progprak.gruppe53.game;

public class Fireball extends Projectile implements Collidable{
	
	private static final long serialVersionUID = 1L;


	
	public Fireball(int x, int y, GameLogic gameLogic, Shooter shooter, double dx, double dy, int faction){
		super(x,y,"images/FireballRed.png", gameLogic,shooter);
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
