package thegameoflife;

import java.util.ArrayList;

public class Grid {
	public final int cols, rows;
	public final float decay;
	public final float[][] viewCells;
	public final float[][] cells;
	private final ArrayList<int[]> userCells;
	private final int[][] neighbors = new int[8][2];

	public Grid(final int cols, final int rows, final float decay) {
		assert cols > 0 : "The column number must be positive.";
		assert rows > 0 : "The row number must be positive.";
		assert decay >= 0 && decay <= 1 : "The decay must be included in [0, 1].";

		this.cols = cols;
		this.rows = rows;
		this.decay = decay;
		viewCells = new float[cols][rows];
		cells = new float[cols][rows];
		userCells = new ArrayList<int[]>();

		for (int i = 0, j; i < cols; i++) {
			for (j = 0; j < rows; j++) {
				cells[i][j] = Math.random() < .25 ? 1 : 0;
			}
		}
	}

	public void evolve() {
		for (int i = 0, j; i < cols; i++) {
			for (j = 0; j < rows; j++) {
				viewCells[i][j] = getState(cells[i][j], getNeighborCount(i, j));
			}
		}

		for (int i = 0, j; i < cols; i++) {
			for (j = 0; j < rows; j++) {
				cells[i][j] = viewCells[i][j];
			}
		}
	}

	public void registerNeighbors(final int x, final int y) {
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
		registerNeighbors(x, y);

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

	public boolean getUserCell(final int x, final int y) {
		for (final int[] cell : userCells) {
			if (cell[0] == x && cell[1] == y) return true;
		}

		return false;
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

	public void addUserCell(final int x, final int y) {
		userCells.add(new int[] {x, y});
	}

	public void mergeUserCells() {
		if (userCells.isEmpty()) return;

		for (int i = 0, j; i < cols; i++) {
			for (j = 0; j < rows; j++) {
				if (getUserCell(i, j)) cells[i][j] = 1;
			}
		}

		userCells.clear();
	}

	public void clear() {
		for (int i = 0, j; i < cols; i++) {
			for (j = 0; j < rows; j++) {
				cells[i][j] = 0;
			}
		}
	}
}