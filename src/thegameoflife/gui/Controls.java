package thegameoflife.gui;

import java.awt.*;
import javax.swing.*;

public class Controls extends JPanel {
	public Controls() {
		setPreferredSize(new Dimension(100, 0));
		setBackground(SystemColor.windowBorder);

		add(new ToggleButton());
		add(new ResetButton());
	}
}