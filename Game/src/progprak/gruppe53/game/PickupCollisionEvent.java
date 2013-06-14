package progprak.gruppe53.game;

import progprak.gruppe53.items.Item;

public class PickupCollisionEvent extends CollisionEvent {

	private Item item;
	
	public PickupCollisionEvent(Item item) {
		super(CollisionEvent.EVENT_PICKUP,item);
		this.item = item;
	}
	
	/**
	 * @return the item
	 */
	public Item getItem(){
		return item;
	}
	

}
