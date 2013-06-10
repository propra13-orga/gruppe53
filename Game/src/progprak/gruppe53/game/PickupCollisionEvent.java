package progprak.gruppe53.game;

public class PickupCollisionEvent extends CollisionEvent {

	private Item item;
	
	public PickupCollisionEvent(Item item) {
		super(CollisionEvent.EVENT_PICKUP);
		this.item = item;
	}
	
	/**
	 * @return the item
	 */
	public Item getItem(){
		return item;
	}
	

}
