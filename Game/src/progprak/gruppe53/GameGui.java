package progprak.gruppe53;

import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JPanel;

public class GameGui extends JPanel {

	private static final long serialVersionUID = -8905247101110071179L;

	public GameGui() {
		super();
		doInitializations();
	}

	private void doInitializations() {
		setPreferredSize(new Dimension(800,120));
	}
}
