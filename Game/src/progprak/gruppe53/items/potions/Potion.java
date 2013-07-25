package progprak.gruppe53.items.potions;

import progprak.gruppe53.game.Collidable;
import progprak.gruppe53.game.GameLogic;
import progprak.gruppe53.items.Item;
import progprak.gruppe53.sprites.characters.Hero;

public abstract class Potion extends Item implements Collidable{
		
		private static final long serialVersionUID = 1L;
		protected Hero owner;
		protected int price = 0;
		/**
		 * 
		 * @param imagePath Requires the images of Potions.
		 * @param gameLogic Requires the gameLogic.
		 * Constructor of the Potions.
		 */
		public Potion(int x, int y, String imagePath,GameLogic gameLogic) {
			super(x, y, imagePath,gameLogic);
		}
		// only the Hero can use Potion.
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
