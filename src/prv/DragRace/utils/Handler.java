package prv.DragRace.utils;

import javafx.scene.Group;
import prv.DragRace.Game;
import prv.DragRace.gfx.GameCamera;
import prv.DragRace.states.State;
import prv.DragRace.states.StateManager;

public class Handler {
	
	private Game game;
	
	private GameCamera GameCamera;
	
	private State gameState;
	
	
//	private State currentState;
	
	public Handler(){
		
	}
	
	public void setGameStateInstance(State state){
		this.gameState = state;
	}
	
	
	
	public void setState(State state){
		//adding non graphics elements to game state
		
		if(state == gameState){
			Group g = game.getMainGroup();
			g.getChildren().add(gameState.nonGraphicsEliments());
			game.setMainGroup(g);
		}
		
		StateManager.setCurrentState(state);
	}
	
	public State getGameStateInstance(){
		return gameState;
	}
	
	public GameCamera getGameCamera() {
		return GameCamera;
	}

	public void setGameCamera(GameCamera gCamera) {
		this.GameCamera = gCamera;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}
	
}
