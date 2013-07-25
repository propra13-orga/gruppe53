package progprak.gruppe53.items.rangedWeapons;

import progprak.gruppe53.game.GameLogic;
import progprak.gruppe53.game.Shooter;
import progprak.gruppe53.items.Weapon;
import progprak.gruppe53.sprites.CombatObject;

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
	public void shootRemoved() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doneKill(CombatObject combatObject) {
		owner.doneKill(combatObject);
	}
}
