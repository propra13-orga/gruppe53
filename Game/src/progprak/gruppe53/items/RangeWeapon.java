package progprak.gruppe53.items;

import progprak.gruppe53.game.GameLogic;
import progprak.gruppe53.game.Shooter;
import progprak.gruppe53.sprites.CombatObject;
import progprak.gruppe53.sprites.Fireball;

public class RangeWeapon extends Weapon implements Shooter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6124980088772086796L;
	protected long last = 0;
	protected long recharge = 0;

	public RangeWeapon(int x, int y, String imagePath, GameLogic gameLogic) {
		super(x, y, imagePath, gameLogic);
	}

	@Override
	public void doLogic(long delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void attack(boolean attack) {
		long now = System.nanoTime();
		drawWeapon(attack);
		if ((now - last) >= (recharge)) {
			super.attack(attack);
			if (gameLogic.getHero().getTalentTree().getTalent(10) == 1) {
				if (attack && gameLogic.getHero().getMana() >= manaCost * 3) {
					if (gameLogic.getHero().getLastDx() < 0) {
						gameLogic.addSprite(new Fireball(
								(int) this.getX() - 12, (int) this.getY(),
								gameLogic, this, gameLogic.getHero()
										.getLastDx() * 2.5, gameLogic.getHero()
										.getLastDy() * 2.5, gameLogic.getHero()
										.getFaction()));
						gameLogic.addSprite(new Fireball(
								(int) this.getX() - 12, (int) this.getY(),
								gameLogic, this, gameLogic.getHero()
										.getLastDx() * 2, gameLogic
										.getHero().getLastDy() * 2.5 + 1,
								gameLogic.getHero().getFaction()));
						gameLogic.addSprite(new Fireball(
								(int) this.getX() - 12, (int) this.getY(),
								gameLogic, this, gameLogic.getHero()
										.getLastDx() * 2, gameLogic
										.getHero().getLastDy() * 2.5 - 1,
								gameLogic.getHero().getFaction()));
					} else {
						gameLogic.addSprite(new Fireball(
								(int) this.getX() + 12, (int) this.getY(),
								gameLogic, this, gameLogic.getHero()
										.getLastDx() * 2.5, gameLogic.getHero()
										.getLastDy() * 2.5, gameLogic.getHero()
										.getFaction()));
						gameLogic.addSprite(new Fireball(
								(int) this.getX() + 12, (int) this.getY(),
								gameLogic, this, gameLogic.getHero()
										.getLastDx() * 2, gameLogic
										.getHero().getLastDy() * 2.5 + 1,
								gameLogic.getHero().getFaction()));
						gameLogic.addSprite(new Fireball(
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
					gameLogic.addSprite(new Fireball((int) this.getX() - 12,
							(int) this.getY(), gameLogic, this, gameLogic
									.getHero().getLastDx() * 2.5, gameLogic
									.getHero().getLastDy() * 2.5, gameLogic
									.getHero().getFaction()));
				} else {
					gameLogic.addSprite(new Fireball((int) this.getX() + 12,
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

	@Override
	public void shootRemoved() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doneKill(CombatObject combatObject) {
		owner.doneKill(combatObject);
	}
}
