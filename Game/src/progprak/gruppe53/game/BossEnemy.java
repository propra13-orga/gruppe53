package progprak.gruppe53.game;

public class BossEnemy extends Enemy {
	
	protected String nextLevel;

	public BossEnemy(int x, int y, String imagePath, Game game) {
		super(x, y, imagePath, game);
		// TODO Auto-generated constructor stub
	}

	public String getNextLevel() {
		return nextLevel;
	}

}
