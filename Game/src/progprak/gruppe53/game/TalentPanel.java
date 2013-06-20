package progprak.gruppe53.game;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class TalentPanel extends JPanel {

	
	private JPanel talentPanel;
	private Game game;
	private JButton[] button;
	
	
	public TalentPanel(Game game) {
		
		this.game = game;
		
		doInitalizations();
		
	}


	private void doInitalizations() {
		setLayout(new BorderLayout());
		
		talentPanel = new JPanel();
		add(talentPanel,BorderLayout.CENTER);
		
		talentPanel.setLayout(new GridLayout(0, 3,50,25));
		for(int i=0;i<=11;i++){
			button[i] = new JButton();
			button[i].setIcon(null);
		}
		button[0].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		button[1].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		button[2].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		button[3].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		button[4].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		button[5].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		button[6].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		button[7].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		button[8].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		button[9].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		button[10].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		button[11].addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		for(int i=0 ;i<=11;i++)
			talentPanel.add(button[i]);
			
		
	}

}
