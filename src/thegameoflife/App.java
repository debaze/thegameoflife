package thegameoflife;

import java.io.FileInputStream;
import java.util.Properties;
import javax.swing.UIManager;
import thegameoflife.gui.Window;

public class App {
	public static String propertiesPath = "src/config.properties";

	public static void main(final String[] args) throws Exception {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		final Properties properties = new Properties();

		properties.load(new FileInputStream(propertiesPath));

		final Grid grid = new Grid(
			Integer.parseInt(properties.getProperty("ROWS")),
			Integer.parseInt(properties.getProperty("COLS")),
			Float.parseFloat(properties.getProperty("DECAY"))
		);

		new Window(grid, properties);
	}
}