package progprak.gruppe53;

import java.util.ListIterator;
import java.util.Vector;

public class Hero extends Sprite{

	private static final long serialVersionUID = -8077486599395198634L;

	private KeyboardInput keyboardInput;
	
	private Game game;
	
	private int health;
	private int maxHealth;
	private long lastDamage = 0L;
	
	public Hero(int x, int y, Game game){
		super(x,y,"images/hero.png");
		this.keyboardInput = game.getKeyboardInput();
		this.game = game;
		maxHealth = 10;
		health = maxHealth;
	}
	
	@Override
	public void doLogic(long delta)
	{
		if(keyboardInput.isUp()){
			dy = -1;
		}
		if(keyboardInput.isDown()){
			dy = 1;
		}
		if(!keyboardInput.isUp() && !keyboardInput.isDown()){
			dy = 0;
		}
		if(keyboardInput.isLeft()){
			dx = -1;
		}
		if(keyboardInput.isRight()){
			dx = 1;
		}
		if(!keyboardInput.isLeft() && !keyboardInput.isRight()){
			dx = 0;
		}

		testForCollision();
		
		
	}
	
	private void testForCollision(){
		Vector<Sprite> cs = game.testForCollision(getMaxX(),getMinX(),getMaxY(),getMinY(),dx,dy);
		for (ListIterator<Sprite> it = cs.listIterator(); it.hasNext();) {
			Sprite s = it.next();
			CollisionEvent ce = ((Collidable)s).getCollisionEvent();
			if(ce.getEvent() == CollisionEvent.EVENT_MASSIVE){
				if(ce.getDirection() == CollisionEvent.DIRECTION_HORIZONTAL)dx = 0;
				else if(ce.getDirection() == CollisionEvent.DIRECTION_VERTICAL)dy = 0;
			}
			else if(ce.getEvent() == CollisionEvent.EVENT_DAMAGE){
				long current = System.nanoTime();
				if((current - lastDamage)> 1e9){
					lastDamage = current;
					if(--health < 0){
						game.loose();
					}
				}
			}
			else if (ce.getEvent() == CollisionEvent.EVENT_TELEPORT) {
				x = ce.getNewX();
				y = ce.getNewY();
			}
			else if (ce.getEvent() == CollisionEvent.EVENT_GOAL){
				game.restart();
			}
			else if(ce.getEvent() == CollisionEvent.EVENT_SWITCH_LEVEL){
				game.switchLevel(ce.getNewLevel());
			}
		}
		
	}

	/**
	 * @return the health
	 */
	public int getHealth() {
		return health;
	}

	public void setX(int newX) {
		x = newX;		
	}
	public void setY(int newY) {
		y = newY;		
	}

	/**
	 * @return the maxHealth
	 */
	public int getMaxHealth() {
		return maxHealth;
	}
}
