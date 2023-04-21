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
import thegameoflife.Grid;

public class Canvas extends JPanel {
	private static final int[] cursorLocation = new int[2];
	private static final Color cursorColor = new Color(.5f, .5f, .5f);

	private final Grid grid;
	private final int scale;
	private boolean isHovered;

	public Canvas(final Grid grid, final int scale) {
		this.grid = grid;
		this.scale = scale;
		isHovered = false;

		setPreferredSize(new Dimension(grid.cols * scale, grid.rows * scale));
		setBackground(Color.WHITE);
		setCursor(getToolkit().createCustomCursor(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB), new Point(), null));
		addMouseListener(new MouseAdapter() {
			public void mouseEntered(final MouseEvent event) {
				isHovered = true;
			}

			public void mouseExited(final MouseEvent event) {
				isHovered = false;
			}

			public void mouseClicked(final MouseEvent event) {
				grid.addUserCell(cursorLocation[0], cursorLocation[1]);
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseMoved(final MouseEvent event) {
				cursorLocation[0] = event.getX() / scale;
				cursorLocation[1] = event.getY() / scale;
			}

			public void mouseDragged(final MouseEvent event) {
				cursorLocation[0] = event.getX() / scale;
				cursorLocation[1] = event.getY() / scale;

				grid.addUserCell(cursorLocation[0], cursorLocation[1]);
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