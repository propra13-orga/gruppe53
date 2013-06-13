package progprak.gruppe53.game;

import java.awt.image.BufferedImage;

public abstract class Potion extends Item implements Collidable{
		
		private static final long serialVersionUID = 1L;
		protected CollisionEvent collisionEvent;
		protected Hero owner;
		protected int price = 0;
		
		public Potion(int x, int y, String imagePath) {
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
