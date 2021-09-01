package command;

import java.util.Collections;

import geometry.Shape;
import mvc.DrawingModel;

public class CmdToBack implements Command {
	private DrawingModel model;
	private Shape shape;
	private int index;

	public CmdToBack(DrawingModel model, Shape shape) {
		this.model = model;
		this.shape = shape;
		this.index = model.getShapes().indexOf(shape);
	}

	@Override
	public void execute() {
		if (index > 0) {
			Collections.swap(model.getShapes(), index, index - 1);
			index -= 1;
		}
	}

	@Override
	public void unexecute() {

		if (index < model.getShapes().size() - 1) {
			Collections.swap(model.getShapes(), index, index + 1);
			index += 1;
		}
	}

	@Override
	public String toString() {
		return "Moved to back - " + this.shape + "\n";
	}
}
