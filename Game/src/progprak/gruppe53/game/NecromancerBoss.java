package progprak.gruppe53.game;

public class NecromancerBoss extends BossEnemy implements Shooter {
	private static final long serialVersionUID = 2838843254158087591L;
	
	private long cooldown = 0;
	private int castOrder = 3;
	
	public NecromancerBoss(int x, int y, Game game) {
		super(x, y, "images/Monster1.png",game);
		health = 8;
		nextLevel = "levels/Level1.xml";
		dx = Math.random()*5-2.5;
		dy = Math.random()*5-2.5;
	}
	
	private void shootFireball(double xPosition, double yPosition){
		double x = xPosition - getX();
		double y = yPosition - getY();
		double length = Math.pow((Math.pow(x,2) + Math.pow(y,2)),0.5);
		x = x/length;
		y = y/length;
		game.getGameLogic().addSprite(new Fireball((int)getX(),(int)getY(),game,this,x*3,y*3,2));
		castOrder = castOrder + 1;
		cooldown = System.nanoTime() + (long)((5L)*1e8);
	}
	
	private void spawnGhost(double xPosition, double yPosition) {
		double x = xPosition - getX();
		double y = yPosition - getY();
		double xSpawn = xPosition - 0.9*x;
		double ySpawn = yPosition - 0.9*y;
		game.getGameLogic().addSprite(new EnemyGhost((int)xSpawn,(int)ySpawn,game));
		castOrder = 0;
		cooldown = System.nanoTime() + (long)((7L)*1e8);
	}
	
	protected void handleMassiveEvent(CollisionEvent ce){
		if(ce.getDirection() == CollisionEvent.DIRECTION_HORIZONTAL) {
			if(dx >= 0) dx = Math.random()*-3;
			else dx = Math.random()*3;
		}
		else if(ce.getDirection() == CollisionEvent.DIRECTION_VERTICAL) {
			if(dy >= 0) dy = Math.random()*-3;
			else dy = Math.random()*3;
		}
		handleEvents = false;
	}
	
	@Override
	public void shootRemoved() {
	}

	@Override
	public void doneKill(CombatObject combatObject) {
	}
	
	public void doLogic(long delta) {
		if (System.nanoTime() >= cooldown) {
			if (castOrder == 3) {
				spawnGhost(game.getGameLogic().getHero().getX(),game.getGameLogic().getHero().getY());
			}
			else {
				shootFireball(game.getGameLogic().getHero().getX(),game.getGameLogic().getHero().getY());
			}
		}
			
		
	}
}
