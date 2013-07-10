package progprak.gruppe53.serverOld;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class ServerWindow extends JPanel {

	private JTextArea log;
	private JButton start;
	private JButton stop;
	private Server server;
	
	ServerWindow(Server server){
		this.server = server;
		setPreferredSize(new Dimension(400,600));
		setLayout(new BorderLayout());
		doInitializations();
		
	}
	
	private void doInitializations(){
		log = new JTextArea();
		log.setEditable(false);
		add(log,BorderLayout.CENTER);
		start = new JButton("Start");
		start.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				server.startServer();
				start.setEnabled(false);
				stop.setEnabled(true);
			}
		});
		stop = new JButton("Stop");
		stop.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				server.stopServer();
				start.setEnabled(true);
				stop.setEnabled(false);
			}
		});
		stop.setEnabled(false);
		JPanel btnPanel = new JPanel();
		btnPanel.add(start);
		btnPanel.add(stop);
		add(btnPanel,BorderLayout.NORTH);
	}
	
	public void log(String logStr) {
		log.append(logStr + "\n");
	}
}
