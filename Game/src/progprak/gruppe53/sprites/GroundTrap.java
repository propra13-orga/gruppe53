package progprak.gruppe53.sprites;

import progprak.gruppe53.game.GameLogic;

public class GroundTrap extends Trap {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2139487826489572489L;

	public GroundTrap(int x, int y,GameLogic gameLogic) {
		super(x, y, "images/groundTrap1.png",gameLogic);
	}


}
