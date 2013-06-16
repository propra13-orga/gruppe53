package progprak.gruppe53.levelEditor;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class AttributeDialog extends JDialog implements ActionListener {
	private static final long serialVersionUID = 1;
	
	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField3;
	private JTextField textField4;
	private JTextField textField5;
	public static int attribute1;
	public static int attribute2;
	public static int attribute3;
	public static int attribute4;
	public static String attribute5 = "levels/";
	public static double attribute6;
	public static double attribute7;
	private LevelEditor le;

	private String spriteType = "";
	private String btnString1 = "Ok";
	private String btnString2 = "Cancel";
		
	private static final int DEFAULT_WINDOW_WIDTH = 200;
	private static final int DEFAULT_WINDOW_HEIGHT = 215;

	public AttributeDialog(LevelEditor parent, String spriteType) {
		super();
		le = parent;
		this.spriteType = spriteType;
		
		setTitle("AttributeDialog");
		setSize(DEFAULT_WINDOW_WIDTH, DEFAULT_WINDOW_HEIGHT);
	    setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
	    setLocationRelativeTo(null);
	    setResizable(false);
	     
		
		
		getContentPane().setLayout(new BorderLayout());
		textField1 = new JTextField(10);
		textField2 = new JTextField(10);
		textField3 = new JTextField(10);
		textField4 = new JTextField(10);
		textField5 = new JTextField(10);
		String attributeLabel1 = "XSpeed:";
		JLabel label1 = new JLabel(attributeLabel1);
		String attributeLabel2 = "YSpeed:";
		JLabel label2 = new JLabel(attributeLabel2);
		String attributeLabel3 = "XRespawn:";
		JLabel label3 = new JLabel(attributeLabel3);
		String attributeLabel4 = "YRespawn:";
		JLabel label4 = new JLabel(attributeLabel4);
		String attributeLabel5 = "TeleportXLocation:";
		JLabel label5 = new JLabel(attributeLabel5);
		String attributeLabel6 = "TeleportYLocation:";
		JLabel label6 = new JLabel(attributeLabel6);
		String attributeLabel7 = "LevelPath:";
		JLabel label7 = new JLabel(attributeLabel7);
		String attributeLabel8 = "Not required";
		JLabel label8 = new JLabel(attributeLabel8);
		JLabel label9 = new JLabel(attributeLabel8);
		JLabel label10 = new JLabel(attributeLabel8);
		String attributeLabel9 = "Direction:";
		JLabel label11 = new JLabel(attributeLabel9);
		String msgString1 = "Please enter Attributes:";
		JLabel titelLabel = new JLabel(msgString1);
		JButton saveButton = new JButton(btnString1);
		saveButton.setActionCommand(btnString1);
		saveButton.addActionListener(this);
		JButton cancelButton = new JButton(btnString2);
		cancelButton.setActionCommand(btnString2);
		cancelButton.addActionListener(this);
		JPanel text = new JPanel();
		text.setLayout(new BoxLayout(text,BoxLayout.Y_AXIS));
		text.add(titelLabel);
		JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons,BoxLayout.X_AXIS));
		buttons.add(saveButton);
		buttons.add(cancelButton);
		add(text,BorderLayout.CENTER);
		add(buttons,BorderLayout.SOUTH);
		setVisible(true);
		
		
		if (spriteType == "trapFireball") {
			text.add(label1);
			text.add(textField1);
			text.add(label2);
			text.add(textField2);
			text.add(label3);
			text.add(textField3);
			text.add(label4);
			text.add(textField4);
			setVisible(true);
		}
		else if (spriteType == "portal") {
			text.add(label5);
			text.add(textField1);
			text.add(label6);
			text.add(textField2);
			text.add(label8);
			text.add(textField3);
			text.add(label9);
			text.add(textField4);
			setVisible(true);
		}
		else if (spriteType =="levelSwitch") {
			text.add(label7);
			text.add(textField5);
			text.add(label8);
			text.add(textField2);
			text.add(label9);
			text.add(textField3);
			text.add(label10);
			text.add(textField4);
			setVisible(true);
		}
		else if (spriteType =="wallLevelSwitch") {
			text.add(label11);
			text.add(textField1);
			text.add(label7);
			text.add(textField5);
			text.add(label9);
			text.add(textField3);
			text.add(label10);
			text.add(textField4);
			setVisible(true);
		}
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand() == btnString2){
			if (le.isEdited == true) {
				le.isEdited = false;
			}
			dispose();
			
		}
		else if(e.getActionCommand() == btnString1){
			if (le.isEdited == true) {
				le.deleteEdited();
				le.isEdited = false;
			}
			if (spriteType == "trapFireball") {
				attribute6 = Double.parseDouble(textField1.getText());
				attribute7 = Double.parseDouble(textField2.getText());
				attribute1 = Integer.parseInt(textField3.getText());
				attribute2 = Integer.parseInt(textField4.getText());				
			}
			else if (spriteType == "portal") {
				attribute1 = Integer.parseInt(textField1.getText());
				attribute2 = Integer.parseInt(textField2.getText());
			}
			else if (spriteType == "levelSwitch") {
				attribute5 += textField5.getText() + ".xml";
			}
			else if (spriteType == "wallLevelSwitch") {
				attribute1 = Integer.parseInt(textField1.getText());
				attribute5 += textField5.getText() + ".xml";
			}
			le.addSprite(spriteType);
			dispose();
			
		}
	}
	
}