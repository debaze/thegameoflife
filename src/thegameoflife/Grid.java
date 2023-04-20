package thegameoflife;

public class Grid {
	private final int width, height;
	private float[][] cells, nextCells;

	public Grid(final int width, final int height) {
		this.width = width;
		this.height = height;
		cells = new float[width][height];
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public float[][] getCells() {
		return cells;
	}

	public void evolve() {
		nextCells = cells.clone();

		// TODO: survival/death/born

		cells = nextCells;
	}
}