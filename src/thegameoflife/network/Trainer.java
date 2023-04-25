package thegameoflife.network;

public class Trainer {
	public static Network network = new Network(2, 10, 1, 3);

	public static void main(final String[] args) {
		final int epochs = 100_000;
		final double[][] samples = {
			{
				1, 1, 1,
			},
		};
		final double[][] answers = {
			{Pattern.BLINKER.id},
		};

		network.train(samples, answers, epochs);
	}
}