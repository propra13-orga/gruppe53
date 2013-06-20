package progprak.gruppe53.sprites;

import progprak.gruppe53.game.GameLogic;
import progprak.gruppe53.game.Shooter;

public class FireballTrap2 extends Sprite implements Shooter {
	private static final long serialVersionUID = 1L;
	
	private GameLogic gameLogic;
	private boolean shootAlive = false;
	private double xMovement;
	private double yMovement;
	private int xRespawn;
	private int yRespawn;

	public FireballTrap2(int x, int y,GameLogic gameLogic, double xMovement, double yMovement, int xRespawn, int yRespawn) {
		super(x, y, null);
		this.gameLogic = gameLogic;
		this.xMovement = xMovement;
		this.yMovement = yMovement;
		this.xRespawn = xRespawn;
		this.yRespawn = yRespawn;
		gameLogic.addSprite(new Fireball((int)(this.x+width/2), (int)(this.y+height/2), gameLogic,this, xMovement, yMovement, 2));
	}
	public FireballTrap2(int x, int y,GameLogic gameLogic, double xMovement, double yMovement, int xRespawn, int yRespawn, String imagePath) {
		super(x, y, imagePath);
		this.gameLogic = gameLogic;
		this.xMovement = xMovement;
		this.yMovement = yMovement;
		this.xRespawn = xRespawn;
		this.yRespawn = yRespawn;
		//game.getGameLogic().addSprite(new Fireball((int)(this.x+width/2), (int)(this.y+height/2), game,this, xMovement, yMovement, 2));
	}

	@Override
	public void doLogic(long delta) {
		if(!shootAlive){
			gameLogic.addSprite(new Fireball(xRespawn, yRespawn, gameLogic,this, xMovement, yMovement, 2));
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
