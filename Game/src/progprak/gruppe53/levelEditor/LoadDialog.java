package progprak.gruppe53.levelEditor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoadDialog extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1;
	
	private JTextField textField;
	private LevelEditor le;

	private String btnString1 = "Load";
	private String btnString2 = "Cancel";
	private String levelPath ="";
	
	private static final int DEFAULT_WINDOW_WIDTH = 320;
	private static final int DEFAULT_WINDOW_HEIGHT = 160;

	public LoadDialog(LevelEditor parent) {
		super();
		le = parent;

		setTitle("Savedialog");
		 setSize(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT);
	     setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	     setLocationRelativeTo(null);
	     setResizable(false);

		textField = new JTextField(10);

		String msgString1 = "Please enter filename:";
		JLabel label = new JLabel(msgString1);
		JButton loadButton = new JButton(btnString1);
		loadButton.setActionCommand(btnString1);
		loadButton.addActionListener(this);
		JButton cancelButton = new JButton(btnString2);
		cancelButton.setActionCommand(btnString2);
		cancelButton.addActionListener(this);
		JPanel text = new JPanel();
		text.setLayout(new BoxLayout(text,BoxLayout.Y_AXIS));
		text.setSize(200,40);
		text.add(label);
		text.add(textField);
		JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons,BoxLayout.X_AXIS));
		buttons.add(loadButton);
		buttons.add(cancelButton);
		add(text);
		add(buttons);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == btnString2){
			dispose();
			
		}
		else if(e.getActionCommand() == btnString1){
			levelPath = "levels/" + textField.getText() + ".xml";
			le.loadLevel(levelPath);
			dispose();
			
		}
	}
	
}