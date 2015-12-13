package voogasalad_GucciGames.gameAuthoring.gui.map;

import javafx.beans.NamedArg;

public class GridPoint {

	private int x;

	/**
	 * Point or vector with both coordinates set to 0.
	 */
	public static final GridPoint ZERO = new GridPoint(0, 0);

	/**
	 * The x coordinate.
	 * 
	 * @return the x coordinate
	 */
	public final int getX() {
		return x;
	}

	/**
	 * The y coordinate.
	 *
	 * @defaultValue 0
	 */
	private int y;

	/**
	 * The y coordinate.
	 * 
	 * @return the y coordinate
	 */
	public final int getY() {
		return y;
	}

	/**
	 * Cache the hash code to make computing hashes faster.
	 */
	private int hash = 0;

	/**
	 * Creates a new instance of {@code Point2D}.
	 * 
	 * @param x
	 *            the x coordinate of the point
	 * @param y
	 *            the y coordinate of the point
	 */
	public GridPoint(@NamedArg("x") int x, @NamedArg("y") int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Computes the distance between this point and point {@code (x1, y1)}.
	 *
	 * @param x1
	 *            the x coordinate of other point
	 * @param y1
	 *            the y coordinate of other point
	 * @return the distance between this point and point {@code (x1, y1)}.
	 */
	public double distance(int x1, int y1) {
		double a = getX() - x1;
		double b = getY() - y1;
		return Math.sqrt(a * a + b * b);
	}

	/**
	 * Computes the distance between this point and the specified {@code point}.
	 *
	 * @param point
	 *            the other point
	 * @return the distance between this point and the specified {@code point}.
	 * @throws NullPointerException
	 *             if the specified {@code point} is null
	 */
	public double distance(GridPoint point) {
		return distance(point.getX(), point.getY());
	}

	/**
	 * Indicates whether some other object is "equal to" this one.
	 * 
	 * @param obj
	 *            the reference object with which to compare
	 * @return true if this point is the same as the obj argument; false
	 *         otherwise
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj instanceof GridPoint) {
			GridPoint other = (GridPoint) obj;
			return getX() == other.getX() && getY() == other.getY();
		} else
			return false;
	}

	/**
	 * Returns a hash code value for the point.
	 * 
	 * @return a hash code value for the point.
	 */
	@Override
	public int hashCode() {
		if (hash == 0) {
			long bits = 17L;
			bits = 1013L * bits + x;
			bits = 1013L * bits + y;
			hash = (int) (bits ^ (bits >> 32));
		}
		return hash;
	}

	@Override
	public String toString() {
		return x + " " + y;
	}
}
