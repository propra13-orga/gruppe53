package progprak.gruppe53.items;

import progprak.gruppe53.game.CollisionEvent;
import progprak.gruppe53.game.FireballTrap;
import progprak.gruppe53.game.Game;

public class LongBow extends Weapon{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4274376584235570942L;
	
	private Game game;

	public LongBow(int x, int y,Game game) {
		super(x, y, "");
		this.game = game;
		
		doInitalizations();

	}
	
	public LongBow(){
		super(0,0, "");
		collisionEvent = new CollisionEvent(CollisionEvent.EVENT_NOTHING);
		draw = false;
		doInitalizations();
	}
	
	private void doInitalizations(){
		price = 100;
	}

	@Override
	public void doLogic(long delta) {

		
	}

	@Override
	public void attack(boolean attack) {
		if(attack)
		{
			game.getGameLogic().getActors().add(new FireballTrap(game.getGameLogic().getHero().getXPos(),
					game.getGameLogic().getHero().getYPos(), game, game.getGameLogic().getHero().getLastDx(),
					game.getGameLogic().getHero().getLastDy(), game.getGameLogic().getHero().getFaction()));
		}
	}

}
