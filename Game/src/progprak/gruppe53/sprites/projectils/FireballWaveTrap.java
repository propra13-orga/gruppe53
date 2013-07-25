package progprak.gruppe53.sprites.projectils;

import progprak.gruppe53.game.GameLogic;
import progprak.gruppe53.game.Shooter;
import progprak.gruppe53.sprites.CombatObject;
/** 
 * A trap that shoots rows of fireballs
 */
public class FireballWaveTrap extends CombatObject implements Shooter {
	private static final long serialVersionUID = 2838843254158087591L;
	
	private long cooldown = 0;
	private int castOrder;
	private int amount;
	private int direction;

	/** 
	 * The constructor for the FireballWaveTrap class
	 * @param x The x-coordinate of the fireballwavetrap
	 * @param y The y-coordinate of the fireballwavetrap
	 * @param gameLogic The game loop
	 * @param amount The amount of fireballs in one row
	 * @param direction The direction of the rows
	 */
	public FireballWaveTrap(int x, int y,GameLogic gameLogic, int amount, int direction) {
		super(x, y, null, gameLogic);
		castOrder = amount;
		this.amount = amount;
		this.direction = direction;
	}
	
	/** 
	 * A second constructor used in the Editor to make the fireballwavetrap visible
	 * for other parameters, refer to the other FireballWaveTrap constructor
	 * @param imagePath The path to the image that displays the fireballwavetrap in the editor
	 */
	public FireballWaveTrap(int x, int y,GameLogic gameLogic, int amount, int direction, String imagePath) {
		super(x, y, imagePath, gameLogic);
		castOrder = amount;
		this.amount = amount;
		this.direction = direction;
	}

	/** 
	 * The game loop
	 * @param delta The time the last loop took
	 */
	@Override
	public void doLogic(long delta) {
		if(System.nanoTime() >= cooldown){
			if (direction == 1) {
				if (castOrder == amount) {
					gameLogic.addSprite(new Fireball((int)(this.getX()+getWidth()/2), (int)(this.getY()+getHeight()/2), gameLogic,this, 1, 0, 2));
					castOrder = 1;
					cooldown = System.nanoTime() + (long)((2L)*1e8);
				}
				else {
					gameLogic.addSprite(new Fireball((int)(this.getX()+getWidth()/2), (int)(this.getY()+getHeight()/2)-16*castOrder, gameLogic,this, 1, 0, 2));
					castOrder = castOrder+1;
					if (castOrder == 8) cooldown = System.nanoTime() + (long)((5L)*1e8);
					else cooldown = System.nanoTime() + (long)((2L)*1e8);
				}
			}
			else if (direction == 2) {
				if (castOrder == amount) {
					gameLogic.addSprite(new Fireball((int)(this.getX()+getWidth()/2), (int)(this.getY()+getHeight()/2), gameLogic,this, 0, -1, 2));
					castOrder = 1;
					cooldown = System.nanoTime() + (long)((2L)*1e8);
				}
				else {
					gameLogic.addSprite(new Fireball((int)(this.getX()+getWidth()/2)+16*castOrder, (int)(this.getY()+getHeight()/2), gameLogic,this, 0, -1, 2));
					castOrder = castOrder+1;
					if (castOrder == 8) cooldown = System.nanoTime() + (long)((5L)*1e8);
					else cooldown = System.nanoTime() + (long)((2L)*1e8);
				}
			}
			else if (direction == 3) {
				if (castOrder == amount) {
					gameLogic.addSprite(new Fireball((int)(this.getX()+getWidth()/2), (int)(this.getY()+getHeight()/2), gameLogic,this, -1, 0, 2));
					castOrder = 1;
					cooldown = System.nanoTime() + (long)((2L)*1e8);
				}
				else {
					gameLogic.addSprite(new Fireball((int)(this.getX()+getWidth()/2), (int)(this.getY()+getHeight()/2)-16*castOrder, gameLogic,this, -1, 0, 2));
					castOrder = castOrder+1;
					if (castOrder == 8) cooldown = System.nanoTime() + (long)((5L)*1e8);
					else cooldown = System.nanoTime() + (long)((2L)*1e8);
				}
			}
			else if (direction == 4) {
				if (castOrder == amount) {
					gameLogic.addSprite(new Fireball((int)(this.getX()+getWidth()/2), (int)(this.getY()+getHeight()/2), gameLogic,this, 0, 1, 2));
					castOrder = 1;
					cooldown = System.nanoTime() + (long)((2L)*1e8);
				}
				else {
					gameLogic.addSprite(new Fireball((int)(this.getX()+getWidth()/2)+16*castOrder, (int)(this.getY()+getHeight()/2), gameLogic,this, 0, 1, 2));
					castOrder = castOrder+1;
					if (castOrder == 8) cooldown = System.nanoTime() + (long)((5L)*1e8);
					else cooldown = System.nanoTime() + (long)((2L)*1e8);
				}
			}
			else if (direction == 5) {
				if (castOrder == amount) {
					gameLogic.addSprite(new Fireball((int)(this.getX()+getWidth()/2), (int)(this.getY()+getHeight()/2), gameLogic,this, 1, 0, 2));
					castOrder = 1;
					cooldown = System.nanoTime() + (long)((2L)*1e8);
				}
				else {
					gameLogic.addSprite(new Fireball((int)(this.getX()+getWidth()/2), (int)(this.getY()+getHeight()/2)+16*castOrder, gameLogic,this, 1, 0, 2));
					castOrder = castOrder+1;
					if (castOrder == 8) cooldown = System.nanoTime() + (long)((5L)*1e8);
					else cooldown = System.nanoTime() + (long)((2L)*1e8);
				}
			}
			else if (direction == 6) {
				if (castOrder == amount) {
					gameLogic.addSprite(new Fireball((int)(this.getX()+getWidth()/2), (int)(this.getY()+getHeight()/2), gameLogic,this, 0, -1, 2));
					castOrder = 1;
					cooldown = System.nanoTime() + (long)((2L)*1e8);
				}
				else {
					gameLogic.addSprite(new Fireball((int)(this.getX()+getWidth()/2)-16*castOrder, (int)(this.getY()+getHeight()/2), gameLogic,this, 0, -1, 2));
					castOrder = castOrder+1;
					if (castOrder == 8) cooldown = System.nanoTime() + (long)((5L)*1e8);
					else cooldown = System.nanoTime() + (long)((2L)*1e8);
				}
			}
			else if (direction == 7) {
				if (castOrder == amount) {
					gameLogic.addSprite(new Fireball((int)(this.getX()+getWidth()/2), (int)(this.getY()+getHeight()/2), gameLogic,this, -1, 0, 2));
					castOrder = 1;
					cooldown = System.nanoTime() + (long)((2L)*1e8);
				}
				else {
					gameLogic.addSprite(new Fireball((int)(this.getX()+getWidth()/2), (int)(this.getY()+getHeight()/2)+16*castOrder, gameLogic,this, -1, 0, 2));
					castOrder = castOrder+1;
					if (castOrder == 8) cooldown = System.nanoTime() + (long)((5L)*1e8);
					else cooldown = System.nanoTime() + (long)((2L)*1e8);
				}
			}
			else if (direction == 8) {
				if (castOrder == amount) {
					gameLogic.addSprite(new Fireball((int)(this.getX()+getWidth()/2), (int)(this.getY()+getHeight()/2), gameLogic,this, 0, 1, 2));
					castOrder = 1;
					cooldown = System.nanoTime() + (long)((2L)*1e8);
				}
				else {
					gameLogic.addSprite(new Fireball((int)(this.getX()+getWidth()/2)-16*castOrder, (int)(this.getY()+getHeight()/2), gameLogic,this, 0, 1, 2));
					castOrder = castOrder+1;
					if (castOrder == 8) cooldown = System.nanoTime() + (long)((5L)*1e8);
					else cooldown = System.nanoTime() + (long)((2L)*1e8);
				}
			}
			
		}
	}

	/** 
	 * Tells the trap that there is no longer a fireball shot by it in the game
	 */
	@Override
	public void shootRemoved() {
		
	}

	/** 
	 * Tells the trap it killed something
	 * @param combatObject The combatobject that got killed
	 */
	@Override
	public void doneKill(CombatObject combatObject) {
		
	}

}
