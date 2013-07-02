package progprak.gruppe53.game;

import java.io.Serializable;

import progprak.gruppe53.items.HealthPotion;
import progprak.gruppe53.items.Item;
import progprak.gruppe53.items.ManaPotion;
import progprak.gruppe53.items.RejuvenationPotion;
import progprak.gruppe53.sprites.Hero;

public class Shop implements Serializable {

	private static final long serialVersionUID = 3087419368009639154L;
	private Item[] items;
	private int shopSlots = 12;
	private Hero hero;
	private transient GameLogic gameLogic;

	public Shop(Hero hero, GameLogic gameLogic) {
		items = new Item[shopSlots];
		this.hero = hero;
		this.gameLogic = gameLogic;
		items[0] = new HealthPotion(gameLogic);
		items[1] = new ManaPotion(gameLogic);
		items[2] = new RejuvenationPotion(gameLogic);
	}

	public Item[] getItems() {
		return items;

	}

	public void slotClicked(int slotNumber) {
		if (items[slotNumber] != null) {
			hero.setMoney(hero.getMoney() - items[slotNumber].getPrice());
			hero.getInventory().addItem(items[slotNumber]);
			items[slotNumber] = null;
		}
	}

}
