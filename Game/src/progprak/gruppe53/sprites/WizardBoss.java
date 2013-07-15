package progprak.gruppe53.sprites;

import progprak.gruppe53.game.CollisionEvent;
import progprak.gruppe53.game.DamageCollisionEvent;
import progprak.gruppe53.game.GameLogic;
import progprak.gruppe53.game.Shooter;

public class WizardBoss extends BossEnemy implements Shooter {
	private static final long serialVersionUID = 2838843254158087591L;
	
	private long cooldown = 0;
	private int castOrder = 0;
	private double lastdy;
	
	public WizardBoss(int x, int y, GameLogic gameLogic) {
		super(x, y, "images/bug.png",gameLogic);
		health = 30;
		nextLevel = "levels/Level4.xml";
		dx = 0;
		dy = 2;
		damageType = 5;
	}
	
	private void shootGreenFireball(){
		lastdy = dy;
		dy = 0;
		gameLogic.addSprite(new GreenFireball((int)getX()-20,(int)getY()+8,gameLogic,this,-2,0,2));
		castOrder = 0;
		cooldown = System.nanoTime() + (long)((6L)*1e9);
	}
	private void shootFireballs(){
		gameLogic.addSprite(new Fireball((int)getX()-20,(int)getY()+8,gameLogic,this,-2,-0.5,2));
		gameLogic.addSprite(new Fireball((int)getX()-20,(int)getY()+8,gameLogic,this,-2.5,-0.25,2));
		gameLogic.addSprite(new Fireball((int)getX()-20,(int)getY()+8,gameLogic,this,-3,0,2));
		gameLogic.addSprite(new Fireball((int)getX()-20,(int)getY()+8,gameLogic,this,-2.5,0.25,2));
		gameLogic.addSprite(new Fireball((int)getX()-20,(int)getY()+8,gameLogic,this,-2,0.5,2));
		castOrder = castOrder +1;
		cooldown = System.nanoTime() + (long)((1L)*1e9);
	}
	
	@Override
	protected void handleMassiveEvent(CollisionEvent ce){
		if(ce.getDirection() == CollisionEvent.DIRECTION_HORIZONTAL)dx *= -1;
		else if(ce.getDirection() == CollisionEvent.DIRECTION_VERTICAL)dy *= -1;
		handleEvents = false;
	}
	
	@Override
	public void shootRemoved() {
		
	}

	@Override
	public void doneKill(CombatObject combatObject) {
	}
	@Override
	protected void handleDamageEvent(CollisionEvent ce) {
		DamageCollisionEvent de = (DamageCollisionEvent) ce;
		if (de.getFaction() != 1) {
			super.handleDamageEvent(ce);
		}
	}
	
	public void doLogic(long delta) {
		if (System.nanoTime() >= cooldown) {
			if (dy == 0) dy = lastdy;
			if (castOrder == 3) {
				shootGreenFireball();
			}
			else {
				shootFireballs();
			}
			
		}
	}
}