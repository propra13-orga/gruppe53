package progprak.gruppe53.game;

public class FireballTrap extends Sprite implements Shooter {

	private Game game;
	private boolean shootAlive = false;
	private double xMovement;
	private double yMovement;

	public FireballTrap(int x, int y,Game game, double xMovement, double yMovement) {
		super(x, y, "images/grass.png");
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

}
