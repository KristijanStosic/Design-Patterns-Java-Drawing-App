package command;

import geometry.Donut;

public class CmdDonutModify implements Command {
	private Donut oldState;
	private Donut newState;
	private Donut original = new Donut();

	public CmdDonutModify(Donut oldState, Donut newState) {
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
