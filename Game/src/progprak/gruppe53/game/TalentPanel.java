package progprak.gruppe53.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import progprak.gruppe53.sprites.Hero;

public class TalentPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -665767178120915847L;
	private JPanel talentPanel;
	private Game game;
	private JPanel talent1, talent2, talent3, talent4, talent5, talent6,
			talent7, talent8, talent9, talent10, talent11, talent12;
	private JButton button1, button2, button3, button4, button5, button6,
			button7, button8, button9, button10, button11, button12;

	public TalentPanel(Game game) {

		this.game = game;
		doInitalizations();

	}

	private void doInitalizations() {
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);

		talentPanel = new JPanel();
		add(talentPanel, BorderLayout.CENTER);

		talentPanel.setLayout(new GridLayout(0, 3, 50, 25));

		button1 = new JButton(new ImageIcon(
				ImageLoader.loadImage("images/entrance.png")));
		button2 = new JButton(new ImageIcon(
				ImageLoader.loadImage("images/entrance.png")));
		button3 = new JButton(new ImageIcon(
				ImageLoader.loadImage("images/entrance.png")));
		button4 = new JButton(new ImageIcon(
				ImageLoader.loadImage("images/entrance.png")));
		button5 = new JButton(new ImageIcon(
				ImageLoader.loadImage("images/entrance.png")));
		button6 = new JButton(new ImageIcon(
				ImageLoader.loadImage("images/entrance.png")));
		button7 = new JButton(new ImageIcon(
				ImageLoader.loadImage("images/hpReg.png")));
		button8 = new JButton(new ImageIcon(
				ImageLoader.loadImage("images/manaReg.png")));
		button9 = new JButton(new ImageIcon(
				ImageLoader.loadImage("images/expReg.png")));
		button10 = new JButton(new ImageIcon(
				ImageLoader.loadImage("images/entrance.png")));
		button11 = new JButton(new ImageIcon(
				ImageLoader.loadImage("images/entrance.png")));
		button12 = new JButton(new ImageIcon(
				ImageLoader.loadImage("images/goldReg.png")));

		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				game.getPlayer().talentButtonClicked(1);
			}
		});

		talent1 = new JPanel() //{
			/**
			 * 
			 */
			/*private static final long serialVersionUID = 6809444458569759690L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLUE);
				g.drawString("Maximal HP:"
						+ gameLogic.getHero().getTalentTree().getTalent(1)
						+ "/5", 80, 10);
			}
		}*/;
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				game.getPlayer().talentButtonClicked(2);
			}
		});
		talent2 = new JPanel() //{
			/**
			 * 
			 */
			/*private static final long serialVersionUID = -4152248316606299828L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLUE);
				g.drawString("Maximal Mana"
						+ gameLogic.getHero().getTalentTree().getTalent(2)
						+ "/5", 80, 10);
			}
		}*/;
		button3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				game.getPlayer().talentButtonClicked(3);
			}
		});
		talent3 = new JPanel() //{
			/**
			 * 
			 */
			/*private static final long serialVersionUID = -8408144610168840045L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLUE);
				g.drawString("Armor Bonus"
						+ gameLogic.getHero().getTalentTree().getTalent(3)
						+ "/5", 80, 10);
			}
		}*/;
		button4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				game.getPlayer().talentButtonClicked(4);
			}
		});

		talent4 = new JPanel() //{
			/**
			 * 
			 */
			/*private static final long serialVersionUID = 9089404986526239752L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLUE);
				g.drawString("Upgraded Healpotions"
						+ gameLogic.getHero().getTalentTree().getTalent(4)
						+ "/5", 80, 10);
			}
		}*/;
		button5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				game.getPlayer().talentButtonClicked(5);
			}
		});
		talent5 = new JPanel() //{
			/**
			 * 
			 */
			/*private static final long serialVersionUID = 8805351483514477851L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLUE);
				g.drawString("Upgraded Manapotions"
						+ gameLogic.getHero().getTalentTree().getTalent(5)
						+ "/5", 80, 10);
			}
		}*/;
		button6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				game.getPlayer().talentButtonClicked(6);
			}
		});
		talent6 = new JPanel() //{
			/**
			 * 
			 */
			/*private static final long serialVersionUID = 3668354450987356722L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLUE);
				g.drawString("Increased Experiencerate"
						+ gameLogic.getHero().getTalentTree().getTalent(6)
						+ "/5", 80, 10);
			}
		}*/;
		button7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				game.getPlayer().talentButtonClicked(7);
			}
		});
		talent7 = new JPanel() //{
			/**
			 * 
			 */
			/*private static final long serialVersionUID = 276982124425888518L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLUE);
				g.drawString("Health Regeneration"
						+ gameLogic.getHero().getTalentTree().getTalent(7)
						+ "/5", 80, 10);
			}
		}*/;
		button8.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				game.getPlayer().talentButtonClicked(8);
			}
		});
		talent8 = new JPanel() //{
			/**
			 * 
			 */
			/*private static final long serialVersionUID = -121259327350214129L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLUE);
				g.drawString("Mana Regenration"
						+ gameLogic.getHero().getTalentTree().getTalent(8)
						+ "/5", 80, 10);
			}
		}*/;
		button9.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				game.getPlayer().talentButtonClicked(9);
			}
		});
		talent9 = new JPanel() //{
			/**
			 * 
			 */
			/*private static final long serialVersionUID = -5883970746108560897L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLUE);
				g.drawString("Experience Regeneration"
						+ gameLogic.getHero().getTalentTree().getTalent(9)
						+ "/5", 80, 10);
			}
		}*/;
		button10.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				game.getPlayer().talentButtonClicked(10);
			}
		});
		talent10 = new JPanel() //{
			/**
			 * 
			 */
			/*private static final long serialVersionUID = 346982580841007019L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLUE);
				g.drawString("Increased Damage"
						+ gameLogic.getHero().getTalentTree().getTalent(11)
						+ "/5", 80, 10);
			}
		}*/;
		button11.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				game.getPlayer().talentButtonClicked(11);
			}
		});
		talent11 = new JPanel() //{
			/**
			 * 
			 */
			/*private static final long serialVersionUID = -1779312139582535352L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLUE);
				g.drawString("Multifireballs"
						+ gameLogic.getHero().getTalentTree().getTalent(11)
						+ "/1", 80, 10);
			}
		}*/;
		button12.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				game.getPlayer().talentButtonClicked(12);
			}
		});
		talent12 = new JPanel()//{
			/**
			 * 
			 */
			/*private static final long serialVersionUID = 5522572243012397709L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLUE);
				g.drawString("Gold Regeneration" + gameLogic.getHero().getTalentTree().getTalent(12) + "/5", 80, 10);
			}
		}*/;

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

	public void render(Hero hero) {
		if (hero.getTalentTree().getTalent(1) >= 3) {
			button4.setEnabled(true);
		}
		if (hero.getTalentTree().getTalent(2) >= 3) {
			button5.setEnabled(true);
		}
		if (hero.getTalentTree().getTalent(3) >= 1) {
			button6.setEnabled(true);
		}
		if (hero.getTalentTree().getTalent(4) >= 3){
			button7.setEnabled(true);
		}
		if (hero.getTalentTree().getTalent(5) >= 3){
			button8.setEnabled(true);
		}
		if (hero.getTalentTree().getTalent(6) >= 3){
			button9.setEnabled(true);
		}
		if (hero.getTalentTree().getTalent(7) >= 3){
			button10.setEnabled(true);
		}
		if (hero.getTalentTree().getTalent(8) >= 3){
			button11.setEnabled(true);
		}
		if (hero.getTalentTree().getTalent(9) >= 3){
			button12.setEnabled(true);
		}
		

	}
}
