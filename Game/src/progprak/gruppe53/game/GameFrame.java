package progprak.gruppe53.game;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.ListIterator;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.DefaultCaret;

import progprak.gruppe53.sprites.Hero;
import progprak.gruppe53.sprites.Sprite;

public class GameFrame extends JFrame {

	
	private InfoWindow infoWindow;
	
	private ShopPanel shop;

	private JLayeredPane mainPane;

	private SpeechPane speechPane;
	
	private TalentPanel talentPanel;
	
	
	/*
	 * The Gamepanel
	 */
	private GamePanel gamePanel;

	private Game game;
	
	private Menu menu;

	private JTextArea chatWindow;

	private JTextField chatTextField;
	
	public GameFrame(String title,Game game) {
		super(title);
		this.game = game;
		doInitalizations();
	}

	private void doInitalizations() {
		setResizable(false);
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
		talentPanel = new TalentPanel(game);
		talentPanel.setLocation((int)(mainPane.getPreferredSize().width*0.05),(int)(mainPane.getPreferredSize().height*0.05));
		talentPanel.setSize((int)(mainPane.getPreferredSize().width*0.9),(int)(mainPane.getPreferredSize().height*0.9));
		mainPane.add(talentPanel, new Integer(0));

		
		JPanel chatPanel = new JPanel(new BorderLayout());
		chatWindow = new JTextArea();
		chatWindow.setEditable(false);
		((DefaultCaret)chatWindow.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		JScrollPane chatScroll = new JScrollPane(chatWindow);
		chatPanel.add(chatScroll,BorderLayout.CENTER);
		chatTextField = new JTextField(20);
		
		chatTextField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				game.chat(chatTextField.getText());
				chatTextField.setText(null);
			}
		});
		
		chatPanel.add(chatTextField,BorderLayout.NORTH);
		chatPanel.setPreferredSize(chatPanel.getPreferredSize());
		add(chatPanel,BorderLayout.EAST);
		


		add(mainPane,BorderLayout.CENTER);
		add(menu,BorderLayout.NORTH);
		add(infoWindow,BorderLayout.SOUTH);
		pack();

	}

	private static final long serialVersionUID = -3116185873584567253L;

	public void render(long delta, ArrayList<Sprite> actors, Hero hero) {
		showShop(hero.isShopOpen());
		if(hero.isSpeechPaneShow()){
			showSpeechPane(hero.getSpeechPaneText());
		}
		else {
			hideSpeechPane();
		}
		showTalentTree(hero.isTalentTree());
		gamePanel.render(delta,actors,hero);
		menu.render();
		infoWindow.render(hero);
		speechPane.render();
		shop.render(hero.getShop().getItems());
		talentPanel.render(hero);
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
			mainPane.setLayer(this.talentPanel, 2);
		}
		else {
			mainPane.setLayer(this.talentPanel, 0);
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

	public void setChat(ArrayList<String> chatMessages) {
		chatWindow.setText("");
		for (ListIterator<String> iterator = chatMessages.listIterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			chatWindow.append(string + "\n");
		}
	}


}
