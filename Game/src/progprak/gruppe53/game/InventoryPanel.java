package progprak.gruppe53.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JPanel;

public class InventoryPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InventoryPanel() {
		super();
		doInitalizations();
	}
	
	private void doInitalizations(){
		setLayout(new FlowLayout());
		for(int i=0;i<10;i++){
			JPanel a = new JPanel();
			a.setPreferredSize(new Dimension(60,60));
			if(i%2==0)a.setBackground(Color.magenta);
			else a.setBackground(Color.cyan);
			add(a);
		}
		
	}
}
