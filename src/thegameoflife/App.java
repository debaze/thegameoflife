package thegameoflife;

import java.io.FileInputStream;
import java.util.Properties;
import javax.swing.UIManager;
import thegameoflife.gui.Window;
import thegameoflife.network.Trainer;

public class App {
	private static final String propertiesPath = "src/config.properties";

	public static void main(final String[] args) throws Exception {
		Trainer.main(args);

		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		final Properties properties = new Properties();

		properties.load(new FileInputStream(propertiesPath));

		new Window(properties);
	}
}