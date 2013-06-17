package progprak.gruppe53.game;

public class WizardBoss extends BossEnemy implements Shooter {
	private static final long serialVersionUID = 2838843254158087591L;
	
	private long cooldown = 0;
	private int castOrder = 0;
	
	public WizardBoss(int x, int y, Game game) {
		super(x, y, "images/Monster1.png",game);
		health = 3;
		nextLevel = "levels/Level1.xml";
		dx = 0;
		dy = 2;
	}
	
	private void shootGreenFireball(){
		dy = 0;
		game.getGameLogic().addSprite(new GreenFireball((int)getX()-20,(int)getY()+8,game,this,-2,0,3));
		castOrder = 0;
		cooldown = System.nanoTime() + (long)((6L)*1e9);
	}
	private void shootFireballs(){
		game.getGameLogic().addSprite(new Fireball((int)getX()-20,(int)getY()+8,game,this,-2,-0.5,3));
		game.getGameLogic().addSprite(new Fireball((int)getX()-20,(int)getY()+8,game,this,-2.5,-0.25,3));
		game.getGameLogic().addSprite(new Fireball((int)getX()-20,(int)getY()+8,game,this,-3,0,3));
		game.getGameLogic().addSprite(new Fireball((int)getX()-20,(int)getY()+8,game,this,-2.5,0.25,3));
		game.getGameLogic().addSprite(new Fireball((int)getX()-20,(int)getY()+8,game,this,-2,0.5,3));
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
			if (dy == 0) dy = 2;
			if (castOrder == 3) {
				shootGreenFireball();
			}
			else {
				shootFireballs();
			}
			
		}
	}
}