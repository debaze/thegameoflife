package thegameoflife.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import thegameoflife.Grid;

public class Canvas extends JPanel {
	private final Grid grid;
	private final int scale;
	private final Color cursorColor = new Color(.5f, .5f, .5f);
	private final int[] cursorLocation = new int[2];
	private boolean isHovered = false;

	public Canvas(final Grid grid, final int scale) {
		assert scale != 0 : "The scale must not be null.";

		this.grid = grid;
		this.scale = scale;

		setPreferredSize(new Dimension(grid.cols * scale, grid.rows * scale));
		setBackground(Color.WHITE);
		setCursor(getToolkit().createCustomCursor(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB), new Point(), null));
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(final MouseEvent event) {
				isHovered = true;
			}

			@Override
			public void mouseExited(final MouseEvent event) {
				isHovered = false;
			}

			@Override
			public void mousePressed(final MouseEvent event) {
				if (SwingUtilities.isLeftMouseButton(event)) {
					grid.addCell(cursorLocation[0], cursorLocation[1]);
				}

				if (SwingUtilities.isRightMouseButton(event)) {
					grid.removeCell(cursorLocation[0], cursorLocation[1]);
				}
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(final MouseEvent event) {
				cursorLocation[0] = event.getX() / scale;
				cursorLocation[1] = event.getY() / scale;
			}

			@Override
			public void mouseDragged(final MouseEvent event) {
				cursorLocation[0] = event.getX() / scale;
				cursorLocation[1] = event.getY() / scale;

				if (SwingUtilities.isLeftMouseButton(event)) {
					grid.addCell(cursorLocation[0], cursorLocation[1]);
				}

				if (SwingUtilities.isRightMouseButton(event)) {
					grid.removeCell(cursorLocation[0], cursorLocation[1]);
				}
			}
		});
	}

	public void paintComponent(final Graphics graphics) {
		super.paintComponent(graphics);

		final Graphics2D graphics2d = (Graphics2D) graphics;

		graphics2d.scale(scale, scale);

		for (int i = 0, j; i < grid.cols; i++) {
			for (j = 0; j < grid.rows; j++) {
				if (grid.cells[i][j] == 0) continue;

				graphics2d.setColor(new Color(0, 0, 0, grid.cells[i][j]));
				graphics2d.fillRect(i, j, 1, 1);
			}
		}

		if (isHovered) {
			graphics2d.setColor(cursorColor);
			graphics2d.fillRect(cursorLocation[0], cursorLocation[1], 1, 1);
		}
	}
}