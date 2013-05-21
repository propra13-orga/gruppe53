package progprak.gruppe53;

public class Hero extends Sprite{

	private static final long serialVersionUID = -8077486599395198634L;

	private KeyboardInput keyboardInput;
	
	private Game game;
	
	private int health;
	private long lastDamage = 0L;
	
	public Hero(int x, int y, Game game){
		super(x,y,"images/profi.png");
		this.keyboardInput = game.getKeyboardInput();
		this.game = game;
		health = 12;
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

		CollisionEvent ce;
		if((ce = game.testForCollision(getMaxX()+dx,getMinX()+dx,getMaxY()+dy,getMinY()+dy)) != null){
			if(ce.getEvent() == CollisionEvent.MASSIVE){
				dx = dy = 0;
			}
			else if(ce.getEvent() == CollisionEvent.DAMAGE){
				long current = System.nanoTime();
				if((current - lastDamage)> 1e9){
					lastDamage = current;
					if(--health < 0){
						game.restart();
					}
				}
			}
			else if (ce.getEvent() == CollisionEvent.TELEPORT) {
				x = ce.getNewX();
				y = ce.getNewY();
			}
			else if (ce.getEvent() == CollisionEvent.GOAL){
				game.restart();
			}
			else if(ce.getEvent() == CollisionEvent.SWITCH_LEVEL){
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
}
