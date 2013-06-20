package progprak.gruppe53.game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class TalentPanel extends JPanel {

	
	private JPanel talentPanel;
	private Game game;
	
	
	public TalentPanel(Game game) {
		
		this.game = game;
		
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
				
			}
		});
		button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		button3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		button4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		button5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		button6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		button7.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		button8.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		button9.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		button10.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		button11.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		button12.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		talentPanel.add(button1);
		talentPanel.add(button2);
		talentPanel.add(button3);
		talentPanel.add(button4);
		talentPanel.add(button5);
		talentPanel.add(button6);
		talentPanel.add(button7);
		talentPanel.add(button8);
		talentPanel.add(button9);
		talentPanel.add(button10);
		talentPanel.add(button11);
		talentPanel.add(button12);
		
		setPreferredSize(getPreferredSize());
	}

}
