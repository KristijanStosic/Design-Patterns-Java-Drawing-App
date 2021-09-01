package command;

import java.util.Collections;

import geometry.Shape;
import mvc.DrawingModel;

public class CmdBringToBack implements Command {

	private DrawingModel model;
	private Shape shape;
	private int position;

	public CmdBringToBack(DrawingModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
		position = model.getShapes().indexOf(shape);
	}

	@Override
	public void execute() {
		for (int i = position; i > 0; i--) {
			Collections.swap(model.getShapes(), i, i - 1);
		}

	}

	@Override
	public void unexecute() {
		for (int i = 0; i < position; i++) {
			Collections.swap(model.getShapes(), i, i + 1);
		}

	}

	@Override
	public String toString() {
		return "Bringed to back - " + this.shape + "\n";
	}

}
