package progprak.gruppe53.game;

public class LeverCollisionEvent extends CollisionEvent {

	

	private int faction;
	
	public LeverCollisionEvent(int faction,Collidable actor) {
		super(CollisionEvent.EVENT_USE_LEVER,actor);

		this.faction = faction;
	}
	
	/**
	 * @return the faction
	 */
	public int getFaction() {
		return faction;
	}



}
