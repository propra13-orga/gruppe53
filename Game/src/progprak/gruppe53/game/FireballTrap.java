package progprak.gruppe53.game;

public class FireballTrap extends Sprite implements Shooter {
	private static final long serialVersionUID = 1L;
	
	private Game game;
	private boolean shootAlive = false;
	private double xMovement;
	private double yMovement;

	public FireballTrap(int x, int y,Game game, double xMovement, double yMovement) {
		super(x, y, null);
		this.game = game;
		this.xMovement = xMovement;
		this.yMovement = yMovement;
	}
	public FireballTrap(int x, int y,Game game, double xMovement, double yMovement, String imagePath) {
		super(x, y, imagePath);
		this.game = game;
		this.xMovement = xMovement;
		this.yMovement = yMovement;
	}

	@Override
	public void doLogic(long delta) {
		if(!shootAlive){
			game.getGameLogic().addSprite(new Fireball((int)(this.x+width/2), (int)(this.y+height/2), game,this, xMovement, yMovement, 2));
			shootAlive = true;
		}
	}

	@Override
	public void shootRemoved() {
		shootAlive = false;
	}

	@Override
	public void doneKill(CombatObject combatObject) {
		
	}

}
