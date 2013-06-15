package progprak.gruppe53.game;

public class FireballTrap extends Sprite implements Shooter {

	private Game game;
	private boolean shootAlive = false;

	public FireballTrap(int x, int y,Game game) {
		super(x, y, "images/grass.png");
		this.game = game;
	}

	@Override
	public void doLogic(long delta) {
		if(!shootAlive){
			game.getGameLogic().addSprite(new Fireball((int)(this.x+width/2), (int)(this.y+height/2), game,this, 1, 0, 2));
			shootAlive = true;
		}
	}

	@Override
	public void shootRemoved() {
		shootAlive = false;
	}

}
