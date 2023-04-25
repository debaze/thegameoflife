package thegameoflife.network;

public class Trainer {
	public static final Network network = new Network(9, 45, 1, 3);

	public static void main(final String[] args) {
		final int epochs = 100_000;
		final double[][] samples = {
			{
				1, 1, 1,
				1, 0, 0,
				0, 0, 0,
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
				1, 1, 1,
				0, 0, 0,
				0, 0, 0,
			},
		};
		final double[][] answers = {
			{Pattern.BLOCK.id},
			{Pattern.BOAT.id},
			{Pattern.BOAT.id},
			{Pattern.BOAT.id},
			{Pattern.BOAT.id},
			{Pattern.BLINKER.id},
		};

		network.train(samples, answers, epochs);
	}
}