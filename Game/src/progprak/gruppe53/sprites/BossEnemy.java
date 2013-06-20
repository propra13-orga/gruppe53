package progprak.gruppe53.sprites;

import progprak.gruppe53.game.GameLogic;

public class BossEnemy extends Enemy {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -243758835967314123L;
	protected String nextLevel;

	public BossEnemy(int x, int y, String imagePath, GameLogic gameLogic) {
		super(x, y, imagePath, gameLogic);
		// TODO Auto-generated constructor stub
	}

	public String getNextLevel() {
		return nextLevel;
	}

}
