package progprak.gruppe53.game;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class InventorySlot extends JPanel{

/**
*
*/
private static final long serialVersionUID = 2129304223986847233L;

private boolean used;
private Item item;

	public InventorySlot(){
		setPreferredSize(new Dimension(40,40));
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
	public Item getItem(){
		return item;
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(item != null){
			g.drawImage(item.getImage().getScaledInstance(40, 40, 1), 0, 0, null);
		}
	}
}