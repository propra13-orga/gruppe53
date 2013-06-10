package progprak.gruppe53.game;

import javax.swing.JPanel;

public class InventorySlot extends JPanel{

/**
*
*/
private static final long serialVersionUID = 2129304223986847233L;

private boolean used;
private Item item;

	public InventorySlot(int x, int y, Item item){

	}


	/**
	 * @return is Used
	 */
	public boolean isUsed(){
		return used;
	}

	/**
	 * @param newItem
	 */
	public void newItem(Item newItem){
		item = newItem;
		this.used = true;
	}

	public void removeItem(){
		item = null;
		this.used = false;
	}
	
	/**
	 * @return Item
	 */
	public Item isItem(){
		return item;
	}
}