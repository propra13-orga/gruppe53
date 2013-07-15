package progprak.gruppe53.sprites;

import progprak.gruppe53.game.CollisionEvent;
import progprak.gruppe53.game.GameLogic;
import progprak.gruppe53.game.Shooter;

public class SpawnerBoss extends BossEnemy implements Shooter {
	private static final long serialVersionUID = 1L;
	
	private long cooldown = 0;
	private int castOrder = 3;
	
	public SpawnerBoss(int x, int y, GameLogic gameLogic) {
		super(x, y, "images/totenkopf.png",gameLogic);
		health = 500;
		nextLevel = "levels/CoOp-Goal.xml";
		dx = Math.random()*5-2.5;
		dy = Math.random()*5-2.5;
		damageType = 1;
	}
	
	private void shootFireball(double xPosition, double yPosition){
		double x = xPosition - getX();
		double y = yPosition - getY();
		double length = Math.pow((Math.pow(x,2) + Math.pow(y,2)),0.5);
		x = x/length;
		y = y/length;
		gameLogic.addSprite(new Fireball((int)getX(),(int)getY(),gameLogic,this,x*3,y*3,2));
		castOrder = castOrder + 1;
		cooldown = System.nanoTime() + (long)((9L)*1e9);
	}
	
	private void spawnGhost(double xPosition, double yPosition) {
		double x = xPosition - getX();
		double y = yPosition - getY();
		double xSpawn = xPosition - 0.9*x;
		double ySpawn = yPosition - 0.9*y;
		gameLogic.addSprite(new EnemyGhost((int)xSpawn,(int)ySpawn,gameLogic));
	}
	
	private void spawnCharger(){
		gameLogic.addSprite(new EnemyCharger((int)getX(),(int)getY()-24,gameLogic));
		gameLogic.addSprite(new EnemyCharger((int)getX(),(int)getY()+24,gameLogic));
	}
	
	private void spawnNecromancer(){
		gameLogic.addSprite(new EnemyNecromancer((int)getX(),(int)getY(),gameLogic));
		gameLogic.addSprite(new EnemyOldManNPC(32,32,gameLogic));
		castOrder = 0;
	}
	
	private void spawnSpider(){
		gameLogic.addSprite(new EnemySpider((int)getX()-24,(int)getY(),gameLogic));
		gameLogic.addSprite(new EnemySpider((int)getX()+24,(int)getY(),gameLogic));
		gameLogic.addSprite(new EnemySpider((int)getX(),(int)getY()+24,gameLogic));
		gameLogic.addSprite(new EnemySpider((int)getX(),(int)getY()-24,gameLogic));
	}
	@Override
	protected void handleMassiveEvent(CollisionEvent ce){
		if(ce.getDirection() == CollisionEvent.DIRECTION_HORIZONTAL) {
			if(dx >= 0) dx = -0.5 + Math.random()*-2;
			else dx = 0.5 + Math.random()*2;
		}
		else if(ce.getDirection() == CollisionEvent.DIRECTION_VERTICAL) {
			if(dy >= 0) dy = -0.5 + Math.random()*-2;
			else dy = 0.5 + Math.random()*2;
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
				spawnGhost(gameLogic.getHero().getX(),gameLogic.getHero().getY());
				shootFireball(gameLogic.getHero().getX(),gameLogic.getHero().getY());
			}
			else if(castOrder == 4) {
				spawnCharger();
				shootFireball(gameLogic.getHero().getX(),gameLogic.getHero().getY());
			}
			else if(castOrder == 5) {
				spawnNecromancer();
				shootFireball(gameLogic.getHero().getX(),gameLogic.getHero().getY());
			}
			else if(castOrder == 2){
				spawnSpider();
				shootFireball(gameLogic.getHero().getX(),gameLogic.getHero().getY());
			}
			else {
				shootFireball(gameLogic.getHero().getX(),gameLogic.getHero().getY());
			}
		}
			
		
	}
}
