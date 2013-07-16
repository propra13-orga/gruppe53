package progprak.gruppe53.game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
/** 
 * A speechdialog for NPCs
 * This class is no longer in use
 */
public class SpeechDialog extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1;
	
	private static final int DEFAULT_WINDOW_WIDTH = 350;
	private static final int DEFAULT_WINDOW_HEIGHT = 90;
	
	public static boolean speechDialogOpened = false;
	
	private String msgString2;

	/** 
	 * The contructor for the speechdialog
	 * @param speech What the NPC says
	 * @param answer what the player can answer
	 */
	public SpeechDialog(String speech, String answer) {
		
		getContentPane().setLayout(new BorderLayout());
		JPanel speechPanel = new JPanel();
		JPanel buttonPanel = new JPanel();
		setTitle("SpeechDialog");
		 setSize(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT);
	     setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	     setLocationRelativeTo(null);
	     setResizable(false);
		String msgString1 = speech;
		JLabel label = new JLabel(msgString1);
		msgString2 = answer;
		JButton answerButton = new JButton(msgString2);
		answerButton.setPreferredSize(new Dimension(70,25));
		answerButton.setActionCommand(msgString2);
		answerButton.addActionListener(this);
		add(speechPanel,BorderLayout.NORTH);
		add(buttonPanel,BorderLayout.CENTER);
		speechPanel.add(label);
		buttonPanel.add(answerButton);
		setVisible(true);
		
		
	}
	
	/** 
	 * the Method that sets the function of the button
	 * @param e the ActionEvent that triggers this method
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == msgString2){
			speechDialogOpened = false;
			dispose();
			
		}
	}
	
}