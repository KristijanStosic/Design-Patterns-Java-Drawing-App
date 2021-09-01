package mvc;

import java.util.ArrayList;

import geometry.Shape;

public class DrawingModel {

	private ArrayList<Shape> shapes = new ArrayList<Shape>();

	public DrawingModel() {

	}

	public ArrayList<Shape> getShapes() {
		return shapes;
	}

	public void add(Shape s) {
		shapes.add(s);
	}

	public void remove(Shape s) {
		shapes.remove(s);
	}

	public Shape getOneShape(int index) {
		return shapes.get(index);
	}

}
