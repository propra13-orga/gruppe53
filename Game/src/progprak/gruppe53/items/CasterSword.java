package progprak.gruppe53.items;

import progprak.gruppe53.game.CollisionEvent;
import progprak.gruppe53.game.GameLogic;
import progprak.gruppe53.sprites.MagicSwordProjectil;

public class CasterSword extends RangeWeapon{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4274376584235570942L;
	

	public CasterSword(int x, int y,GameLogic gameLogic) {
		super(x, y, "images/castSwordField.png", gameLogic);
		
		doInitalizations();

	}
	
	public CasterSword(GameLogic gameLogic){
		super(0,0, "images/castSwordField.png", gameLogic);
		collisionEvent = new CollisionEvent(CollisionEvent.EVENT_NOTHING,this);
		draw = false;
		doInitalizations();
	}
	
	private void doInitalizations(){
		price = 100;
		manaCost = 30;
		ww = 16;
		wh = 7;
		xOffset = 11;
		yOffset = 7;
		recharge = (long) (2e9);
	}

	@Override
	public void doLogic(long delta) {

		
	}

	@Override
	public void attack(boolean attack){
		long now = System.nanoTime();
		drawWeapon(attack);
		if ((now - last) >= (recharge)) {
			super.attack(attack);
			if (gameLogic.getHero().getTalentTree().getTalent(10) == 1) {
				if (attack && gameLogic.getHero().getMana() >= manaCost * 3) {
					if (gameLogic.getHero().getLastDx() < 0) {
						gameLogic.addSprite(new MagicSwordProjectil(
								(int) this.getX() - 12, (int) this.getY(),
								gameLogic, this, gameLogic.getHero()
										.getLastDx() * 2.5, gameLogic.getHero()
										.getLastDy() * 2.5, gameLogic.getHero()
										.getFaction()));
						gameLogic.addSprite(new MagicSwordProjectil(
								(int) this.getX() - 12, (int) this.getY(),
								gameLogic, this, gameLogic.getHero()
										.getLastDx() * 2, gameLogic
										.getHero().getLastDy() * 2.5 + 1,
								gameLogic.getHero().getFaction()));
						gameLogic.addSprite(new MagicSwordProjectil(
								(int) this.getX() - 12, (int) this.getY(),
								gameLogic, this, gameLogic.getHero()
										.getLastDx() * 2, gameLogic
										.getHero().getLastDy() * 2.5 - 1,
								gameLogic.getHero().getFaction()));
					} else {
						gameLogic.addSprite(new MagicSwordProjectil(
								(int) this.getX() + 12, (int) this.getY(),
								gameLogic, this, gameLogic.getHero()
										.getLastDx() * 2.5, gameLogic.getHero()
										.getLastDy() * 2.5, gameLogic.getHero()
										.getFaction()));
						gameLogic.addSprite(new MagicSwordProjectil(
								(int) this.getX() + 12, (int) this.getY(),
								gameLogic, this, gameLogic.getHero()
										.getLastDx() * 2, gameLogic
										.getHero().getLastDy() * 2.5 + 1,
								gameLogic.getHero().getFaction()));
						gameLogic.addSprite(new MagicSwordProjectil(
								(int) this.getX() + 12, (int) this.getY(),
								gameLogic, this, gameLogic.getHero()
										.getLastDx() * 2, gameLogic
										.getHero().getLastDy() * 2.5 - 1,
								gameLogic.getHero().getFaction()));
					}
					gameLogic.getHero().drainMana(manaCost * 3);
					last = now;

				}

			} 
			else if(attack && gameLogic.getHero().getMana() >= manaCost)
			{
				if (gameLogic.getHero().getLastDx() < 0) {
					gameLogic.addSprite(new MagicSwordProjectil((int) this.getX() - 12,
							(int) this.getY(), gameLogic, this, gameLogic
									.getHero().getLastDx() * 2.5, gameLogic
									.getHero().getLastDy() * 2.5, gameLogic
									.getHero().getFaction()));
				} else {
					gameLogic.addSprite(new MagicSwordProjectil((int) this.getX() + 12,
							(int) this.getY(), gameLogic, this, gameLogic
									.getHero().getLastDx() * 2.5, gameLogic
									.getHero().getLastDy() * 2.5, gameLogic
									.getHero().getFaction()));
				}
				gameLogic.getHero().drainMana(manaCost);
				last = now;
			}
		}
	
	}

}