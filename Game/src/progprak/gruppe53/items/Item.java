package progprak.gruppe53.items;

import java.awt.image.BufferedImage;

import progprak.gruppe53.game.Collidable;
import progprak.gruppe53.game.CollisionEvent;
import progprak.gruppe53.game.Game;
import progprak.gruppe53.game.Hero;
import progprak.gruppe53.game.PickupCollisionEvent;
import progprak.gruppe53.game.Sprite;

abstract public class Item extends Sprite implements Collidable {

	private static final long serialVersionUID = -7562614880016250784L;
	protected CollisionEvent collisionEvent;
	protected Hero owner;
	protected int price = 0;
	protected Game game;
	
	public Item(int x, int y, String imagePath,Game game) {
		super(x, y, imagePath);
		this.game = game;
		collisionEvent = new PickupCollisionEvent(this);
	}
	public CollisionEvent getCollisionEvent(){
		return collisionEvent;
	}
	public void setOwner(Hero owner){
		this.owner = owner;
		collisionEvent = new CollisionEvent(CollisionEvent.EVENT_NOTHING, null);
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
	public void use(){
		
	}

}
