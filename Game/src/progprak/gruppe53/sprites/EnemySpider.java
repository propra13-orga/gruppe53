package progprak.gruppe53.sprites;

import progprak.gruppe53.game.CollisionEvent;
import progprak.gruppe53.game.GameLogic;
import progprak.gruppe53.game.Shooter;

public class EnemySpider extends Enemy implements Shooter{


	private static final long serialVersionUID = 2838843254158087591L;

	private long lastShot;
	private double slowFactor;
	
	
	public EnemySpider(int x, int y, GameLogic gameLogic) {
		super(x, y, "images/spider.png",gameLogic);
		dx = Math.random()*3-1.5;
		dy = Math.random()*3-1.5;
		this.slowFactor = 0.5;
		health = 20;
		damageType = 2;
	}
	
	@Override
	protected void handleMassiveEvent(CollisionEvent ce){
		if(ce.getDirection() == CollisionEvent.DIRECTION_HORIZONTAL)dx *= -1;
		if(ce.getDirection() == CollisionEvent.DIRECTION_VERTICAL)dy *= -1;
		handleEvents = false;
	}

	public void doLogic(long delta){
		long current = System.nanoTime();
		if(current - lastShot >= 1e9/2){
			shootWeb(gameLogic.getHero().getX(),gameLogic.getHero().getY());
			lastShot = current;
		}
	}
	
	private void shootWeb(double xPosition, double yPosition){
		double x = xPosition - getX();
		double y = yPosition - getY();
		double length = Math.pow((Math.pow(x,2) + Math.pow(y,2)),0.5);
		x = x/length;
		y = y/length;
		gameLogic.addSprite(new SpiderWebBall((int)getX(),(int)getY(),gameLogic,this,x*2,y*2,2,slowFactor));
	}
	
	@Override
	public void doneKill(CombatObject combatObject) {
	}

	@Override
	public void shootRemoved() {
		// TODO Auto-generated method stub
		
	}
	
}