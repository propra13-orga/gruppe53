package progprak.gruppe53.server;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.DefaultCaret;

public class ServerWindow extends JPanel {

	
	private Server server;
	private JTextArea logWindow;
	private JTextArea chatWindow;
	private ArrayList<String> chatMessages;
	
	public ServerWindow(Server server) {
		setPreferredSize(new Dimension(400,600));
		this.server = server;
		doInitializations();
	}
	private void doInitializations(){
		chatMessages = new ArrayList<String>();
		setLayout(new BorderLayout());
		logWindow = new JTextArea();
		logWindow.setEditable(false);
		chatWindow = new JTextArea(0,10);
		chatWindow.setEditable(false);
		((DefaultCaret)logWindow.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		((DefaultCaret)chatWindow.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		JScrollPane logScroll = new JScrollPane(logWindow);
		JScrollPane chatScroll = new JScrollPane(chatWindow);
		add(logScroll,BorderLayout.CENTER);
		add(chatScroll,BorderLayout.EAST);
		
	}
	public void log(String logStr){
		logWindow.append(logStr + "\n");
	}
	public void chat(ArrayList<String> chatMessages) {
		this.chatMessages.addAll(chatMessages);
		for (Iterator iterator = chatMessages.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			chatWindow.append(string + "\n");
		}
	}
	public ArrayList<String> getChatMessages(){
		return chatMessages;
	}


}
