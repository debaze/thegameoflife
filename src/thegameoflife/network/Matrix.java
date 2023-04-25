package thegameoflife.network;

public class Matrix {
	private int rows, cols;
	private double[][] data;

	public Matrix(final int rows, final int cols) {
		this.rows = rows;
		this.cols = cols;
		data = new double[rows][cols];
	}

	public Matrix(final int rows, final int cols, final double[][] data) {
		this.rows = rows;
		this.cols = cols;
		this.data = data;
	}

	public static Matrix fromArray(final double[] array) {
		final int length = array.length;
		final double[][] data = new double[length][1];

		for (int i = 0; i < length; i++) data[i][0] = array[i];

		return new Matrix(length, 1, data);
	}

	public static Matrix random(final int rows, final int cols) {
		final double[][] data = new double[rows][cols];

		for (int i = 0, j; i < rows; i++) {
			for (j = 0; j < cols; j++) {
				data[i][j] = Math.random() * 2 - 1;
			}
		}

		return new Matrix(rows, cols, data);
	}

	public Matrix add(final double scalar) {
		for (int i = 0, j; i < rows; i++) {
			for (j = 0; j < cols; j++) {
				data[i][j] += scalar;
			}
		}

		return this;
	}

	public Matrix add(final Matrix other) {
		assert rows == other.rows && cols == other.cols : "The matrices must have the same size.";

		for (int i = 0, j; i < rows; i++) {
			for (j = 0; j < cols; j++) {
				data[i][j] += other.data[i][j];
			}
		}

		return this;
	}

	public Matrix clone() {
		final Matrix clone = new Matrix(rows, cols);

		for (int i = 0, j; i < rows; i++) {
			for (j = 0; j < cols; j++) {
				clone.data[i][j] = data[i][j];
			}
		}

		return clone;
	}

	public Matrix derivativeSigmoid() {
		for (int i = 0, j; i < rows; i++) {
			for (j = 0; j < cols; j++) {
				data[i][j] *= 1 - data[i][j];
			}
		}

		return this;
	}

	public Matrix multiply(final double scalar) {
		for (int i = 0, j; i < rows; i++) {
			for (j = 0; j < cols; j++) {
				data[i][j] *= scalar;
			}
		}

		return this;
	}

	public Matrix multiply(final Matrix other) {
		final double[][] temp = new double[rows][other.cols];
		double sum;

		for (int i = 0, j, k; i < rows; i++) {
			for (j = 0, sum = 0; j < other.cols; j++) {
				for (k = 0; k < cols; k++) {
					sum += data[i][k] * other.data[k][j];
				}

				temp[i][j] = sum;
			}
		}

		cols = other.cols;
		data = temp;

		return this;
	}

	public Matrix multiplyElement(final Matrix other) {
		for (int i = 0, j; i < rows; i++) {
			for (j = 0; j < cols; j++) {
				data[i][j] *= other.data[i][j];
			}
		}

		return this;
	}

	public Matrix sigmoid() {
		for (int i = 0, j; i < rows; i++) {
			for (j = 0; j < cols; j++) {
				data[i][j] = 1 / (Math.exp(-data[i][j]) + 1);
			}
		}

		return this;
	}

	public Matrix subtract(final Matrix other) {
		for (int i = 0, j; i < rows; i++) {
			for (j = 0; j < cols; j++) {
				data[i][j] -= other.data[i][j];
			}
		}

		return this;
	}

	public double[] toArray() {
		final double[] array = new double[rows * cols];

		for (int i = 0, j; i < rows; i++) {
			for (j = 0; j < cols; j++) {
				array[i * j] = data[i][j];
			}
		}

		return array;
	}

	public Matrix transpose() {
		final double[][] temp = new double[cols][rows];

		for (int i = 0, j; i < rows; i++) {
			for (j = 0; j < cols; j++) {
				temp[j][i] = data[i][j];
			}
		}

		cols = rows;
		rows = temp.length;
		data = temp;

		return this;
	}
}