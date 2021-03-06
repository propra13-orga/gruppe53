package progprak.gruppe53.sprites.enemies;

import progprak.gruppe53.game.CollisionEvent;
import progprak.gruppe53.game.GameLogic;
import progprak.gruppe53.game.Shooter;
import progprak.gruppe53.sprites.CombatObject;
import progprak.gruppe53.sprites.projectils.Fireball;

/** 
 * An enemy that shoots fireballs and spawns ghosts. Behaves similar to NecromancerBoss
 * See NecromancerBoss for documentation
 */
public class EnemyNecromancer extends Enemy implements Shooter {
	private static final long serialVersionUID = 1L;
	
	private long cooldown = 0;
	private int castOrder = 3;
	
	public EnemyNecromancer(int x, int y, GameLogic gameLogic) {
		super(x, y, "images/tentacel1.png",gameLogic);
		health = 70;
		dx = Math.random()*5-2.5;
		dy = Math.random()*5-2.5;
		damageType = 1;
		blowResistance = 0.2;
		pierceResistance = 0.3;
		bluntResistance = -0.4;
		fireResistance = 0.2;
		arcaneResistance = 0.3;
	}
	
	private void shootFireball(double xPosition, double yPosition){
		double x = xPosition - getX();
		double y = yPosition - getY();
		double length = Math.pow((Math.pow(x,2) + Math.pow(y,2)),0.5);
		x = x/length;
		y = y/length;
		gameLogic.addSprite(new Fireball((int)getX(),(int)getY(),gameLogic,this,x*3,y*3,2));
		castOrder = castOrder + 1;
		cooldown = System.nanoTime() + (long)((5L)*1e8);
	}
	
	private void spawnGhost(double xPosition, double yPosition) {
		double x = xPosition - getX();
		double y = yPosition - getY();
		double xSpawn = xPosition - 0.9*x;
		double ySpawn = yPosition - 0.9*y;
		gameLogic.addSprite(new EnemyGhost((int)xSpawn,(int)ySpawn,gameLogic));
		castOrder = 0;
		cooldown = System.nanoTime() + (long)((7L)*1e8);
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
			}
			else {
				shootFireball(gameLogic.getHero().getX(),gameLogic.getHero().getY());
			}
		}
			
		
	}
}
