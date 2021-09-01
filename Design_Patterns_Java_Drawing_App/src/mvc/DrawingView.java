package mvc;

import java.awt.Graphics;
import java.util.Iterator;

import javax.swing.JPanel;

import geometry.Shape;

public class DrawingView extends JPanel {
	private DrawingModel model = new DrawingModel();
	
	private static final long serialVersionUID = 1L;

	public void setModel(DrawingModel model) {
		this.model = model;
	}

	public DrawingModel getModel() {
		return model;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (model != null) {
			Iterator<Shape> it = model.getShapes().iterator();
			while (it.hasNext()) {
				it.next().draw(g);
			}
		}
	}
}
