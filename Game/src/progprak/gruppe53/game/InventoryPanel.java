package progprak.gruppe53.game;

import java.awt.FlowLayout;
import javax.swing.JPanel;

public class InventoryPanel extends JPanel {

/**
*
*/
private static final long serialVersionUID = 1L;

private InventorySlot[] inventorySlot;

	public InventoryPanel() {
		super();
		doInitalizations();
	}

	
	private void doInitalizations(){
		setLayout(new FlowLayout());
		inventorySlot = new InventorySlot[10];
		for(int i=0;i<9;i++){
			inventorySlot[i] = new InventorySlot(60,60,null);
			inventorySlot[i] = new InventorySlot(60,60,null);
			this.add(inventorySlot[i]);
		}
	}

	public void render(){

	}

	// Add new Item to Inventory(-Slot)
	public void newItem(InventorySlot slot,Item item){
		slot.newItem(item);
	}
	
	// Remove the Item
	public void removeItem(Item item)
	{
		int n;
		// Check for the right Item
		for(n=0; n<10;n++)
		{
			//Remove the Item
			if(inventorySlot[n].isItem() == item){
				inventorySlot[n].removeItem();
				break;
			}
		}
	}
	
	// Check for Free InventorySlot
	public InventorySlot isFreeSlot(){
		for(int n=0; n<10; n++)
			if(inventorySlot[n].isUsed() == false)
				return inventorySlot[n];
		return null;
	}

}