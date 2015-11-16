package voogasalad_GucciGames.gameAuthoring.gui.map;

import java.util.HashSet;
import java.util.Set;

import javafx.geometry.Point2D;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class GridSelector {
	private final Rectangle _area = new Rectangle(0, 0, Color.rgb(0, 0, 255, 0.4));

	private Point2D _start = Point2D.ZERO;
	private Point2D _end = Point2D.ZERO;
	private double xMin = Double.MAX_VALUE, xMax = Double.MIN_VALUE;
	private double yMin = Double.MAX_VALUE, yMax = Double.MIN_VALUE;

	private Grid myGrid;

	public GridSelector(Grid grid) {
		myGrid = grid;
		grid.getChildren().add(_area);
		_area.setStroke(Color.DARKBLUE);
		_area.setStrokeWidth(1);
		_area.setVisible(false);
		_area.toFront();
		myGrid.addEventHandler(MouseDragEvent.DRAG_DETECTED, e -> dragStart(e));
		myGrid.addEventHandler(MouseDragEvent.MOUSE_DRAG_OVER, e -> dragMoved(e));
		myGrid.addEventHandler(MouseDragEvent.MOUSE_DRAG_RELEASED, e -> dragEnd(e));
	}

	/**
	 * Update location of the rectangle proving two defining point (along the
	 * diameter)
	 */
	private void update(Point2D start, Point2D end) {
		_start = start;
		_end = end;
		updateBound(start);
		updateBound(end);
		_area.setX(Math.min(_start.getX(), _end.getX()));
		_area.setY(Math.min(_start.getY(), _end.getY()));
		_area.setWidth(Math.abs(_start.getX() - _end.getX()));
		_area.setHeight(Math.abs(_start.getY() - _end.getY()));
	}

	private void updateBound(Point2D p) {
		double x = p.getX(), y = p.getY();
		xMin = x < xMin ? x : xMin;
		xMax = x > xMax ? x : xMax;
		yMin = y < yMin ? y : yMin;
		yMax = y > yMax ? y : yMax;
	}

	// Define handling of mouse events

	private void dragStart(MouseEvent e) {
		myGrid.startFullDrag();
		_area.setVisible(true);
		_area.toFront();
		Point2D p = new Point2D(e.getX(), e.getY());
		update(p, p);
		e.consume();
	}

	private void dragMoved(MouseEvent e) {
		update(_start, new Point2D(e.getX(), e.getY()));
		select(_start, _end);
		e.consume();
	}

	private void dragEnd(MouseEvent e) {
		selectFinalize(_start, _end);
		update(Point2D.ZERO, Point2D.ZERO);
		_area.setVisible(false);
		e.consume();
	}

	private Set<GridPoint> getSelectedCells(Point2D start, Point2D end) {
		double size = myGrid.getCellSize().get();
		xMin = Math.min(start.getX(), end.getX());
		xMax = Math.max(start.getX(), end.getX());
		yMin = Math.min(start.getY(), end.getY());
		yMax = Math.max(start.getY(), end.getY());
		int x1 = (int) Math.floor(xMin / size), y1 = (int) Math.floor(yMin / size);
		int x2 = (int) Math.ceil(xMax / size), y2 = (int) Math.ceil(yMax / size);
		HashSet<GridPoint> set = new HashSet<>();
		for (int i = x1; i < x2; i++) {
			for (int j = y1; j < y2; j++) {
				set.add(new GridPoint(i, j));
			}
		}
		return set;
	}

	private void select(Point2D start, Point2D end) {
		getSelectedCells(new Point2D(xMin, yMin), new Point2D(xMax, yMax)).stream().map(p -> myGrid.getCell(p))
				.filter(cell -> cell != null).forEach(cell -> {
					if (cell.isSelected())
						cell.addBound();
					else
						cell.removeBound();
				});

		getSelectedCells(start, end).stream().map(p -> myGrid.getCell(p)).filter(cell -> cell != null).forEach(cell -> {
			if (cell.isSelected())
				cell.removeBound();
			else
				cell.addBound();
		});
	}

	private void selectFinalize(Point2D start, Point2D end) {
		getSelectedCells(start, end).stream().map(p -> myGrid.getCell(p)).filter(cell -> cell != null).forEach(cell -> {
			if (cell.isSelected())
				cell.deselect();
			else
				cell.select();
		});
	}

}
