package progprak.gruppe53.sprites;

import java.util.ListIterator;
import java.util.Vector;

import progprak.gruppe53.game.Collidable;
import progprak.gruppe53.game.CollisionEvent;
import progprak.gruppe53.game.DamageCollisionEvent;
import progprak.gruppe53.game.GameLogic;

abstract public class CombatObject extends Sprite implements Collidable{

	private static final long serialVersionUID = -6996362585746746561L;
	
	protected int faction = 0;

	protected double health = 1;
	protected double maxHealth = 1;
	protected long lastDamage = 0L;
	
	
	protected transient CollisionEvent collisionEvent;

	protected boolean handleEvents = true;
	
	
	protected transient GameLogic gameLogic;
	
	public CombatObject(int x, int y, String imagePath,GameLogic gameLogic) {
		super(x, y, imagePath);
		this.gameLogic = gameLogic;
		doInitalizations();
	}

	protected void doInitalizations() {
		initCollisionEvent();
	}
	protected void initCollisionEvent() {
		collisionEvent = new DamageCollisionEvent(10,faction,this);
	}

	@Override
	public void doLogic(long delta) {

	}
	
	public void testForCollision(){
		//Vector<Sprite> cs = game.testForCollision(this,getMaxX(),getMinX(),getMaxY(),getMinY(),dx,dy);
		Vector<Sprite> cs = gameLogic.testForCollision(this);
		for (ListIterator<Sprite> it = cs.listIterator(); it.hasNext();) {
			Sprite s = it.next();
			CollisionEvent ce = ((Collidable)s).getCollisionEvent();
			handleCollisionEvent(ce);
		}
		
	}
	@Override
	public CollisionEvent getCollisionEvent(){
		return collisionEvent;
		
	}
	protected void handleDamageEvent(CollisionEvent ce){
		DamageCollisionEvent de = (DamageCollisionEvent) ce ;
		if(!(faction == 0 || de.getFaction() ==0)){
			if(faction != de.getFaction() && faction != 0 && de.getFaction() != 0){
				long current = System.nanoTime();
				if((current - lastDamage)> 1e9){
					lastDamage = current;
					((CombatObject)de.getActor()).doneDamage();
					if((health -= (de.getDamage()*damageReduce())) <= 0){
						((CombatObject)de.getActor()).doneKill(this);
						handleDie();
					}
				}
			}
		}
	}
	
	protected double damageReduce() {
		return 1;
	}

	protected void doneKill(CombatObject combatObject) {
		
	}

	protected void handlePickupEvent(CollisionEvent ce){
	}
	
	protected void handleDie() {
		gameLogic.removeSprite(this);
	}

	protected void handleCollisionEvent(CollisionEvent ce) {
		if(handleEvents){
			if(ce.getEvent() == CollisionEvent.EVENT_DAMAGE){
				handleDamageEvent(ce);
			}
			else if(ce.getEvent() == CollisionEvent.EVENT_MASSIVE){
				handleMassiveEvent(ce);
				
			}

			else if(ce.getEvent() == CollisionEvent.EVENT_PICKUP){
				handlePickupEvent(ce);
			}
			else if (ce.getEvent() == CollisionEvent.EVENT_TELEPORT) {
				handleTeleportEvent(ce.getNewX(),ce.getNewY());

			}
			else if (ce.getEvent() == CollisionEvent.EVENT_GOAL){
				handleGoalEvent(ce);
			}
			else if(ce.getEvent() == CollisionEvent.EVENT_SWITCH_LEVEL){
				handleSwitchLevelEvent(ce);
				
			}
		}
		
	}

	protected void handleGoalEvent(CollisionEvent ce) {
		// TODO Auto-generated method stub
		
	}

	protected void handleTeleportEvent(int newX, int newY) {
		setX(newX);
		setY(newY);
	}

	protected void handleSwitchLevelEvent(CollisionEvent ce) {		
	}

	protected void handleMassiveEvent(CollisionEvent ce) {
		if(ce.getDirection() == CollisionEvent.DIRECTION_HORIZONTAL)dx = 0;
		else if(ce.getDirection() == CollisionEvent.DIRECTION_VERTICAL)dy = 0;		
	}

	public void resetHandleEvents() {
		handleEvents = true;
	}
	protected void doneDamage() {
		
	}
}
