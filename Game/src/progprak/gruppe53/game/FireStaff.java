package progprak.gruppe53.game;

public class FireStaff extends Weapon{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4274376584235570942L;
	
	private Game game;

	public FireStaff(int x, int y,Game game) {
		super(x, y, "");
		this.game = game;
		
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
