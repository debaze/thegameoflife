package thegameoflife;

import java.util.ArrayList;

public class Grid {
	public final int cols, rows;
	public final Options options;
	public final float[][] cells, clonedCells;
	public final ArrayList<int[]> patterns = new ArrayList<>();
	private final ArrayList<int[]> addedCells = new ArrayList<>(), removedCells = new ArrayList<>();
	private final int[][] neighbors = new int[8][2];
	private final ArrayList<ArrayList<int[]>> patternLines = new ArrayList<>();
	private final int errorMargin;

	public Grid(final int cols, final int rows, final int errorMargin, final Options options) {
		assert cols > 0 : "The column number must be positive.";
		assert rows > 0 : "The row number must be positive.";
		assert errorMargin >= 0 : "The error margin must not be negative.";

		this.cols = cols;
		this.rows = rows;
		this.errorMargin = errorMargin;
		this.options = options;
		cells = new float[cols][rows];
		clonedCells = new float[cols][rows];

		for (int y = 0, x; y < rows; y++) {
			for (x = 0; x < cols; x++) {
				cells[x][y] = Math.random() < options.density ? 1 : 0;
			}
		}
	}

	public void addCell(final int x, final int y) {
		addedCells.add(new int[] {x, y});
	}

	public void removeCell(final int x, final int y) {
		removedCells.add(new int[] {x, y});
	}

	public void handleUserActions() {
		if (!addedCells.isEmpty()) {
			for (int y = 0, x; y < rows; y++) {
				for (x = 0; x < cols; x++) {
					if (getAddedCell(x, y)) cells[x][y] = 1;
				}
			}

			addedCells.clear();
		}

		if (!removedCells.isEmpty()) {
			for (int y = 0, x; y < rows; y++) {
				for (x = 0; x < cols; x++) {
					if (getRemovedCell(x, y)) cells[x][y] = 0;
				}
			}

			removedCells.clear();
		}
	}

	public void clear() {
		for (int y = 0, x; y < rows; y++) {
			for (x = 0; x < cols; x++) {
				cells[x][y] = 0;
			}
		}
	}

	public void evolve() {
		int x, y;

		for (y = 0; y < rows; y++) {
			for (x = 0; x < cols; x++) {
				clonedCells[x][y] = getState(cells[x][y], getNeighborCount(x, y));
			}
		}

		for (y = 0; y < rows; y++) {
			for (x = 0; x < cols; x++) {
				cells[x][y] = clonedCells[x][y];
			}
		}
	}

	public void recognizePatterns() {
		// Register 1D horizontal slices
		{
			final ArrayList<int[]> line = new ArrayList<>();
			final int[] slice = {-1, 0, 0};
			int error = 0;

			patternLines.clear();

			for (int y = 0, x; y < rows; y++) {
				slice[1] = y;

				for (x = 0; x < cols; x++) {
					if (cells[x][y] == 1) {
						if (slice[0] == -1) {
							// Create new slice

							// Set X origin
							slice[0] = x;

							// Set width to 1 (current cell)
							slice[2] = 1;

							// Reset error
							error = 0;
						} else {
							// A slice is already being processed

							// Update the width with the possible error + 1 for the current cell
							slice[2] += error + 1;

							// Reset error
							error = 0;
						}
					} else {
						if (slice[0] != -1) {
							// A slice is already being processed, but this is not a cell

							// Increment error
							error++;

							if (error > errorMargin) {
								// Too much error, save slice
								line.add(new int[] {slice[0], slice[1], slice[2]});

								// Reset X origin for the next slice
								slice[0] = -1;
							}
						}
					}
				}

				if (slice[0] != -1) {
					// End of the line, save slice
					line.add(new int[] {slice[0], slice[1], slice[2]});

					// Reset X origin for the next slice
					slice[0] = -1;
				}

				if (line.isEmpty()) continue;

				final ArrayList<int[]> clonedLine = new ArrayList<>();

				for (final int[] lineSlice : line) clonedLine.add(lineSlice);

				patternLines.add(clonedLine);
				line.clear();
			}
		}

		// Merge slices into 2D patterns
		{
			patterns.clear();

			final ArrayList<int[]> currentPatterns = new ArrayList<>();
			int y = 0, p, patternMinX, patternMaxX, minX, maxX;

			for (final ArrayList<int[]> line : patternLines) {
				y = line.get(0)[1];

				// Register finished patterns
				for (p = currentPatterns.size() - 1; p >= 0; p--) {
					final int[] pattern = currentPatterns.get(p);

					if (pattern[1] + pattern[3] == y) continue;

					patterns.add(pattern);
					currentPatterns.remove(pattern);
				}

				slice: for (final int[] slice : line) {
					minX = slice[0];
					maxX = minX + slice[2];

					for (final int[] pattern : currentPatterns) {
						patternMinX = pattern[0];
						patternMaxX = patternMinX + pattern[2];

						if (
							((maxX >= patternMinX && maxX <= patternMaxX) && minX <= patternMinX) ||
							((minX >= patternMinX && minX <= patternMaxX) && maxX >= patternMaxX) ||
							((minX >= patternMinX && minX <= patternMaxX) && (maxX >= patternMinX && maxX <= patternMaxX)) ||
							(minX <= patternMinX && maxX >= patternMaxX)
						) {
							// Adjust pattern
							pattern[0] = Integer.min(patternMinX, minX);
							pattern[2] = Integer.max(pattern[2], slice[2]);
							if (pattern[1] + pattern[3] != slice[1] + 1) pattern[3]++;

							continue slice;
						}
					}

					// Create new pattern
					currentPatterns.add(new int[] {slice[0], slice[1], slice[2], 1});
				}
			}

			patterns.addAll(currentPatterns);
		}
	}

	private boolean getAddedCell(final int x, final int y) {
		for (final int[] cell : addedCells) {
			if (cell[0] == x && cell[1] == y) return true;
		}

		return false;
	}

	private boolean getRemovedCell(final int x, final int y) {
		for (final int[] cell : removedCells) {
			if (cell[0] == x && cell[1] == y) return true;
		}

		return false;
	}

	private void getNeighborLocations(final int x, final int y) {
		final int l = x - 1, r = x + 1, t = y - 1, b = y + 1;

		neighbors[0][0] = l;
		neighbors[0][1] = t;
		neighbors[1][0] = x;
		neighbors[1][1] = t;
		neighbors[2][0] = r;
		neighbors[2][1] = t;
		neighbors[3][0] = l;
		neighbors[3][1] = y;
		neighbors[4][0] = r;
		neighbors[4][1] = y;
		neighbors[5][0] = l;
		neighbors[5][1] = b;
		neighbors[6][0] = x;
		neighbors[6][1] = b;
		neighbors[7][0] = r;
		neighbors[7][1] = b;
	}

	private int getNeighborCount(final int x, final int y) {
		getNeighborLocations(x, y);

		int count = 0;

		for (final int[] neighbor : neighbors) {
			if (
				neighbor[0] < 0 ||
				neighbor[0] >= cols ||
				neighbor[1] < 0 ||
				neighbor[1] >= rows ||
				cells[neighbor[0]][neighbor[1]] != 1
			) continue;

			count++;
		}

		return count;
	}

	private float getState(final float cell, final int neighborCount) {
		if (cell != 1) {
			if (neighborCount == 3) return 1;
			if (cell - options.decay < 0) return 0;

			return cell - options.decay;
		}

		if (neighborCount == 2 || neighborCount == 3) return 1;

		return 1 - options.decay;
	}
}