package progprak.gruppe53.game;

import java.awt.FlowLayout;
import java.awt.Graphics;

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
			inventorySlot[i] = new InventorySlot();
			this.add(inventorySlot[i]);
		}
	}


	// Add new Item to Inventory(-Slot)
	public void newItem(Item item){
		InventorySlot slot = getFreeSlot();
		if(slot != null){
			newItem(slot,item);
		}
	}
	public void newItem(InventorySlot slot,Item item){
		slot.newItem(item);
		slot.repaint();
	}
	
	// Remove the Item
	public void removeItem(Item item)
	{
		// Check for the right Item
		for(int n=0; n<9;n++)
		{
			//Remove the Item
			if(inventorySlot[n].getItem() == item){
				inventorySlot[n].removeItem();
				inventorySlot[n].repaint();
				break;
			}
		}
	}
	
	// Check for Free InventorySlot
	public InventorySlot getFreeSlot(){
		for(int n=0; n<9; n++){
			if(inventorySlot[n].isUsed() == false){
				return inventorySlot[n];
			}
		}
		return null;
	}
	
	// Resets the Inventory
	public void resetInventory()
	{
		for(int n=0; n<9;n++)
		{
			inventorySlot[n].removeItem();
			inventorySlot[n].repaint();
		}
	}
	@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
		}

}