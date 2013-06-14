package progprak.gruppe53.game;

public class DamageCollisionEvent extends CollisionEvent {

	
	private int damage;
	private int faction;
	
	public DamageCollisionEvent(int damage, int faction,Collidable actor) {
		super(CollisionEvent.EVENT_DAMAGE,actor);
		this.damage = damage;
		this.faction = faction;
	}
	
	/**
	 * @return the faction
	 */
	public int getFaction() {
		return faction;
	}

	public int getDamage(){
		return damage;
	}

}
