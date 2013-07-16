package progprak.gruppe53.sprites;

import progprak.gruppe53.game.GameLogic;
import progprak.gruppe53.game.Shooter;

/** 
 * A different trap that shoots fireballs
 */
public class FireballTrap2 extends CombatObject implements Shooter {
	private static final long serialVersionUID = 1L;
	
	private boolean shootAlive = false;
	private double xMovement;
	private double yMovement;
	private int xRespawn;
	private int yRespawn;

	/** 
	 * The constructor for the fireballTrap class
	 * @param x The x-coordinate of the fireballtrap
	 * @param y The y-coordinate of the fireballtrap
	 * @param gameLogic The game loop
	 * @param xMovement The x-speed of the fireballs shot
	 * @param yMovement The y-speed of the fireballs shot
	 * @param xRespawn The x-coordinate the fireballs are spawned at
	 * @param yRespawn The y-coordinate the fireballs are spawned at
	 */
	public FireballTrap2(int x, int y,GameLogic gameLogic, double xMovement, double yMovement, int xRespawn, int yRespawn) {
		super(x, y, null, gameLogic);
		this.gameLogic = gameLogic;
		this.xMovement = xMovement;
		this.yMovement = yMovement;
		this.xRespawn = xRespawn;
		this.yRespawn = yRespawn;
		gameLogic.addSprite(new Fireball((int)(this.getX()+getWidth()/2), (int)(this.getY()+getHeight()/2), gameLogic,this, xMovement, yMovement, 2));
	}
	

	/** 
	 * A second constructor used in the Editor to make the fireballtrap visible
	 * for other parameters, refer to the other FireballTrap2 constructor
	 * @param imagePath The path to the image that displays the fireballtrap in the editor
	 */
	public FireballTrap2(int x, int y,GameLogic gameLogic, double xMovement, double yMovement, int xRespawn, int yRespawn, String imagePath) {
		super(x, y, imagePath, gameLogic);
		this.gameLogic = gameLogic;
		this.xMovement = xMovement;
		this.yMovement = yMovement;
		this.xRespawn = xRespawn;
		this.yRespawn = yRespawn;
	}

	/** 
	 * The game loop
	 * @param delta The time the last loop took
	 */
	@Override
	public void doLogic(long delta) {
		if(!shootAlive){
			gameLogic.addSprite(new Fireball(xRespawn, yRespawn, gameLogic,this, xMovement, yMovement, 2));
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
