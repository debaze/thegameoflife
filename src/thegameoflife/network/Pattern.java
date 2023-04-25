package thegameoflife.network;

public enum Pattern {
	BLOCK(0),
	BOAT(1),
	BLINKER(2),
	GLIDER(3);

	final int id;

	private Pattern(final int id) {
		this.id = id;
	}

	public static Pattern fromId(final int id) {
		for (final Pattern pattern : values()) {
			if (pattern.id == id) return pattern;
		}

		return null;
	}
};