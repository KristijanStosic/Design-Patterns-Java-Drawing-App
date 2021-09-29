package command;

import geometry.Shape;
import mvc.DrawingController;

public class CmdDeselectShape implements Command {
	private DrawingController controller;
	private Shape shape;

	public CmdDeselectShape(DrawingController controller, Shape shape) {
		this.controller = controller;
		this.shape = shape;
	}

	@Override
	public void execute() {
		shape.setSelected(false);
		controller.getSelectedShapes().remove(shape);
	}

	@Override
	public void unexecute() {
		shape.setSelected(true);
		controller.getSelectedShapes().add(shape);
	}

	@Override
	public String toString() {
		return "Deselected - " + this.shape + "\n";
	}
}
