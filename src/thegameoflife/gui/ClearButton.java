package thegameoflife.gui;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import thegameoflife.Grid;

// TODO: rework OO
public class ClearButton extends JButton {
	public ClearButton(final Grid grid) {
		super("Clear");

		setPreferredSize(new Dimension(80, 20));
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(final MouseEvent event) {
				grid.clear();
			}
		});
	}
}