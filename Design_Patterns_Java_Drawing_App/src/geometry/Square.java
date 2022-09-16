package geometry;

import java.awt.Color;
import java.awt.Graphics;

@SuppressWarnings("serial")
public class Square extends SurfaceShape {

	private Point upperLeft;
	private int side;

	public Square() {

	}

	public Square(Point upperLeft, int side) {
		this.upperLeft = upperLeft;
		this.side = side;
	}

	public Square(Point upperLeft, int side, boolean selected) {
		this(upperLeft, side);
		setSelected(selected);
	}

	public Square(Point upperLeft, int side, boolean selected, Color color) {
		this(upperLeft, side, selected);
		setColor(color);
	}

	public Square(Point upperLeft, int side, Color color, Color innerColor) {
		this(upperLeft, side);
		setColor(color);
		setInnerColor(innerColor);
	}

	public Square(Point upperLeft, int side, boolean selected, Color color, Color innerColor) {
		this(upperLeft, side, selected, color);
		setInnerColor(innerColor);
	}
	
	@Override
	public void moveBy(int byX, int byY) {
		this.upperLeft.moveBy(byX, byY);
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Square) {
			Square forwarded = (Square) o;
			return this.side - forwarded.side;
		} else {
			return 0;
		}
	}

	@Override
	public boolean contains(int x, int y) {
		if (this.upperLeft.getX() <= x && x <= this.upperLeft.getX() + this.side && this.upperLeft.getY() <= y
				&& y <= this.upperLeft.getY() + this.side) {
			return true;
		} else {
			return false;
		}
	}
	
	public double area() {
		return 6 * (side * side);
	}
	
	public Line diagonal() {
		Point upperRight = new Point(this.upperLeft.getX() + this.side, upperLeft.getY());
		Point lowerLeft = new Point(upperLeft.getX(), this.upperLeft.getY() + this.side);

		Line diagonal = new Line(upperRight, lowerLeft);

		return diagonal;
	}

	@Override
	public void selected(Graphics g) {
		upperLeft.selected(g);
		this.diagonal().getStartPoint().selected(g);
		this.diagonal().getEndPoint().selected(g);
		Point lowerRight = new Point(upperLeft.getX() + side, upperLeft.getY() + side);
		lowerRight.selected(g);
		Line l1 = new Line(upperLeft, this.diagonal().getStartPoint());
		l1.middleOfLine().selected(g);
		Line l2 = new Line(this.diagonal().getEndPoint(), lowerRight);
		l2.middleOfLine().selected(g);
		Line l3 = new Line(upperLeft, this.diagonal().getEndPoint());
		l3.middleOfLine().selected(g);
		Line l4 = new Line(this.diagonal().getStartPoint(), lowerRight);
		l4.middleOfLine().selected(g);
	}

	public boolean equals(Object obj) {
		if (obj instanceof Square) {
			Square tempSquare = (Square) obj;
			if (this.upperLeft.equals(tempSquare.getUpperLeft()) && this.getSide() == tempSquare.getSide())
				return true;
			else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawRect(this.upperLeft.getX(), this.upperLeft.getY(), this.side, this.side);
		fill(g);
		if (isSelected()) {
			this.selected(g);
		}
	}

	@Override
	public void fill(Graphics g) {
		g.setColor(this.getInnerColor());
		g.fillRect(upperLeft.getX(), upperLeft.getY(), this.side, this.side);
	}

	public String toString() {
		return "Square: (" + upperLeft.getX() + ", " + upperLeft.getY() + "), " + "Side=" + side
				+ ", Edge Color: (" + Integer.toString(getColor().getRGB()) + ")"
				+ ", Inner Color: (" + Integer.toString(getInnerColor().getRGB()) + ")";
	}

	public Point getUpperLeft() {
		return upperLeft;
	}

	public void setUpperLeft(Point upperLeft) {
		this.upperLeft = upperLeft;
	}

	public int getSide() {
		return side;
	}

	public void setSide(int side) {
		this.side = side;
	}
	
	public Square clone(Square s) {
		s.getUpperLeft().setX(this.getUpperLeft().getX());
		s.getUpperLeft().setY(this.getUpperLeft().getY());
		s.setSide(this.getSide());
		s.setColor(this.getColor());
		s.setInnerColor(this.getInnerColor());

		return s;
	}
}