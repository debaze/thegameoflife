package thegameoflife.gui;

import java.awt.Dimension;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import thegameoflife.Options;

public class DecaySlider extends JSlider {
	public DecaySlider(final Options options) {
		super(JSlider.HORIZONTAL, 0, 10, (int) options.decay * 10);

		setPreferredSize(new Dimension(120, 20));
		addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(final ChangeEvent event) {
				final JSlider source = (JSlider) event.getSource();

				options.decay = source.getValue() * .1f;
			}
		});
	}
}