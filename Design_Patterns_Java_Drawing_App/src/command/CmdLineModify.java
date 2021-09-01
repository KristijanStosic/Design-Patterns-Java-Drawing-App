package command;

import geometry.Line;

public class CmdLineModify implements Command {

	private Line oldState;
	private Line newState;
	private Line original = new Line();

	public CmdLineModify(Line oldState, Line newState) {
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
