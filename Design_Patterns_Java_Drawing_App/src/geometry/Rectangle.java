package geometry;

import java.awt.Color;
import java.awt.Graphics;

@SuppressWarnings("serial")
public class Rectangle extends SurfaceShape {

	private int width;
	private int height;
	private Point upperLeftPoint = new Point();

	public Rectangle() {

	}

	public Rectangle(Point upperLeftPoint, int width, int height) {
		this.upperLeftPoint = upperLeftPoint;
		this.width = width;
		this.height = height;
	}

	public Rectangle(Point upperLeftPoint, int width, int height, boolean selected) {
		this(upperLeftPoint, width, height);
		setSelected(selected);
	}

	public Rectangle(Point upperLeftPoint, int width, int height, boolean selected, Color color) {
		this(upperLeftPoint, width, height, selected);
		setColor(color);
	}

	public Rectangle(Point upperLeftPoint, int width, int height, Color color, Color innerColor) {
		this(upperLeftPoint, width, height);
		setColor(color);
		setInnerColor(innerColor);
	}

	public Rectangle(Point upperLeftPoint, int width, int height, boolean selected, Color color, Color innerColor) {
		this(upperLeftPoint, width, height, selected, color);
		setInnerColor(innerColor);
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Rectangle) {
			return (int) (this.area() - ((Rectangle) o).area());
		}
		return 0;
	}

	@Override
	public void moveBy(int byX, int byY) {
		this.upperLeftPoint.moveBy(byX, byY);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawRect(this.upperLeftPoint.getX(), this.upperLeftPoint.getY(), this.width, this.height);
		fill(g);
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.upperLeftPoint.getX() - 3, this.upperLeftPoint.getY() - 3, 6, 6);
			g.drawRect(this.upperLeftPoint.getX() + this.width - 3, this.upperLeftPoint.getY() - 3, 6, 6);
			g.drawRect(this.upperLeftPoint.getX() - 3, this.upperLeftPoint.getY() + this.height - 3, 6, 6);
			g.drawRect(this.upperLeftPoint.getX() + this.width - 3, this.upperLeftPoint.getY() + this.height - 3, 6, 6);
		}

	}

	@Override
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		g.fillRect(this.upperLeftPoint.getX() + 1, this.getUpperLeftPoint().getY() + 1, width - 1, height - 1);
	}

	public double area() {
		return height * width;
	}

	public boolean contains(int x, int y) {
		if (upperLeftPoint.getX() <= x && this.getUpperLeftPoint().getY() <= y
				&& x <= this.getUpperLeftPoint().getX() + width && y <= this.getUpperLeftPoint().getY() + height) {
			return true;
		} else {
			return false;
		}
	}

	public boolean contains(Point p) {
		if (upperLeftPoint.getX() <= p.getX() && this.getUpperLeftPoint().getY() <= p.getY()
				&& p.getX() <= this.getUpperLeftPoint().getX() + width
				&& p.getY() <= this.getUpperLeftPoint().getY() + height) {
			return true;
		} else {
			return false;
		}
	}

	public boolean equals(Object obj) {
		if (obj instanceof Rectangle) {
			Rectangle prosledjen = (Rectangle) obj;
			if (this.upperLeftPoint.equals(prosledjen.getUpperLeftPoint()) && this.height == prosledjen.getHeight()
					&& this.width == prosledjen.getWidth()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}

	public void setUpperLeftPoint(Point upperLeftPoint) {
		this.upperLeftPoint = upperLeftPoint;
	}

	public String toString() {
		return "Rectangle: (" + upperLeftPoint.getX() + ", " + upperLeftPoint.getY() + "), " + "Width=" + width
				+ ",Height=" + height + ", Edge Color: (" + Integer.toString(getColor().getRGB()) + ")"
				+ ", Inner Color: (" + Integer.toString(getInnerColor().getRGB()) + ")";
	}

	public Rectangle clone(Rectangle r) {

		r.getUpperLeftPoint().setX(this.getUpperLeftPoint().getX());
		r.getUpperLeftPoint().setY(this.getUpperLeftPoint().getY());
		r.setHeight(this.getHeight());
		r.setWidth(this.getWidth());
		r.setColor(this.getColor());
		r.setInnerColor(this.getInnerColor());

		return r;
	}
}
