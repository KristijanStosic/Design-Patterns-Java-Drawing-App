package geometry;

import java.awt.Color;
import java.awt.Graphics;

@SuppressWarnings("serial")
public class Circle extends SurfaceShape {

	private Point center = new Point();
	protected int radius;

	public Circle() {

	}

	public Circle(Point center, int radius) {
		this.center = center;
		this.radius = radius;
	}

	public Circle(Point center, int radius, boolean selected) {
		this(center, radius);
		setSelected(selected);
	}

	public Circle(Point center, int radius, boolean selected, Color color) {
		this(center, radius, selected);
		setColor(color);
	}
	
	public Circle(Point center, int radius, Color Color, Color innerColor) {
		this.center = center;
		this.radius = radius;
		setColor(Color);
		setInnerColor(innerColor);
	}

	public Circle(Point center, int radius, boolean selected, Color color, Color innerColor) {
		this(center, radius, selected, color);
		setInnerColor(innerColor);
	}

	@Override
	public int compareTo(Object o) {
		if (o instanceof Circle) {
			return this.radius - ((Circle) o).radius;
		}
		return 0;
	}

	@Override
	public void moveBy(int byX, int byY) {
		this.center.moveBy(byX, byY);
	}

	@Override
	public void fill(Graphics g) {
		g.setColor(getInnerColor());
		g.fillOval(this.center.getX() - radius + 1, this.center.getY() - radius + 1, radius * 2 - 2, radius * 2 - 2);
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(getColor());
		g.drawOval(this.center.getX() - radius, this.center.getY() - radius, this.radius * 2, this.radius * 2);
		fill(g);
		if (isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(this.center.getX() - 3, this.center.getY() - 3, 6, 6);
			g.drawRect(this.center.getX() - radius - 3, this.center.getY() - 3, 6, 6);
			g.drawRect(this.center.getX() + radius - 3, this.center.getY() - 3, 6, 6);
			g.drawRect(this.center.getX() - 3, this.center.getY() - radius - 3, 6, 6);
			g.drawRect(this.center.getX() - 3, this.center.getY() + radius - 3, 6, 6);
		}

	}

	public double area() {
		return radius * radius * Math.PI;
	}

	public boolean contains(int x, int y) {
		return this.center.distance(x, y) <= radius;
	}

	public boolean contains(Point p) {
		return this.center.distance(p.getX(), p.getY()) <= radius;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Circle) {
			Circle prosledjen = (Circle) obj;
			if (this.center.equals(prosledjen.getCenter()) && this.radius == prosledjen.getRadius()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public void selectCircle(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect(this.center.getX() - 3, this.center.getY() - 3, 6, 6);
		g.drawRect(this.center.getX() - radius - 3, this.center.getY() - 3, 6, 6);
		g.drawRect(this.center.getX() + radius - 3, this.center.getY() - 3, 6, 6);
		g.drawRect(this.center.getX() - 3, this.center.getY() - radius - 3, 6, 6);
		g.drawRect(this.center.getX() - 3, this.center.getY() + radius - 3, 6, 6);
	}

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) throws Exception {
		if (radius > 0) {
			this.radius = radius;
		} else {
			throw new NumberFormatException("Radius has to be a value greater then 0!");
		}
	}

	@Override
	public String toString() {
		return "Circle: (" + this.center.getX() + ", " + this.center.getY() + "), " + "Radius=" + this.radius + ", Edge Color: ("+Integer.toString(getColor().getRGB())+")" + ", Inner Color: ("+Integer.toString(getInnerColor().getRGB())+")";
	}

	public Circle clone(Circle c) {
		c.getCenter().setX(this.getCenter().getX());
		c.getCenter().setY(this.getCenter().getY());
		try {
			c.setRadius(this.getRadius());
		} catch (Exception e) {
			throw new NumberFormatException("Radius has to be a value greater then 0!");
		}
		c.setColor(this.getColor());
		c.setInnerColor(this.getInnerColor());

		return c;
	}

}
