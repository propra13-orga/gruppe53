package progprak.gruppe53.game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import progprak.gruppe53.items.Item;

public class InventorySlot extends JPanel{

	private static final long serialVersionUID = 2129304223986847233L;

	private boolean used;
	private Item item;
	private SlotAction slotAction;

	public InventorySlot(SlotAction sa){
		slotAction = sa;
		setPreferredSize(new Dimension(40,40));
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				if(slotAction != null && used){
					slotAction.slotClicked((InventorySlot) e.getSource());
				}
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
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
		repaint();
	}

	public void removeItem(){
		item = null;
		this.used = false;
		repaint();
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


	public void setSlotAction(SlotAction slotAction) {
		this.slotAction = slotAction;
	}
	
}