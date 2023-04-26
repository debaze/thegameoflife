package thegameoflife.network;

public class Trainer {
	public static final Network network = new Network(9, 45, 1, .02);

	public static void main(final String[] args) {
		final int epochs = 150_000;
		final double[][] samples = {
			{
				0, 0, 0,
				0, 0, 0,
				0, 0, 0,
			},
			{
				1, 1, 1,
				1, 1, 1,
				1, 1, 1,
			},
			{
				1, 1, 1,
				1, 0, 1,
				1, 1, 1,
			},
			{
				1, 1, 1,
				0, 0, 1,
				1, 1, 1,
			},
			{
				1, 0, 1,
				1, 0, 1,
				1, 1, 1,
			},
			{
				1, 1, 1,
				1, 0, 0,
				1, 1, 1,
			},
			{
				1, 1, 1,
				1, 0, 1,
				1, 0, 1,
			},
			{
				0, 0, 1,
				0, 0, 1,
				1, 1, 1,
			},
			{
				1, 0, 0,
				1, 0, 0,
				1, 1, 1,
			},
			{
				1, 1, 1,
				1, 0, 0,
				1, 0, 0,
			},
			{
				1, 1, 1,
				0, 0, 1,
				0, 0, 1,
			},
			{
				0, 1, 0,
				1, 0, 1,
				1, 1, 1,
			},
			{
				1, 1, 0,
				1, 0, 1,
				1, 1, 0,
			},
			{
				1, 1, 1,
				1, 0, 1,
				0, 1, 0,
			},
			{
				0, 1, 1,
				1, 0, 1,
				0, 1, 1,
			},
			{
				0, 1, 0,
				1, 0, 1,
				0, 1, 0,
			},
			{
				1, 1, 0,
				1, 0, 1,
				0, 1, 0,
			},
			{
				0, 1, 1,
				1, 0, 1,
				0, 1, 0,
			},
			{
				0, 1, 0,
				1, 0, 1,
				0, 1, 1,
			},
			{
				0, 1, 0,
				1, 0, 1,
				1, 1, 0,
			},
			// Gliders
			{
				0, 1, 0,
				1, 0, 0,
				1, 1, 1,
			},
			{
				1, 1, 0,
				1, 0, 1,
				1, 0, 0,
			},
			{
				1, 1, 1,
				0, 0, 1,
				0, 1, 0,
			},
			{
				0, 0, 1,
				1, 0, 1,
				0, 1, 1,
			},
			{
				0, 1, 0,
				0, 0, 1,
				1, 1, 1,
			},
			{
				1, 0, 0,
				1, 0, 1,
				1, 1, 0,
			},
			{
				1, 1, 1,
				1, 0, 0,
				0, 1, 0,
			},
			{
				0, 1, 1,
				1, 0, 1,
				0, 0, 1,
			},
			{
				1, 0, 1,
				1, 1, 0,
				0, 1, 0,
			},
			{
				1, 0, 1,
				0, 1, 1,
				0, 1, 0,
			},
			{
				0, 1, 1,
				1, 1, 1,
				0, 0, 1,
			},
			{
				0, 0, 1,
				1, 1, 1,
				0, 1, 1,
			},
			{
				0, 1, 0,
				0, 1, 1,
				1, 1, 1,
			},
			{
				0, 1, 0,
				1, 1, 0,
				1, 1, 1,
			},
			{
				1, 0, 0,
				1, 1, 1,
				1, 1, 0,
			},
			{
				1, 1, 0,
				1, 1, 1,
				1, 0, 0,
			},
		};
		final double[][] answers = {
			{0},
			{0},
			{0},
			{0},
			{0},
			{0},
			{0},
			{0},
			{0},
			{0},
			{0},
			{0},
			{0},
			{0},
			{0},
			{0},
			{0},
			{0},
			{0},
			{0},
			{Pattern.GLIDER.id},
			{Pattern.GLIDER.id},
			{Pattern.GLIDER.id},
			{Pattern.GLIDER.id},
			{Pattern.GLIDER.id},
			{Pattern.GLIDER.id},
			{Pattern.GLIDER.id},
			{Pattern.GLIDER.id},
			{Pattern.GLIDER.id},
			{Pattern.GLIDER.id},
			{Pattern.GLIDER.id},
			{Pattern.GLIDER.id},
			{Pattern.GLIDER.id},
			{Pattern.GLIDER.id},
			{Pattern.GLIDER.id},
			{Pattern.GLIDER.id},
		};

		network.train(samples, answers, epochs);
	}
}