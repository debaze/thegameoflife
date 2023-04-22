package thegameoflife;

public class Options {
	public final long frameTime;
	public final float density;
	public boolean isPlaying;
	public float decay;

	public Options(final boolean isPlaying, final float density, final float decay, final long fps) {
		assert decay >= 0 && decay <= 1 : "The decay must be included in [0, 1].";
		assert density >= 0 && density <= 1 : "The density must be included in [0, 1].";

		frameTime = 1000 / fps;
		this.density = density;
		this.isPlaying = isPlaying;
		this.decay = decay;
	}
}