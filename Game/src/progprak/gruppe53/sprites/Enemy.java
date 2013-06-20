package progprak.gruppe53.sprites;

import progprak.gruppe53.game.Collidable;
import progprak.gruppe53.game.GameLogic;

public abstract class Enemy extends CombatObject implements Collidable{
	
	private static final long serialVersionUID = 1L;

	
	public Enemy(int x, int y,String imagePath,GameLogic gameLogic){
		super(x,y,imagePath,gameLogic);
		faction = 2;
		doInitalizations();
	}


	@Override
	public void doLogic(long delta){
		
	}
	@Override
	protected void handleTeleportEvent(int newX, int newY) {
	}

}
