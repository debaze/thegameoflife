package thegameoflife.gui;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import thegameoflife.Grid;

public class ResetButton extends JButton {
	public ResetButton(final Grid grid, final Canvas canvas) {
		super("Reset");

		setPreferredSize(new Dimension(80, 20));
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent event) {
				grid.clear();
				canvas.repaint();
			}
		});
	}
}