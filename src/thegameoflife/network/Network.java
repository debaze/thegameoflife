package thegameoflife.network;

public class Network {
	private final Matrix weightsInputHidden, weightsHiddenOutput, biasesHidden, biasesOutput;
	private final int inputNeurons, hiddenNeurons, outputNeurons;
	private final double learningRate;

	public Network(final int inputNeurons, final int hiddenNeurons, final int outputNeurons, final double learningRate) {
		weightsInputHidden = Matrix.random(hiddenNeurons, inputNeurons);
		weightsHiddenOutput = Matrix.random(outputNeurons, hiddenNeurons);
		biasesHidden = Matrix.random(hiddenNeurons, 1);
		biasesOutput = Matrix.random(outputNeurons, 1);
		this.inputNeurons = inputNeurons;
		this.hiddenNeurons = hiddenNeurons;
		this.outputNeurons = outputNeurons;
		this.learningRate = learningRate;
	}

	public int getInputNeurons() {
		return inputNeurons;
	}

	public int getHiddenNeurons() {
		return hiddenNeurons;
	}

	public int getOutputNeurons() {
		return outputNeurons;
	}

	public double getLearningRate() {
		return learningRate;
	}

	public void train(final double[][] inputs, final double[][] targets, final int epochs) {
		final int length = inputs.length;
		int sample;

		for (int i = 0; i < epochs; i++) {
			sample = (int) (Math.random() * length);

			trainEpoch(inputs[sample], targets[sample]);
		}
	}

	public void trainEpoch(final double[] inputs, final double[] targets) {
		final Matrix
			input = Matrix.fromArray(inputs),
			hidden = weightsInputHidden
				.clone()
				.multiply(input)
				.add(biasesHidden)
				.sigmoid(),
			output = weightsHiddenOutput
				.clone()
				.multiply(hidden)
				.add(biasesOutput)
				.sigmoid(),
			target = Matrix.fromArray(targets),
			error = target.clone().subtract(output),
			gradient = output
				.clone()
				.derivativeSigmoid()
				.multiply(error)
				.multiply(learningRate),
			hiddenTranspose = hidden.clone().transpose(),
			weightsHiddenOutputDelta = gradient.clone().multiply(hiddenTranspose);

		weightsHiddenOutput.add(weightsHiddenOutputDelta);
		biasesOutput.add(gradient);

		final Matrix
			weightsHiddenOutputTranspose = weightsHiddenOutput.clone().transpose(),
			hiddenErrors = weightsHiddenOutputTranspose.clone().multiply(error),
			hiddenGradient = hidden
				.clone()
				.derivativeSigmoid()
				.multiply(hiddenErrors)
				.multiply(learningRate),
			inputTranspose = input.clone().transpose(),
			weightsInputHiddenDelta = hiddenGradient.clone().multiply(inputTranspose);

		weightsInputHidden.add(weightsInputHiddenDelta);
		biasesHidden.add(hiddenGradient);
	}

	public double[] predict(final double[] inputs) {
		final Matrix
			input = Matrix.fromArray(inputs),
			hidden = weightsInputHidden
				.clone()
				.multiply(input)
				.add(biasesHidden)
				.sigmoid(),
			output = weightsHiddenOutput
				.clone()
				.multiply(hidden)
				.add(biasesOutput)
				.sigmoid();

		return output.toArray();
	}
}