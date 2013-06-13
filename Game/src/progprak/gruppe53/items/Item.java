package progprak.gruppe53.items;

import java.awt.image.BufferedImage;

import progprak.gruppe53.game.Collidable;
import progprak.gruppe53.game.CollisionEvent;
import progprak.gruppe53.game.Hero;
import progprak.gruppe53.game.PickupCollisionEvent;
import progprak.gruppe53.game.Sprite;

abstract public class Item extends Sprite implements Collidable {

	private static final long serialVersionUID = -7562614880016250784L;
	protected CollisionEvent collisionEvent;
	protected Hero owner;
	protected int price = 0;
	
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
	public BufferedImage getImage(){
		return image;
	}
	/**
	 * @return the price
	 */
	public int getPrice() {
		return price;
	}

}