package progprak.gruppe53;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JPanel;

public class GameGui extends JPanel {

	private static final long serialVersionUID = -8905247101110071179L;

	
	private GameLogic gameLogic;
	
	public GameGui(GameLogic gameLogic) {
		super();
		this.gameLogic = gameLogic;
		doInitializations();
	}

	private void doInitializations() {
		setPreferredSize(new Dimension(800,120));
		setBackground(Color.MAGENTA);
	}
}
