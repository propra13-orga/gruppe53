package progprak.gruppe53.game;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

import progprak.gruppe53.items.Item;

public class InventorySlot extends JPanel{

	private static final long serialVersionUID = 2129304223986847233L;

	private Item item;
	private SlotAction slotAction;

	private int slotNumber;

	/**
	 * @param sa: Requires a SlotAction
	 * @param slotNumber: Has a SlotNumber
	 * Initialises an Inventoryslot with the Number "slotNumber"
	 */
	public InventorySlot(SlotAction sa,int slotNumber){
		slotAction = sa;
		this.slotNumber = slotNumber;
		setPreferredSize(new Dimension(40,40));
		addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				if(slotAction != null){
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
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(item != null){
			g.drawImage(item.getImage().getScaledInstance(40, 40, 1), 0, 0, null);
		}
	}

	/**
	 * @param slotAction: Requires a SlotAction
	 * sets the Objects SlotAction to the parameters' SlotAction
	 */
	public void setSlotAction(SlotAction slotAction) {
		this.slotAction = slotAction;
	}
	
	/**
	 * @param item: Requires an Item
	 * If the current Item is different to the Parameters' Item,
	 * it is switched and calls repaint.
	 */
	public void render(Item item){
		if(this.item != item){
			this.item = item;
			repaint();
		}
	}
	
	/**
	 * @return Returns the Slots slotNumber
	 */
	public int getSlotNumber() {
		return slotNumber;
	}
}