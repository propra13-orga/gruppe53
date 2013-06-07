package progprak.gruppe53.game;

public class PickupCollisionEvent extends CollisionEvent {

	public PickupCollisionEvent(Item item) {
		super(CollisionEvent.EVENT_PICKUP);
	}

}
