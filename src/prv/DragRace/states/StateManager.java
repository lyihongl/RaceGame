package prv.DragRace.states;

public class StateManager {
	
	private static State currentState;
	
	public StateManager(){
		
	}

	public static State getCurrentState() {
		return currentState;
	}

	public static void setCurrentState(State currentState) {
		StateManager.currentState = currentState;
	}
	
}
