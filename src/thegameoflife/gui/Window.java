package thegameoflife.gui;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Properties;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import thegameoflife.Grid;
import thegameoflife.Options;

public class Window extends JFrame {
	public Window(final Properties properties) {
		super("The Game of Life");

		final Options options = new Options(
			true,
			Float.parseFloat(properties.getProperty("DENSITY")),
			Float.parseFloat(properties.getProperty("DECAY")),
			Integer.parseInt(properties.getProperty("FRAMES_PER_SECOND"))
		);
		final Grid grid = new Grid(
			Integer.parseInt(properties.getProperty("COLS")),
			Integer.parseInt(properties.getProperty("ROWS")),
			Integer.parseInt(properties.getProperty("EXTRACT_ERROR_MARGIN")),
			options
		);
		final Canvas canvas = new Canvas(grid, Integer.parseInt(properties.getProperty("SCALE")));
		final JPanel controls = new JPanel();
		final JLabel patternCount = new JLabel("Patterns: 0");
		final JLabel gliderCount = new JLabel("Gliders: 0");

		// FIXME: fix control panel layout
		controls.setLayout(new BoxLayout(controls, BoxLayout.Y_AXIS));
		controls.add(new ToggleButton(options));
		controls.add(new ClearButton(grid));
		controls.add(new DecaySlider(options));
		controls.add(patternCount);
		controls.add(gliderCount);

		setResizable(false);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent event) {
				System.exit(0);
			}
		});
		add(canvas, BorderLayout.WEST);
		add(controls);
		pack();
		setLocationRelativeTo(null);

		for (;;) {
			grid.handleUserActions();

			if (options.isPlaying) grid.evolve();

			grid.extractPatterns();
			grid.recognizeGliderPatterns();
			patternCount.setText("Patterns: " + grid.patterns.size());
			gliderCount.setText("Gliders: " + grid.gliderPatterns);

			canvas.repaint();

			try {
				Thread.sleep(options.frameTime);
			} catch (final InterruptedException exception) {
				break;
			}
		}
	}
}