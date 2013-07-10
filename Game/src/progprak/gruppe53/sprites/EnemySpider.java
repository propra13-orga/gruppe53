package progprak.gruppe53.sprites;

import progprak.gruppe53.game.CollisionEvent;
import progprak.gruppe53.game.GameLogic;
import progprak.gruppe53.game.Shooter;

public class EnemySpider extends Enemy implements Shooter{


	private static final long serialVersionUID = 2838843254158087591L;

	private long lastShot;
	
	public EnemySpider(int x, int y, GameLogic gameLogic) {
		super(x, y, "images/spider.png",gameLogic);
		dx = Math.random()*3-1.5;
		dy = Math.random()*3-1.5;
		health = 20;
	}
	
	@Override
	protected void handleMassiveEvent(CollisionEvent ce){
		if(ce.getDirection() == CollisionEvent.DIRECTION_HORIZONTAL)dx *= -1;
		if(ce.getDirection() == CollisionEvent.DIRECTION_VERTICAL)dy *= -1;
		handleEvents = false;
	}

	public void doLogic(long delta){
		long current = System.nanoTime();
		if(lastShot - current >= 1e9){
			shootWeb();
			lastShot = System.nanoTime();
		}
	}
	
	private void shootWeb(){
			gameLogic.addSprite(new SpiderWebBall((int)(this.getX()),
											 (int)(this.getY()),
											 gameLogic,
											 this,
											 (int)(1.5*(gameLogic.getHero().getX()-this.getX())),
											 (int)(1.5*(gameLogic.getHero().getY()-this.getY())),
											 2));	
	}
	
	@Override
	public void doneKill(CombatObject combatObject) {
	}

	@Override
	public void shootRemoved() {
		// TODO Auto-generated method stub
		
	}
	
}