package progprak.gruppe53.game;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import progprak.gruppe53.sprites.Hero;
import progprak.gruppe53.sprites.Sprite;

public class GameFrame extends JFrame {

	
	private InfoWindow infoWindow;
	
	private ShopPanel shop;

	private JLayeredPane mainPane;

	private SpeechPane speechPane;
	
	
	/*
	 * The Gamepanel
	 */
	private GamePanel gamePanel;

	private Game game;
	
	private Menu menu;
	
	public GameFrame(String title,Game game) {
		super(title);
		this.game = game;
		doInitalizations();
	}

	private void doInitalizations() {
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	
		mainPane = new JLayeredPane();
		gamePanel = new GamePanel();
		menu = new Menu(game);
		infoWindow = new InfoWindow(game);		
		mainPane.setPreferredSize(gamePanel.getPreferredSize());
		gamePanel.setLocation(0, 0);
		gamePanel.setSize(gamePanel.getPreferredSize());
		mainPane.add(gamePanel,new Integer(1));
		shop = new ShopPanel(game);
		shop.setLocation(mainPane.getPreferredSize().width/2 - shop.getPreferredSize().width/2,mainPane.getPreferredSize().height/2 - shop.getPreferredSize().height/2);
		shop.setSize(shop.getPreferredSize());
		mainPane.add(shop,new Integer(0));
		speechPane = new SpeechPane();
		speechPane.setPreferredSize(new Dimension(400,100));
		speechPane.setLocation(mainPane.getPreferredSize().width/2-speechPane.getPreferredSize().width/2,100);
		speechPane.setSize(speechPane.getPreferredSize());
		speechPane.setBackground(Color.BLACK);
		speechPane.setVisible(false);
		mainPane.add(speechPane,new Integer(9));
		


		add(mainPane);
		add(menu,BorderLayout.NORTH);
		add(infoWindow,BorderLayout.SOUTH);
		pack();

	}

	private static final long serialVersionUID = -3116185873584567253L;

	public void render(long delta, ArrayList<Sprite> actors, Hero hero) {
		gamePanel.render(delta,actors,hero);
		menu.render();
		infoWindow.render(hero);
		gamePanel.repaint();
		speechPane.render();
		hero.getTalentTree().getTalentPanel().render();
		showShop(hero.isShop());
		showTalentTree(hero.isTalentTree());
	}

	
	private void showShop(boolean shop) {
		if(shop){
			mainPane.setLayer(this.shop, 2);
		}
		else {
			mainPane.setLayer(this.shop, 0);
		}
	}
	
	private void showTalentTree(boolean talentTree) {
		if(talentTree){
			mainPane.setLayer(game.getGameLogic().getHero().getTalentTree().getTalentPanel(), 2);
		}
		else {
			mainPane.setLayer(game.getGameLogic().getHero().getTalentTree().getTalentPanel(), 0);
		}
	}

	private void showSpeechPane(String text){
		speechPane.setText(text);
		speechPane.setShow(true);
	}


	private void hideSpeechPane() {
		speechPane.setShow(false);
	}
	
	public Game getGame(){
		return game;
	}


}
