package inda13projekt;

/**
 * The camera is used to keep track of the offset used to render the tilemap and
 * objects
 * 
 * @author Alex
 * 
 */
public class Camera {
	private int xOffset;
	private int yOffset;
	private int xSize; // in pixels
	private int ySize; // in pixels
	private final int middleX = 320;
	private final int middleY = 240;

	/**
	 * @param xGridSize
	 *            x size of map in tiles
	 * @param yGridSize
	 *            y size of map in tiles
	 */
	public Camera(int xGridSize, int yGridSize) {
		xSize = xGridSize * 32;
		ySize = yGridSize * 32;
		xOffset = 0;
		yOffset = 0;
	}

	/**
	 * Sets camera to follow an object
	 * 
	 * @param x
	 *            object location to follow, in pixels
	 * @param y
	 *            object location to follow, in pixels
	 */
	public void setLocation(int x, int y) {
		x += 16;
		y += 16;

		if (x < middleX) {
			xOffset = 0;
		} else if (x > xSize - middleX) {
			xOffset = -xSize + middleX * 2;
		} else {
			xOffset = -x + middleX;
		}

		if (y < middleY) {
			yOffset = 0;
		} else if (y > ySize - middleY) {
			yOffset = -ySize + middleY * 2;
		} else {
			yOffset = -y + middleY;
		}

	}

	public int getXOffset() {
		return xOffset;
	}

	public int getYoffset() {
		return yOffset;
	}
}
