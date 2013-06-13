package progprak.gruppe53.items;

import progprak.gruppe53.game.FireballTrap;
import progprak.gruppe53.game.Game;

public class RangeWeapon extends Weapon {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6124980088772086796L;
	protected Game game;
	
	public RangeWeapon(int x, int y, String imagePath, Game game) {
		super(x, y, imagePath, game);
		this.game = game;
	}

	@Override
	public void doLogic(long delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void attack(boolean attack) {
		super.attack(attack);
		if(attack)
		{
			game.getGameLogic().addSprite(new FireballTrap((int)game.getGameLogic().getHero().getX(),
				(int)game.getGameLogic().getHero().getY(), game, game.getGameLogic().getHero().getLastDx()*1.5,
				game.getGameLogic().getHero().getLastDy()*1.5, game.getGameLogic().getHero().getFaction())
			);
		}
	}
}
