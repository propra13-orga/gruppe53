package progprak.gruppe53.sprites.projectils;

import progprak.gruppe53.game.GameLogic;
import progprak.gruppe53.game.Shooter;
import progprak.gruppe53.sprites.CombatObject;
import progprak.gruppe53.sprites.PassiveCombatObject;

/** 
 * A trap that shoots fireballs
 */
public class FireballTrap extends PassiveCombatObject implements Shooter {
	private static final long serialVersionUID = 1L;
	
	private boolean shootAlive = false;
	private double xMovement;
	private double yMovement;

	/** 
	 * The contructor for the fireballTrap class
	 * @param x The x-coordinate of the fireballtrap
	 * @param y The y-coordinate of the fireballtrap
	 * @param gameLogic The game loop
	 * @param xMovement The x-speed of the fireballs shot
	 * @param yMovement the y-speed of the fireballs shot
	 */
	public FireballTrap(int x, int y,GameLogic gameLogic, double xMovement, double yMovement) {
		super(x, y, null, gameLogic);
		this.xMovement = xMovement;
		this.yMovement = yMovement;
	}
	
	/** 
	 * A second constructor used in the Editor to make the fireballtrap visible
	 * for other parameters, refer to the other FireballTrap constructor
	 * @param imagePath The path to the image that displays the fireballtrap in the editor
	 */
	public FireballTrap(int x, int y,GameLogic gameLogic, double xMovement, double yMovement, String imagePath) {
		super(x, y, imagePath, gameLogic);
		this.xMovement = xMovement;
		this.yMovement = yMovement;
	}

	/** 
	 * The game loop
	 * @param delta The time the last loop took
	 */
	@Override
	public void doLogic(long delta) {
		if(!shootAlive){
			gameLogic.addSprite(new Fireball((int)(this.getX()+getWidth()/2), (int)(this.getY()+getHeight()/2), gameLogic,this, xMovement, yMovement, 2));
			shootAlive = true;
		}
	}

	/** 
	 * Tells the trap that there is no longer a fireball shot by it in the game
	 */
	@Override
	public void shootRemoved() {
		shootAlive = false;
	}

	/** 
	 * Tells the trap it killed something
	 * @param combatObject The combatobject that got killed
	 */
	@Override
	public void doneKill(CombatObject combatObject) {
		
	}

}
