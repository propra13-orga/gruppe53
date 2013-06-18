package progprak.gruppe53.game;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JTextArea;

public class SpeechPane extends JTextArea {

	String text = "";
	boolean show;
	
	public SpeechPane() {
		setFont(new Font("Arial", Font.BOLD, 18));
		setForeground(Color.white);
	}
	
	public void setText(String text) {
		this.text = text;
		super.setText(text);
	}
	/*@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.white);
		g.drawString(text, 10, 10);
	}*/
	public void setShow(boolean b) {
		show = b;
	}
	public void render() {
		if(isVisible()!=show){
			setVisible(show);
		}
	}


}
