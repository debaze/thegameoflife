package thegameoflife;

import java.util.ArrayList;

public class Grid {
	public final int cols, rows;
	public final float decay;
	public final float[][] cells, clonedCells;
	private final ArrayList<int[]> addedCells = new ArrayList<int[]>(), removedCells = new ArrayList<int[]>();
	private final int[][] neighbors = new int[8][2];

	public Grid(final int cols, final int rows, final float decay) {
		assert cols > 0 : "The column number must be positive.";
		assert rows > 0 : "The row number must be positive.";
		assert decay >= 0 && decay <= 1 : "The decay must be included in [0, 1].";

		this.cols = cols;
		this.rows = rows;
		this.decay = decay;
		cells = new float[cols][rows];
		clonedCells = new float[cols][rows];

		for (int i = 0, j; i < cols; i++) {
			for (j = 0; j < rows; j++) {
				cells[i][j] = Math.random() < .25 ? 1 : 0;
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
			for (int i = 0, j; i < cols; i++) {
				for (j = 0; j < rows; j++) {
					if (getAddedCell(i, j)) cells[i][j] = 1;
				}
			}

			addedCells.clear();
		}

		if (!removedCells.isEmpty()) {
			for (int i = 0, j; i < cols; i++) {
				for (j = 0; j < rows; j++) {
					if (getRemovedCell(i, j)) cells[i][j] = 0;
				}
			}

			removedCells.clear();
		}
	}

	public void evolve() {
		int i, j;

		for (i = 0; i < cols; i++) {
			for (j = 0; j < rows; j++) {
				clonedCells[i][j] = getState(cells[i][j], getNeighborCount(i, j));
			}
		}

		for (i = 0; i < cols; i++) {
			for (j = 0; j < rows; j++) {
				cells[i][j] = clonedCells[i][j];
			}
		}
	}

	public boolean getAddedCell(final int x, final int y) {
		for (final int[] cell : addedCells) {
			if (cell[0] == x && cell[1] == y) return true;
		}

		return false;
	}

	public boolean getRemovedCell(final int x, final int y) {
		for (final int[] cell : removedCells) {
			if (cell[0] == x && cell[1] == y) return true;
		}

		return false;
	}

	public void getNeighborLocations(final int x, final int y) {
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

	public int getNeighborCount(final int x, final int y) {
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

	public float getState(final float cell, final int neighborCount) {
		if (cell != 1) {
			if (neighborCount == 3) return 1;
			if (cell - decay < 0) return 0;

			return cell - decay;
		}

		if (neighborCount == 2 || neighborCount == 3) return 1;

		return 1 - decay;
	}

	public void clear() {
		for (int i = 0, j; i < cols; i++) {
			for (j = 0; j < rows; j++) {
				cells[i][j] = 0;
			}
		}
	}
}