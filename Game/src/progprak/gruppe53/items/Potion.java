package progprak.gruppe53.items;

import progprak.gruppe53.game.Collidable;
import progprak.gruppe53.game.GameLogic;
import progprak.gruppe53.sprites.Hero;

public abstract class Potion extends Item implements Collidable{
		
		private static final long serialVersionUID = 1L;
		protected Hero owner;
		protected int price = 0;
		
		/**
		 * Constructor of Potions you can find on the field
		 * @param String imagePath: Requires the images
		 * @param GameLogic: Requires the GameLogic
		 */
		public Potion(int x, int y, String imagePath,GameLogic gameLogic) {
			super(x, y, imagePath,gameLogic);
		}
		// setOwner determines the Hero as the owner of Potions
		public void setOwner(Hero owner){
			this.owner = owner;
		}
		/**
		 * @return the price
		 */
		public int getPrice() {
			return price;
		}

	}
