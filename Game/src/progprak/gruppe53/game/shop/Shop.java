package progprak.gruppe53.game.shop;

import java.io.Serializable;

import progprak.gruppe53.game.GameLogic;
import progprak.gruppe53.items.Item;
import progprak.gruppe53.items.potions.HealthPotion;
import progprak.gruppe53.items.potions.ManaPotion;
import progprak.gruppe53.items.potions.RejuvenationPotion;
import progprak.gruppe53.sprites.characters.Hero;

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
		if (items[slotNumber] != null && hero.getMoney() >= items[slotNumber].getPrice() && hero.getInventory().hasFreeSlot()) {
				if(items[slotNumber] instanceof HealthPotion){
					hero.setMoney(hero.getMoney() - items[slotNumber].getPrice());
					hero.getInventory().addItem(items[slotNumber]);
					items[slotNumber] = null;
					items[slotNumber] = new HealthPotion(gameLogic);
				}
				else if(items[slotNumber] instanceof ManaPotion){
					hero.setMoney(hero.getMoney() - items[slotNumber].getPrice());
					hero.getInventory().addItem(items[slotNumber]);
					items[slotNumber] = null;
					items[slotNumber] = new ManaPotion(gameLogic);
				}
				else if(items[slotNumber] instanceof RejuvenationPotion){
					hero.setMoney(hero.getMoney() - items[slotNumber].getPrice());
					hero.getInventory().addItem(items[slotNumber]);
					items[slotNumber] = null;
					items[slotNumber] = new RejuvenationPotion(gameLogic);
				}
				else{
					hero.setMoney(hero.getMoney() - items[slotNumber].getPrice());
					hero.getInventory().addItem(items[slotNumber]);
					items[slotNumber] = null;
				}
		}
	}
	
	public void newItem(Item newItem){
		if(hasEmptySlot()){
			for(int i=0 ; i< shopSlots; i++){
				if(items[i] == null){
					items[i] = newItem;
					break;
				}
			}
		}
	}
	
	public boolean hasEmptySlot(){
		for(int i = 0; i<shopSlots; i++){
			if(items[i]== null){
				return true;
			}
		}
		return false;
	}

}
