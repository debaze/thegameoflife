package thegameoflife.gui;

import javax.swing.JPanel;
import thegameoflife.Grid;

// TODO: rework OO
public class Controls extends JPanel {
	public Controls(final Grid grid, final Window window) {
		add(new ToggleButton(window));
		add(new ClearButton(grid));
	}
}