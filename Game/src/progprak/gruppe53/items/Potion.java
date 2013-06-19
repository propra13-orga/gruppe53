package progprak.gruppe53.items;

import java.awt.image.BufferedImage;

import progprak.gruppe53.game.Collidable;
import progprak.gruppe53.game.CollisionEvent;
import progprak.gruppe53.game.GameLogic;
import progprak.gruppe53.game.Hero;
import progprak.gruppe53.game.PickupCollisionEvent;

public abstract class Potion extends Item implements Collidable{
		
		private static final long serialVersionUID = 1L;
		protected CollisionEvent collisionEvent;
		protected Hero owner;
		protected int price = 0;
		
		public Potion(int x, int y, String imagePath,GameLogic gameLogic) {
			super(x, y, imagePath,gameLogic);
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
