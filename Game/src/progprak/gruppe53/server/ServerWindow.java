package progprak.gruppe53.server;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

public class ServerWindow extends JPanel {

	
	private Server server;
	private JTextArea logWindow;
	
	public ServerWindow(Server server) {
		setPreferredSize(new Dimension(400,600));
		this.server = server;
		doInitializations();
	}
	private void doInitializations(){
		setLayout(new BorderLayout());
		logWindow = new JTextArea();
		logWindow.setEditable(false);
		((DefaultCaret)logWindow.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		JScrollPane logScroll = new JScrollPane(logWindow);
		add(logScroll,BorderLayout.CENTER);
		
	}
	public void log(String logStr){
		logWindow.append(logStr + "\n");
	}


}
