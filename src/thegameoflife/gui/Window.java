package thegameoflife.gui;

import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Properties;
import javax.swing.JFrame;
import thegameoflife.Grid;

public class Window extends JFrame {
	private final long frameTime;
	public boolean isSimulationRunning;

	public Window(final Properties properties) {
		super("The Game of Life");

		isSimulationRunning = true;

		setResizable(false);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent event) {
				System.exit(0);
			}
		});

		final Grid grid = new Grid(
			Integer.parseInt(properties.getProperty("COLS")),
			Integer.parseInt(properties.getProperty("ROWS")),
			Float.parseFloat(properties.getProperty("DECAY"))
		);
		final Canvas canvas = new Canvas(grid, Integer.parseInt(properties.getProperty("SCALE")), this);
		final Controls controls = new Controls(grid, this);

		add(canvas, BorderLayout.WEST);
		add(controls, BorderLayout.EAST);
		pack();
		setLocationRelativeTo(null);

		frameTime = 1000 / Integer.parseInt(properties.getProperty("FRAMES_PER_SECOND"));

		for (;;) {
			grid.mergeUserCells();

			if (isSimulationRunning) grid.evolve();

			canvas.repaint();

			try {
				Thread.sleep(frameTime);
			} catch (InterruptedException exception) {
				break;
			}
		}
	}
}