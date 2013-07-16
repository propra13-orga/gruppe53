package progprak.gruppe53.sprites;

import progprak.gruppe53.game.GameLogic;
import progprak.gruppe53.game.Shooter;

public class FireballTrap2 extends CombatObject implements Shooter {
	private static final long serialVersionUID = 1L;
	
	private boolean shootAlive = false;
	private double xMovement;
	private double yMovement;
	private int xRespawn;
	private int yRespawn;

	public FireballTrap2(int x, int y,GameLogic gameLogic, double xMovement, double yMovement, int xRespawn, int yRespawn) {
		super(x, y, null, gameLogic);
		this.gameLogic = gameLogic;
		this.xMovement = xMovement;
		this.yMovement = yMovement;
		this.xRespawn = xRespawn;
		this.yRespawn = yRespawn;
		gameLogic.addSprite(new Fireball((int)(this.getX()+getWidth()/2), (int)(this.getY()+getHeight()/2), gameLogic,this, xMovement, yMovement, 2));
	}
	
	public FireballTrap2(int x, int y,GameLogic gameLogic, double xMovement, double yMovement, int xRespawn, int yRespawn, String imagePath) {
		super(x, y, imagePath, gameLogic);
		this.gameLogic = gameLogic;
		this.xMovement = xMovement;
		this.yMovement = yMovement;
		this.xRespawn = xRespawn;
		this.yRespawn = yRespawn;
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
