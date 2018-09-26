package prv.DragRace.states;

import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import prv.DragRace.utils.Handler;

public abstract class State {
	
	protected Handler handler;
	
	public State(Handler handler){
		this.handler = handler;
	}
	
	public abstract void tick();
	public abstract void render(GraphicsContext gc);
	public abstract Group nonGraphicsEliments();
	
	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

}
