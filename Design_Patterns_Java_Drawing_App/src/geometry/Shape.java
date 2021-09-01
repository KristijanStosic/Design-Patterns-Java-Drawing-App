package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

@SuppressWarnings({ "serial", "rawtypes" })
public abstract class Shape implements Moveable, Comparable, Serializable {

	@SuppressWarnings("unused")
	private static final long SerialVersionUID = 1L;
	
	private boolean selected;
	private Color color;

	public abstract void draw(Graphics g);

	public abstract boolean contains(int x, int y);

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}
