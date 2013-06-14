package progprak.gruppe53.game;

public class FireballTrap extends CombatObject implements Collidable{
	
	private static final long serialVersionUID = 1L;

	public FireballTrap(int x, int y, Game game, int dx, int dy, int reSpX, int reSpY){
		super(x,y,"images/FireballRed.png", game);
			this.width = 16;
			this.height = 16;
			this.dx = dx;
			this.dy = dy;
			doInitalizations();
	}
	
	public FireballTrap(int x, int y, Game game, double d, double e, int faction)
	{
		super(x,y,"images/FireballRed.png", game);
		this.width = 16;
		this.height = 16;
		this.dx = d;
		this.dy = e;
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
	}
	@Override
	protected void handleDamageEvent(CollisionEvent ce) {
	}
	@Override
	protected void doneDamage() {
		super.doneDamage();
		game.getGameLogic().removeSprite(this);
	}

	
}
