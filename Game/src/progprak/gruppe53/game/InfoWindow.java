package progprak.gruppe53.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JProgressBar;


public class InfoWindow extends JPanel {

	private static final long serialVersionUID = 8189052008771431526L;

	private Game game;

	private JProgressBar healthBar;
	
	private JProgressBar manaBar;
	
	private JProgressBar expBar;
	
	private JPanel barPanel;
	
	private InventoryPanel inventoryPanel;
	
	private JPanel infoPanel;

	private int money;

	private int lifes;
	
	public InfoWindow(Game game) {
		this.game = game;
		doInitalizations();
	}

	private void doInitalizations() {
		setBackground(Color.WHITE);
		setLayout(new BorderLayout());
		
		inventoryPanel = new InventoryPanel(game);
		add(inventoryPanel,BorderLayout.CENTER);
		
		barPanel = new JPanel();

		
		healthBar = new JProgressBar();
		healthBar.setForeground(Color.RED);
		Dimension hpb = healthBar.getPreferredSize();
		hpb.width = 400;
		healthBar.setPreferredSize(hpb);
		
		manaBar = new JProgressBar();
		manaBar.setForeground(Color.BLUE);
		Dimension mpb = manaBar.getPreferredSize();
		mpb.width = 400;
		manaBar.setPreferredSize(mpb);

		expBar = new JProgressBar();
		expBar.setForeground(Color.CYAN);
		Dimension exb = expBar.getPreferredSize();
		exb.width = 800;
		expBar.setPreferredSize(exb);
		
		
		barPanel.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.gridx = 0;
		c.gridy = 0;
		barPanel.add(healthBar, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 1;
		c.gridx = 1;
		c.gridy = 0;
		barPanel.add(manaBar, c);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 1;
		barPanel.add(expBar, c);
		
		add(barPanel,BorderLayout.NORTH);
		
		infoPanel = new JPanel(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 7461788149130464989L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLUE);
				g.drawString("Money:" + money
						+ "   Lifes:" + lifes, 2, 10);
			}
		};
		infoPanel.setPreferredSize(new Dimension(800,12));
		add(infoPanel,BorderLayout.SOUTH);
		
		
		
		setPreferredSize(getPreferredSize());
	}

	public void render(GameLogic gameLogic) {
		healthBar.setMaximum((int)gameLogic.getHero().getMaxHealth());
		healthBar.setValue((int)gameLogic.getHero().getHealth());
		manaBar.setMaximum(gameLogic.getHero().getMaxMana());
		manaBar.setValue(gameLogic.getHero().getMana());
		this.money = gameLogic.getHero().getMoney();
		this.lifes = gameLogic.getHero().getLifes();
		inventoryPanel.render(gameLogic.getHero().getInventory().getItems(),gameLogic.getHero().getInventory().getWeapon());
		infoPanel.repaint();
	}

	public InventoryPanel getInventoryPanel(){
		return inventoryPanel;
	}
}
