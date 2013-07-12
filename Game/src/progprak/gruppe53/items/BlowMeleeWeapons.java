package progprak.gruppe53.items;

import progprak.gruppe53.game.GameLogic;

public abstract class BlowMeleeWeapons extends MeleeWeapons {
	
	protected String damageType = "blow";

	public BlowMeleeWeapons(int x, int y, String imagePath, GameLogic gameLogic) {
		super(x, y, imagePath, gameLogic);
	}

}