package thegameoflife.gui;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

public class ToggleButton extends JButton {
	public ToggleButton(final Window window) {
		super(window.isRunning ? "Pause" : "Play");

		setPreferredSize(new Dimension(80, 20));
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent event) {
				window.isRunning = !window.isRunning;

				setText(window.isRunning ? "Pause" : "Play");
			}
		});
	}
}