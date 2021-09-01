package geometry;

import java.awt.Color;
import java.awt.Graphics;

import geometry.Line;

@SuppressWarnings("serial")
public class Line extends Shape {

	private Point startPoint = new Point();
	private Point endPoint = new Point();

	public Line() {

	}

	public Line(Point startPoint, Point endPoint) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
	}

	public Line(Point startPoint, Point endPoint, Color color) {
		this.startPoint = startPoint;
		this.endPoint = endPoint;
		setColor(color);
	}

	public Line(Point startPoint, Point endPoint, boolean selected) {
		this(startPoint, endPoint);
		setSelected(selected);
	}

	public Line(Point startPoint, Point endPoint, boolean selected, Color color) {
		this(startPoint, endPoint, selected);
		setColor(color);
	}

	@Override
	public void moveBy(int byX, int byY) {
		this.startPoint.moveBy(byX, byY);
		this.endPoint.moveBy(byX, byY);
	}

	public Point middleOfLine() {
		int middleByX = (this.startPoint.getX() + this.endPoint.getX()) / 2;
		int middleByY = (this.startPoint.getY() + this.endPoint.getY()) / 2;
		Point p = new Point(middleByX, middleByY);
		return p;
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Line) {
			return (int) (this.length() - ((Line) o).length());
		}
		return 0;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawLine(this.startPoint.getX(), this.startPoint.getY(), this.endPoint.getX(), this.endPoint.getY());

		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.startPoint.getX() - 3, this.startPoint.getY() - 3, 6, 6);
			g.drawRect(this.endPoint.getX() - 3, this.endPoint.getY() - 3, 6, 6);
			g.drawRect(middleOfLine().getX() - 3, middleOfLine().getY() - 3, 6, 6);
		}

	}

	public double length() {
		return startPoint.distance(endPoint.getX(), endPoint.getY());
	}

	public boolean equals(Object obj) {
		if (obj instanceof Line) {
			Line prosledjena = (Line) obj;
			if (this.startPoint.equals(prosledjena.getStartPoint())
					&& this.endPoint.equals(prosledjena.getEndPoint())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean contains(int x, int y) {
		if ((startPoint.distance(x, y) + (endPoint.distance(x, y)) - length()) <= 0.05) {
			return true;
		} else {
			return false;
		}
	}

	public Point getStartPoint() {
		return startPoint;
	}

	public void setStartPoint(Point startPoint) {
		this.startPoint = startPoint;
	}

	public Point getEndPoint() {
		return endPoint;
	}

	public void setEndPoint(Point endPoint) {
		this.endPoint = endPoint;
	}

	public String toString() {
		//return startPoint + "-->" + endPoint;
		return "Line: "+"("+startPoint.getX()+", "+startPoint.getY()+") ("+endPoint.getX()+", "+endPoint.getY()+"), " + "Color: ("+Integer.toString(getColor().getRGB())+")";
	}

	public Line clone(Line l) {
		l.getStartPoint().setX(this.getStartPoint().getX());
		l.getStartPoint().setY(this.getStartPoint().getY());
		l.getEndPoint().setX(this.getEndPoint().getX());
		l.getEndPoint().setY(this.getEndPoint().getY());
		l.setColor(this.getColor());

		return l;
	}
}
