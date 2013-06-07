package progprak.gruppe53.game;

abstract public class Item extends Sprite implements Collidable {

	private static final long serialVersionUID = -7562614880016250784L;
	protected CollisionEvent collisionEvent;
	protected Hero owner;
	
	public Item(int x, int y, String imagePath) {
		super(x, y, imagePath);
		collisionEvent = new PickupCollisionEvent(this);
	}
	public CollisionEvent getCollisionEvent(){
		return collisionEvent;
	}
	public void setOwner(Hero owner){
		this.owner = owner;
	}

}
