package command;

import geometry.Point;

public class CmdPointModify implements Command {

	private Point oldState;
	private Point newState;
	private Point original = new Point();

	public CmdPointModify(Point oldState, Point newState) {
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
