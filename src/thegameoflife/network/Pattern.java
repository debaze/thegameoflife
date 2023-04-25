package thegameoflife.network;

public enum Pattern {
	BLINKER(0),
	GLIDER(1);

	final int id;

	private Pattern(final int id) {
		this.id = id;
	}
};