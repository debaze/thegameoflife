package thegameoflife.network;

public enum Pattern {
	GLIDER(1);

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