package command;

import java.util.Collections;

import geometry.Shape;
import mvc.DrawingModel;

public class CmdBringToFront implements Command {

	private DrawingModel model;
	private Shape shape;
	private int position;
	private int last;

	public CmdBringToFront(DrawingModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
		position = model.getShapes().indexOf(shape);
		last = model.getShapes().size() - 1;
	}

	@Override
	public void execute() {
		for (int i = position; i < last; i++) {
			Collections.swap(model.getShapes(), i, i + 1);
		}

	}

	@Override
	public void unexecute() {
		for (int i = last; i > position; i--) {
			Collections.swap(model.getShapes(), i, i - 1);
		}

	}

	@Override
	public String toString() {
		return "Bringed to front - " + this.shape + "\n";
	}

}
