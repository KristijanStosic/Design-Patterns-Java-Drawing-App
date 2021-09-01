package command;

import adapter.HexagonAdapter;
import geometry.Point;

public class CmdHexagonModify implements Command {
	private HexagonAdapter oldState;
	private HexagonAdapter newState;
	private HexagonAdapter original = new HexagonAdapter(new Point(0, 0), 0);

	public CmdHexagonModify(HexagonAdapter oldState, HexagonAdapter newState) {
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
		oldState.setHexagonCenter(original.getHexagonCenter());
		oldState.getHexagonCenter().setY(original.getHexagonCenter().getY());
		oldState.setHexagonRadius(original.getHexagonRadius());
		oldState.setHexagonBorderColor(original.getHexagonBorderColor());
		oldState.setHexagonInnerColor(original.getHexagonInnerColor());
	}

	@Override
	public String toString() {
		return "Modified - " + this.original + " " + "->" + " " + this.newState + "\n";
	}

}
