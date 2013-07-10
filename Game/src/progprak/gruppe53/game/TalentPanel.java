package progprak.gruppe53.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
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

	/**
	 * @param game
	 *            : Requires the Game Initialises the TalentPanel for all
	 *            Players
	 */
	public TalentPanel(Game game) {

		this.game = game;
		doInitalizations();

	}

	/**
	 * Does the Objects Initializations
	 */
	private void doInitalizations() {
		setLayout(new BorderLayout());
		setBackground(Color.WHITE);

		talentPanel = new JPanel();
		add(talentPanel, BorderLayout.CENTER);

		talentPanel.setLayout(new GridLayout(0, 3, 50, 25));

		// Creates buttons with the Talents' Icon
		button1 = new JButton(new ImageIcon(
				ImageLoader.loadImage("images/entrance.png")));
		button2 = new JButton(new ImageIcon(
				ImageLoader.loadImage("images/entrance.png")));
		button3 = new JButton(new ImageIcon(
				ImageLoader.loadImage("images/entrance.png")));
		button4 = new JButton(new ImageIcon(
				ImageLoader.loadImage("images/healPotReg.png")));
		button5 = new JButton(new ImageIcon(
				ImageLoader.loadImage("images/manaPotReg.png")));
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

		// Adds an ActionListener to Button1
		button1.addActionListener(new ActionListener() {

			@Override
			// If the Button is clicked,
			// it uses the Players function talentButtonClicked,
			// with ID 0.
			public void actionPerformed(ActionEvent e) {
				game.getPlayer().talentButtonClicked(0);
			}
		});

		// Creates JPanel talent1
		talent1 = new JPanel() {
			/**
		 * 
		 */

			private static final long serialVersionUID = -121259327350214129L;

			@Override
			// Contains Information about the Talent connected with Button1
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLUE);
				g.drawString("Maximal HP (5)", 70, 10);
			}
		};

		// Adds ActionListener to Button2
		button2.addActionListener(new ActionListener() {

			@Override
			// If the Button is clicked,
			// it uses the Players function talentButtonClicked,
			// with ID 1.
			public void actionPerformed(ActionEvent e) {
				game.getPlayer().talentButtonClicked(1);
			}
		});

		// Creates JPanel talent2
		talent2 = new JPanel() {
			/**
		 * 
		 */

			private static final long serialVersionUID = -121259327350214129L;

			@Override
			// Contains Information about the Talent connected with Button2
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLUE);
				g.drawString("Maximal Mana (5)", 65, 10);
			}
		};

		// Adds ActionListener to Button3
		button3.addActionListener(new ActionListener() {

			@Override
			// If the Button is clicked,
			// it uses the Players function talentButtonClicked,
			// with ID 2.
			public void actionPerformed(ActionEvent e) {
				game.getPlayer().talentButtonClicked(2);
			}
		});

		// Creates JPanel talent3
		talent3 = new JPanel() {
			/**
		 * 
		 */

			private static final long serialVersionUID = -121259327350214129L;

			@Override
			// Contains Information about the Talent connected with Button3
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLUE);
				g.drawString("Armor Bonus (1)", 70, 10);
			}
		};
		button4.addActionListener(new ActionListener() {

			@Override
			// If the Button is clicked,
			// it uses the Players function talentButtonClicked,
			// with ID 3.
			public void actionPerformed(ActionEvent e) {
				game.getPlayer().talentButtonClicked(3);
			}
		});

		// Creates JPanel talent4
		talent4 = new JPanel() {
			/**
		 * 
		 */

			private static final long serialVersionUID = -121259327350214129L;

			@Override
			// Contains Information about the Talent connected to Button4
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLUE);
				g.drawString("Upgraded LifePotions (5)", 41, 10);
			}
		};
		button5.addActionListener(new ActionListener() {

			@Override
			// If the Button is clicked,
			// it uses the Players function talentButtonClicked,
			// with ID 4.
			public void actionPerformed(ActionEvent e) {
				game.getPlayer().talentButtonClicked(4);
			}
		});

		// Creates JPanel talent5
		talent5 = new JPanel() {
			/**
		 * 
		 */

			private static final long serialVersionUID = -121259327350214129L;

			@Override
			// Contains Information about the Talent connected to Button5
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLUE);
				g.drawString("Upgraded ManaPotions (5)", 41, 10);
			}
		};
		button6.addActionListener(new ActionListener() {

			@Override
			// If the Button is clicked,
			// it uses the Players function talentButtonClicked,
			// with ID 5.
			public void actionPerformed(ActionEvent e) {
				game.getPlayer().talentButtonClicked(5);
			}
		});

		// Creates JPanel talent6
		talent6 = new JPanel() {
			/**
		 * 
		 */

			private static final long serialVersionUID = -121259327350214129L;

			@Override
			// Contains Information about the Talent connected with Button6
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLUE);
				g.drawString("Upgraded Experiencerate (5)", 38, 10);
			}
		};
		button7.addActionListener(new ActionListener() {

			@Override
			// If the Button is clicked,
			// it uses the Players function talentButtonClicked,
			// with ID 6.
			public void actionPerformed(ActionEvent e) {
				game.getPlayer().talentButtonClicked(6);
			}
		});

		// Creates JPanel talent7
		talent7 = new JPanel() {
			/**
		 * 
		 */

			private static final long serialVersionUID = -121259327350214129L;

			@Override
			// Contains Information about the Talent connected with Button7
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLUE);
				g.drawString("Life Regenration (5)", 60, 10);
			}
		};
		button8.addActionListener(new ActionListener() {

			@Override
			// If the Button is clicked,
			// it uses the Players function talentButtonClicked,
			// with ID 7.
			public void actionPerformed(ActionEvent e) {
				game.getPlayer().talentButtonClicked(7);
			}
		});

		// Creates JPanel talent8
		talent8 = new JPanel() {
			/**
			 * 
			 */

			private static final long serialVersionUID = -121259327350214129L;

			@Override
			// Contains Information about the Talent connected with Button8
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLUE);
				g.drawString("Mana Regenration (7)", 53, 10);
			}
		};

		button9.addActionListener(new ActionListener() {

			@Override
			// If the Button is clicked,
			// it uses the Players function talentButtonClicked,
			// with ID 8.
			public void actionPerformed(ActionEvent e) {
				game.getPlayer().talentButtonClicked(8);
			}
		});

		// Creates JPanel talent9
		talent9 = new JPanel() {
			/**
			 * 
			 */

			private static final long serialVersionUID = -5883970746108560897L;

			@Override
			// Contains Information about the Talent connected with Button9
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLUE);
				g.drawString("Experience Regeneration (7)", 40, 10);
			}
		};

		button10.addActionListener(new ActionListener() {

			@Override
			// If the Button is clicked,
			// it uses the Players function talentButtonClicked,
			// with ID 9.
			public void actionPerformed(ActionEvent e) {
				game.getPlayer().talentButtonClicked(9);
			}
		});

		// Creates JPanel talent 10
		talent10 = new JPanel() {

			/**
			 * 
			 */
			private static final long serialVersionUID = -3102176724700588912L;

			@Override
			// Contains Information about the Talent connected with Button10
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLUE);
				g.drawString("Increased Damage (3)", 50, 10);
			}
		};
		button11.addActionListener(new ActionListener() {

			@Override
			// If the Button is clicked,
			// it uses the Players function talentButtonClicked,
			// with ID 10.
			public void actionPerformed(ActionEvent e) {
				game.getPlayer().talentButtonClicked(10);
			}
		});

		// Creates JPanel talent11
		talent11 = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 584946302480038906L;

			@Override
			// Contains Information about the Talent connected with Button11
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLUE);
				g.drawString("Multifireballs (1)", 60, 10);
			}

		};
		button12.addActionListener(new ActionListener() {

			@Override
			// If the Button is clicked,
			// it uses the Players function talentButtonClicked,
			// with ID 11.
			public void actionPerformed(ActionEvent e) {
				game.getPlayer().talentButtonClicked(11);
			}
		});

		// Creates JPanel talent12
		talent12 = new JPanel() {

			private static final long serialVersionUID = 5522572243012397709L;

			@Override
			// Contains Information about the Talent connected with Button12
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLUE);
				g.drawString("Movementspeed (1)", 50, 10);
			}
		};

		// Disables all Buttons, that shall not be used from beginning on
		button4.setEnabled(false);
		button5.setEnabled(false);
		button6.setEnabled(false);
		button7.setEnabled(false);
		button8.setEnabled(false);
		button9.setEnabled(false);
		button10.setEnabled(false);
		button11.setEnabled(false);
		button12.setEnabled(false);
		// Adds the Buttons and Panels to the talentPanel
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

	/**
	 * @param hero
	 *            : Requires the Hero Manages the ability of the different
	 *            Buttons
	 */
	public void render(Hero hero) {
		if (hero.getTalentTree().getTalent(0) >= 3) {
			button4.setEnabled(true);
		}
		if (hero.getTalentTree().getTalent(1) >= 3) {
			button5.setEnabled(true);
		}
		if (hero.getTalentTree().getTalent(2) >= 1) {
			button6.setEnabled(true);
		}
		if (hero.getTalentTree().getTalent(3) >= 3) {
			button7.setEnabled(true);
		}
		if (hero.getTalentTree().getTalent(4) >= 3) {
			button8.setEnabled(true);
		}
		if (hero.getTalentTree().getTalent(5) >= 3) {
			button9.setEnabled(true);
		}
		if (hero.getTalentTree().getTalent(6) >= 3) {
			button10.setEnabled(true);
		}
		if (hero.getTalentTree().getTalent(7) >= 3) {
			button11.setEnabled(true);
		}
		if (hero.getTalentTree().getTalent(8) >= 3) {
			button12.setEnabled(true);
		}

		if (hero.getTalentTree().getTalent(0) >= 5) {
			button1.setEnabled(false);
		}
		if (hero.getTalentTree().getTalent(1) >= 5) {
			button2.setEnabled(false);
		}
		if (hero.getTalentTree().getTalent(2) >= 1) {
			button3.setEnabled(false);
		}
		if (hero.getTalentTree().getTalent(3) >= 5) {
			button4.setEnabled(false);
		}
		if (hero.getTalentTree().getTalent(4) >= 5) {
			button5.setEnabled(false);
		}
		if (hero.getTalentTree().getTalent(5) >= 5) {
			button6.setEnabled(false);
		}
		if (hero.getTalentTree().getTalent(6) >= 5) {
			button7.setEnabled(false);
		}
		if (hero.getTalentTree().getTalent(7) >= 7) {
			button8.setEnabled(false);
		}
		if (hero.getTalentTree().getTalent(8) >= 7) {
			button9.setEnabled(false);
		}
		if (hero.getTalentTree().getTalent(9) >= 3) {
			button10.setEnabled(false);
		}
		if (hero.getTalentTree().getTalent(10) >= 1) {
			button11.setEnabled(false);
		}
		if (hero.getTalentTree().getTalent(11) >= 1) {
			button12.setEnabled(false);
		}

	}
}
