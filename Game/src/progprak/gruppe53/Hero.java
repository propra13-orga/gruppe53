package progprak.gruppe53;

public class Hero extends Sprite{

	private static final long serialVersionUID = 1L;
	
	private KeyboardInput keyboardInput;
	
	private Game game;
	private int currentLevel = 0;
	
	public Hero(int x, int y, Game game, KeyboardInput keyboardInput){
		super(x,y,"images/profi.png");
		this.keyboardInput = keyboardInput;
		this.game = game;
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
				game.restart();
			}
			else if (ce.getEvent() == CollisionEvent.TO_NEXT_LEVEL) {
				if(this.currentLevel == 0){
					x = 403; //+1 wallbug
					y = 227; //+1 wallbug
					currentLevel = currentLevel+1;
				}
				else if(this.currentLevel == 1){
					x = 117; //+1 wallbug
					y = 355; //+1 wallbug
					currentLevel = currentLevel+1;
				}
				else if(this.currentLevel == 2){
					x = 403; //""
					y = 355; //""
					currentLevel = currentLevel+1;
				}
				else if(this.currentLevel == 3){
					game.restart();
				}
			}
			else if (ce.getEvent() == CollisionEvent.NOTHING)
			{
				
			}
		}
		
	}

}
