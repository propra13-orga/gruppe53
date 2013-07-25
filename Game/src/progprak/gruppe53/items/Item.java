package progprak.gruppe53.items;

import progprak.gruppe53.game.Collidable;
import progprak.gruppe53.game.CollisionEvent;
import progprak.gruppe53.game.GameLogic;
import progprak.gruppe53.game.PickupCollisionEvent;
import progprak.gruppe53.sprites.Sprite;
import progprak.gruppe53.sprites.characters.Hero;

abstract public class Item extends Sprite implements Collidable {

	private static final long serialVersionUID = -7562614880016250784L;
	protected transient CollisionEvent collisionEvent;
	protected Hero owner;
	protected int price = 0;
	protected transient GameLogic gameLogic;
	
	public Item(int x, int y, String imagePath,GameLogic gameLogic) {
		super(x, y, imagePath);
		this.gameLogic = gameLogic;
		collisionEvent = new PickupCollisionEvent(this);
	}
	public CollisionEvent getCollisionEvent(){
		return collisionEvent;
	}
	public void setOwner(Hero owner){
		this.owner = owner;
		draw = false;
		collisionEvent = new CollisionEvent(CollisionEvent.EVENT_NOTHING, null);
	}
	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}
	public void use(){
		
	}

}
