package progprak.gruppe53.game;

public class BossEnemy extends Enemy {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -243758835967314123L;
	protected String nextLevel;

	public BossEnemy(int x, int y, String imagePath, Game game) {
		super(x, y, imagePath, game);
		// TODO Auto-generated constructor stub
	}

	public String getNextLevel() {
		return nextLevel;
	}

}
