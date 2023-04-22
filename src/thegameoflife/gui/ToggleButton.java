package thegameoflife.gui;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import thegameoflife.Options;

// TODO: rework OO
public class ToggleButton extends JButton {
	public ToggleButton(final Options options) {
		super(options.isPlaying ? "Pause" : "Play");

		setPreferredSize(new Dimension(80, 20));
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent event) {
				options.isPlaying = !options.isPlaying;

				setText(options.isPlaying ? "Pause" : "Play");
			}
		});
	}
}