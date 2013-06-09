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
		for(int i=0;i<10;i++){
			inventorySlot[i] = new InventorySlot(60,60,null);
			this.add(inventorySlot[i]);
		}
//for(int i=0;i<10;i++){

//a.setPreferredSize(new Dimension(60,60));
//if(i%2==0)a.setBackground(Color.magenta);
//else a.setBackground(Color.cyan);
//add(a);
//}

	}

	public void render(){

	}

	public void newItem(Item item){
		int n;
		for(n=0; n<10;n++)
		{
			if(inventorySlot[n].getUsed() == false)
			{
				inventorySlot[n].newItem(item);
				break;
			}
		}
		if(n==10)
			System.out.println("no free Itemslot.");
	}
	
	public void removeItem(Item item)
	{
		int n;
		for(n=0; n<10;n++)
		{
			if(inventorySlot[n].getItem() == item){
				inventorySlot[n].removeItem();
				break;
			}
		}
	}

}