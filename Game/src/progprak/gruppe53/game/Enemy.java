package progprak.gruppe53.game;

public abstract class Enemy extends CombatObject implements Collidable{
	
	private static final long serialVersionUID = 1L;

	
	public Enemy(int x, int y,String imagePath,Game game){
		super(x,y,imagePath,game);
		faction = 2;
		doInitalizations();
	}

	@Override
	public void doLogic(long delta){
		
	}
	@Override
	protected void handleTeleportEvent(int newX, int newY) {
		super.handleTeleportEvent(newX, newY);
	}

}
