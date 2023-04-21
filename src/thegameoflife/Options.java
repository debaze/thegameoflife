package thegameoflife;

public class Options {
	public final long frameTime;
	public boolean isPlaying;
	public float decay;

	public Options(final boolean isPlaying, final float decay, final long fps) {
		assert decay >= 0 && decay <= 1 : "The decay must be included in [0, 1].";

		frameTime = 1000 / fps;
		this.isPlaying = isPlaying;
		this.decay = decay;
	}
}