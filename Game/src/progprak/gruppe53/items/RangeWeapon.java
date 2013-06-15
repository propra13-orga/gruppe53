package progprak.gruppe53.items;

import progprak.gruppe53.game.CombatObject;
import progprak.gruppe53.game.Fireball;
import progprak.gruppe53.game.Game;
import progprak.gruppe53.game.Shooter;

public class RangeWeapon extends Weapon implements Shooter {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6124980088772086796L;
	protected Game game;
	protected long last = 0;
	protected long recharge = 0;
	
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
		long now = System.nanoTime();
		drawWeapon(attack);
		if((now-last) >= (recharge)){
			super.attack(attack);
			if(attack && game.getGameLogic().getHero().getMana() >= manaCost){
					if(game.getGameLogic().getHero().getLastDx() < 0)
							game.getGameLogic().addSprite(new Fireball((int)this.x-12,
							(int)this.y, game,this, game.getGameLogic().getHero().getLastDx()*2.5,
							game.getGameLogic().getHero().getLastDy()*2.5, game.getGameLogic().getHero().getFaction()));
					else
							game.getGameLogic().addSprite(new Fireball((int)this.x+12,
							(int)this.y, game,this, game.getGameLogic().getHero().getLastDx()*2.5,
							game.getGameLogic().getHero().getLastDy()*2.5, game.getGameLogic().getHero().getFaction()));
				last = now;
			}
		}
	}

	@Override
	public void shootRemoved() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doneKill(CombatObject combatObject) {
		owner.doneKill(combatObject);
		
	}
}
