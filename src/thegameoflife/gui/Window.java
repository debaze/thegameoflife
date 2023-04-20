package thegameoflife.gui;

import java.awt.*;
import java.awt.event.*;
import java.util.Properties;
import javax.swing.*;
import thegameoflife.Grid;

public class Window extends JFrame {
	private final long frameTime;
	private boolean isRunning;

	public Window(final Grid grid, final Properties properties) {
		super("The Game of Life");

		isRunning = true;

		setResizable(false);
		setVisible(true);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(final WindowEvent event) {
				System.exit(0);
			}
		});

		final int cellScale = Integer.parseInt(properties.getProperty("CELL_SCALE"));
		final Dimension canvasSize = new Dimension(
			Integer.parseInt(properties.getProperty("GRID_WIDTH")) * cellScale,
			Integer.parseInt(properties.getProperty("GRID_HEIGHT")) * cellScale
		);
		final Canvas canvas = new Canvas(canvasSize, cellScale);
		final Controls controls = new Controls();

		add(canvas, BorderLayout.WEST);
		add(controls, BorderLayout.EAST);

		pack();
		setLocationRelativeTo(null);

		frameTime = 1000 / Integer.parseInt(properties.getProperty("FRAMES_PER_SECOND"));

		for (;;) {
			// TODO: paint canvas
			if (isRunning) {
				// grid.evolve();
				canvas.setGrid(grid);
				canvas.repaint();
			}

			try {
				Thread.sleep(frameTime);
			} catch (InterruptedException exception) {
				break;
			}
		}
	}
}