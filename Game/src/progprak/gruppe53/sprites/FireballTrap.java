package progprak.gruppe53.sprites;

import progprak.gruppe53.game.GameLogic;
import progprak.gruppe53.game.Shooter;

public class FireballTrap extends PassiveCombatObject implements Shooter {
	private static final long serialVersionUID = 1L;
	
	private boolean shootAlive = false;
	private double xMovement;
	private double yMovement;

	public FireballTrap(int x, int y,GameLogic gameLogic, double xMovement, double yMovement) {
		super(x, y, null, gameLogic);
		this.xMovement = xMovement;
		this.yMovement = yMovement;
	}
	public FireballTrap(int x, int y,GameLogic gameLogic, double xMovement, double yMovement, String imagePath) {
		super(x, y, imagePath, gameLogic);
		this.xMovement = xMovement;
		this.yMovement = yMovement;
	}

	@Override
	public void doLogic(long delta) {
		if(!shootAlive){
			gameLogic.addSprite(new Fireball((int)(this.getX()+getWidth()/2), (int)(this.getY()+getHeight()/2), gameLogic,this, xMovement, yMovement, 2));
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
