package prv.DragRace.Entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import prv.DragRace.utils.Handler;

public abstract class GameObject {
	
	protected Image sprite;
	protected Handler handler;
	
	public GameObject(Image sprite, Handler handler){
		this.sprite = sprite;
		this.handler = handler;
	}
	
	public abstract void tick();
	public abstract void render(GraphicsContext gc);

	
	public Image getSprite() {
		return sprite;
	}

	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}
	
}
