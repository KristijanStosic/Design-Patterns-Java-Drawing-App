package command;

import geometry.Circle;

public class CmdCircleModify implements Command {
	private Circle oldState;
	private Circle newState;
	private Circle original = new Circle();

	public CmdCircleModify(Circle oldState, Circle newState) {
		this.oldState = oldState;
		this.newState = newState;
	}

	@Override
	public void execute() {
		original = oldState.clone(original);
		oldState = newState.clone(oldState);

	}

	@Override
	public void unexecute() {
		oldState = original.clone(oldState);
	}

	@Override
	public String toString() {
		return "Modified - " + this.original + " " + "->" + " " + this.newState + "\n";
	}
}
