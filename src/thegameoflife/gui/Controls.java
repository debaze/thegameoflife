package thegameoflife.gui;

import java.awt.Dimension;
import javax.swing.JPanel;

public class Controls extends JPanel {
	public Controls() {
		setPreferredSize(new Dimension(100, 0));

		add(new ToggleButton());
		add(new ResetButton());
	}
}