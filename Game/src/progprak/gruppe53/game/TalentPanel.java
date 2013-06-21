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
	private transient GameLogic gameLogic;


	public TalentPanel(Hero hero, GameLogic gameLogic) {

		this.hero = hero;
		this.gameLogic = gameLogic;
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
				ImageLoader.loadImage("images/entrance.png")));
		button8 = new JButton(new ImageIcon(
				ImageLoader.loadImage("images/entrance.png")));
		button9 = new JButton(new ImageIcon(
				ImageLoader.loadImage("images/entrance.png")));
		button10 = new JButton(new ImageIcon(
				ImageLoader.loadImage("images/entrance.png")));
		button11 = new JButton(new ImageIcon(
				ImageLoader.loadImage("images/entrance.png")));
		button12 = new JButton(new ImageIcon(
				ImageLoader.loadImage("images/entrance.png")));

		button1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (maxHP <= 4) {
					if (game.getGameLogic().getHero().getTalentPoint() >= 1) {
						maxHP += 1;
						hero.setTalentPoint(-1);

					}
				}
			}
		});

		talent1 = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 6809444458569759690L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLUE);
				g.drawString(maxHP + "/5", 80, 10);
			}
		};
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (maxMana <= 4) {
					if (game.getGameLogic().getHero().getTalentPoint() >= 1) {
						maxMana += 1;
						hero.setTalentPoint(-1);

					}
				}
			}
		});
		talent2 = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -4152248316606299828L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLUE);
				g.drawString(maxMana + "/5", 80, 10);
			}
		};
		button3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (armorBonus <= 2) {
					if (game.getGameLogic().getHero().getTalentPoint() >= 3) {
						armorBonus += 1;
						hero.setTalentPoint(-3);

					}
				}
			}
		});
		talent3 = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -8408144610168840045L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLUE);
				g.drawString(armorBonus + "/5", 80, 10);
			}
		};
		button4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (increasedHPPot <= 5) {
					if (game.getGameLogic().getHero().getTalentPoint() >= 1) {
						increasedHPPot += 1;
						hero.setTalentPoint(-1);

					}
				}
			}
		});
		System.out.println("do");
		if(button4 != null)
			System.out.println("nicht null!");
		talent4 = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 9089404986526239752L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLUE);
				g.drawString(increasedHPPot + "/5", 80, 10);
			}
		};
		button5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (increasedMPPot <= 4) {
					if (game.getGameLogic().getHero().getTalentPoint() >= 1) {
						increasedMPPot += 1;
						hero.setTalentPoint(-1);

					}
				}
			}
		});
		talent5 = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 8805351483514477851L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLUE);
				g.drawString(increasedMPPot + "/5", 80, 10);
			}
		};
		button6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (increasedExpRate <= 4) {
					if (game.getGameLogic().getHero().getTalentPoint() >= 1) {
						increasedExpRate += 1;
						hero.setTalentPoint(-1);

					}
				}
			}
		});
		talent6 = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 3668354450987356722L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLUE);
				g.drawString(increasedExpRate + "/5", 80, 10);
			}
		};
		button7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (hpReg <= 4) {
					if (game.getGameLogic().getHero().getTalentPoint() >= 1) {
						hpReg += 1;
						hero.setTalentPoint(-1);

					}
				}
			}
		});
		talent7 = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 276982124425888518L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLUE);
				g.drawString(hpReg + "/5", 80, 10);
			}
		};
		button8.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (manaReg <= 4) {
					if (game.getGameLogic().getHero().getTalentPoint() >= 1) {
						manaReg += 1;
						hero.setTalentPoint(-1);

					}
				}
			}
		});
		talent8 = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -121259327350214129L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLUE);
				g.drawString(manaReg + "/5", 80, 10);
			}
		};
		button9.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (expReg <= 4) {
					if (game.getGameLogic().getHero().getTalentPoint() >= 1) {
						expReg += 1;
						hero.setTalentPoint(-1);

					}
				}
			}
		});
		talent9 = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -5883970746108560897L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLUE);
				g.drawString(expReg + "/5", 80, 10);
			}
		};
		button10.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (dmgIncrease <= 4) {
					if (game.getGameLogic().getHero().getTalentPoint() >= 1) {
						dmgIncrease += 1;
						hero.setTalentPoint(-1);

					}
				}
			}
		});
		talent10 = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 346982580841007019L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLUE);
				g.drawString(dmgIncrease + "/5", 80, 10);
			}
		};
		button11.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (multiFireBalls == false) {
					if (game.getGameLogic().getHero().getTalentPoint() >= 5) {
						multiFireBalls = true;
						hero.setTalentPoint(-1);

					}
				}
			}
		});
		talent11 = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -1779312139582535352L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLUE);
				if (multiFireBalls)
					g.drawString("1/1", 80, 10);
				else
					g.drawString("0/1", 80, 10);
			}
		};
		button12.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (goldReg <= 4) {
					if (game.getGameLogic().getHero().getTalentPoint() >= 1) {
						goldReg += 1;
						hero.setTalentPoint(-1);

					}
				}
			}
		});
		talent12 = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 5522572243012397709L;

			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				g.setColor(Color.BLUE);
				g.drawString(goldReg + "/5", 80, 10);
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
		if(button4 != null)
			System.out.println("nicht null!2");
	}

	public void render() {
		if (maxHP >= 3) {
			if (button4 == null)
				System.out.println("button4");
			button4.setEnabled(true);
		}
		if (maxMana >= 3) {
			button5.setEnabled(true);
		}
		if (armorBonus >= 1) {
			button6.setEnabled(true);
		}
		if(increasedHPPot >= 3){
			button7.setEnabled(true);
		}
		if(increasedMPPot >= 3){
			button8.setEnabled(true);
		}
		if(increasedExpRate >= 3){
			button9.setEnabled(true);
		}
		if(hpReg >= 3){
			button10.setEnabled(true);
		}
		if(manaReg >= 3){
			button11.setEnabled(true);
		}
		if(expReg >= 3){
			button12.setEnabled(true);
		}
	}

	public int getMaxHP() {
		return maxHP;
	}

	public int getMaxMana() {
		return maxMana;
	}

	public int getManaReg() {
		return manaReg;
	}

	public int getHPReg() {
		return hpReg;
	}

	public int getArmorBonus() {
		return armorBonus;
	}

	public int getGoldReg() {
		return goldReg;
	}

	public int getIncreasedHPPot() {
		return increasedHPPot;
	}

	public int getIncreasedManaPot() {
		return increasedMPPot;
	}

	public int getDMGIncrease() {
		return dmgIncrease;
	}

	public boolean getMultiFireBalls() {
		return multiFireBalls;
	}

	public int getExpReg() {
		return expReg;
	}

	public int getIncreasedExpRate() {
		return increasedExpRate;
	}

}
