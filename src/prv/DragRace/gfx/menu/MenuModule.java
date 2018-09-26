package prv.DragRace.gfx.menu;

import javafx.scene.canvas.GraphicsContext;

public class MenuModule{
	
	private String text;
	private int x, y;
	private MenuAction action;
	private MenuModule previous, next;
	
	/**
	 * 
	 * A module that can be added to a menu
	 * 
	 * @param text Text that the user sees
	 * @param x X Location on screen
	 * @param y Y location on screen
	 * @param action Action to be performed when the menu item is selected
	 */
	public MenuModule(String text, int x, int y, MenuAction action){
		this.text = text;
		this.x = x;
		this.y = y;
		this.action = action;
	}
	
	public void onPress(){
		action.action();
	}
	
	public void render(GraphicsContext gc){
		gc.fillText(text, x, y);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public MenuModule getPrevious() {
		return previous;
	}

	public void setPrevious(MenuModule previous) {
		this.previous = previous;
	}

	public MenuModule getNext() {
		return next;
	}

	public void setNext(MenuModule next) {
		this.next = next;
	}
	
	


}
