package progprak.gruppe53.game;

public class DamageCollisionEvent extends CollisionEvent {

	
	private int damage;
	private int faction;
	
	public DamageCollisionEvent(int damage, int faction) {
		super(CollisionEvent.EVENT_DAMAGE);
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
