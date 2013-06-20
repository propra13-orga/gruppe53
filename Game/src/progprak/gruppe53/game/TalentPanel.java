package progprak.gruppe53.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import progprak.gruppe53.sprites.Hero;

public class TalentPanel extends JPanel {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -665767178120915847L;
	private JPanel talentPanel;
	private Game game;
	private JPanel talent1,talent2,talent3,talent4,talent5,
	talent6,talent7,talent8,talent9,talent10,talent11,talent12;
	
	private int maxHP;
	private int increasedHPPot;
	private int hpReg;
	private int dmgIncrease;
	
	private int maxMana;
	private int increasedMPPot;
	private int manaReg;
	private boolean multiFireBalls;
	
	private int armorBonus;
	private int increasedExpRate;
	private int expReg;
	private int goldReg;
	
	private Hero hero;
	
	public TalentPanel(Game game,Hero hero) {
		
		this.game = game;
		this.hero = hero;
		
		doInitalizations();
		
	}


	private void doInitalizations() {
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);
		
		talentPanel = new JPanel();
		add(talentPanel,BorderLayout.CENTER);
		
		talentPanel.setLayout(new GridLayout(0, 3,50,25));


		JButton button1 = new JButton(new ImageIcon(ImageLoader.loadImage("images/entrance.png")));
		JButton button2 = new JButton(new ImageIcon(ImageLoader.loadImage("images/entrance.png")));
		JButton button3 = new JButton(new ImageIcon(ImageLoader.loadImage("images/entrance.png")));
		JButton button4 = new JButton(new ImageIcon(ImageLoader.loadImage("images/entrance.png")));
		JButton button5 = new JButton(new ImageIcon(ImageLoader.loadImage("images/entrance.png")));
		JButton button6 = new JButton(new ImageIcon(ImageLoader.loadImage("images/entrance.png")));
		JButton button7 = new JButton(new ImageIcon(ImageLoader.loadImage("images/entrance.png")));
		JButton button8 = new JButton(new ImageIcon(ImageLoader.loadImage("images/entrance.png")));
		JButton button9 = new JButton(new ImageIcon(ImageLoader.loadImage("images/entrance.png")));
		JButton button10 = new JButton(new ImageIcon(ImageLoader.loadImage("images/entrance.png")));
		JButton button11 = new JButton(new ImageIcon(ImageLoader.loadImage("images/entrance.png")));
		JButton button12 = new JButton(new ImageIcon(ImageLoader.loadImage("images/entrance.png")));		

		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(maxHP < 5){
					if(game.getGameLogic().getHero().getTalentPoint() >= 1){
						maxHP += 1;
						hero.setTalentPoint(-1);
						
					}
				}
			}
		});
		
		talent1 = new JPanel(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 6809444458569759690L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLUE);
				g.drawString(maxHP + "/5",80,10);
			}
		};
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		talent2 = new JPanel(){
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLUE);
				g.drawString(maxMana + "/5", 80,10);
			}
		};
		button3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		talent3 = new JPanel(){
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLUE);
				g.drawString(armorBonus + "/5", 80,10);
			}
		};
		button4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		talent4 = new JPanel(){
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLUE);
				g.drawString(increasedHPPot + "/5", 80,10);
			}
		};
		button5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		talent5 = new JPanel(){
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLUE);
				g.drawString(increasedMPPot + "/5", 80,10);
			}
		};
		button6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		talent6 = new JPanel(){
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLUE);
				g.drawString(increasedExpRate + "/5", 80,10);
			}
		};
		button7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		talent7 = new JPanel(){
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLUE);
				g.drawString(hpReg + "/5", 80,10);
			}
		};
		button8.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		talent8 = new JPanel(){
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLUE);
				g.drawString(manaReg + "/5", 80,10);
			}
		};
		button9.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		talent9 = new JPanel(){
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLUE);
				g.drawString(expReg + "/5", 80,10);
			}
		};
		button10.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		talent10 = new JPanel(){
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLUE);
				g.drawString(dmgIncrease + "/5", 80,10);
			}
		};
		button11.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		talent11 = new JPanel(){
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLUE);
				if(multiFireBalls)
					g.drawString("1/1", 80,10);
				else
					g.drawString("0/1",80,10);
			}
		};
		button12.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		talent12 = new JPanel(){
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLUE);
				g.drawString(goldReg + "/5", 80,10);
			}
		};
		
		button4.setEnabled(false);
		button5.setEnabled(false);
		button6.setEnabled(false);
		button7.setEnabled(false);
		button8.setEnabled(false);
		button9.setEnabled(false);
		button10.setEnabled(false);
		button11.setEnabled(false);
		button12.setEnabled(false);
		talentPanel.add(button1);
		talentPanel.add(button2);
		talentPanel.add(button3);
		talentPanel.add(talent1);
		talentPanel.add(talent2);
		talentPanel.add(talent3);
		talentPanel.add(button4);
		talentPanel.add(button5);
		talentPanel.add(button6);
		talentPanel.add(talent4);
		talentPanel.add(talent5);
		talentPanel.add(talent6);
		talentPanel.add(button7);
		talentPanel.add(button8);
		talentPanel.add(button9);
		talentPanel.add(talent7);
		talentPanel.add(talent8);
		talentPanel.add(talent9);
		talentPanel.add(button10);
		talentPanel.add(button11);
		talentPanel.add(button12);
		talentPanel.add(talent10);
		talentPanel.add(talent11);
		talentPanel.add(talent12);


		setPreferredSize(getPreferredSize());
	}
	
	public int getMaxHP(){
		return maxHP;
	}
	
	public int getMaxMana(){
		return maxMana;
	}

	public int getManaReg(){
		return manaReg;
	}
	
	public int getHPReg(){
		return hpReg;
	}
	
	public int getArmorBonus(){
		return armorBonus;
	}
	
	public int getGoldReg(){
		return goldReg;
	}
	
	public int getIncreasedHPPot(){
		return increasedHPPot;
	}
	
	public int getIncreasedManaPot(){
		return increasedMPPot;
	}
	
	public int getDMGIncrease(){
		return dmgIncrease;
	}
	
	public boolean getMultiFireBalls(){
		return multiFireBalls;
	}
	
	public int getExpReg(){
		return expReg;
	}
	
	public int getIncreasedExpRate(){
		return increasedExpRate;
	}
}
