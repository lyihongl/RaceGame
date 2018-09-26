package prv.DragRace.input;

import javafx.event.EventHandler;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

public class MouseInput implements EventHandler<MouseEvent> {

	private static boolean leftClick, rightClick;
	private static boolean released = true;
	private static double x, y;
	@Override
	public void handle(MouseEvent event) {
		if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
			if(event.getButton() == MouseButton.PRIMARY)
				leftClick = true;
			if(event.getButton() == MouseButton.SECONDARY)
				rightClick = true;
		}
		if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
			if(event.getButton() == MouseButton.PRIMARY)
				leftClick = false;
			if(event.getButton() == MouseButton.SECONDARY)
				rightClick = false;
//			System.out.println("A");
			released = true;
		}
		if(event.getEventType() == MouseEvent.MOUSE_MOVED){
			x = event.getX();
			y = event.getY();
		}
	}

	public static boolean isLeftClick() {
		boolean b;
		if(released)
			b = leftClick;
		else
			b = false;
		if(b)
			released = false;
		return b;
	}

	public static boolean isRightClick() {
		boolean b;
		if(released)
			b = rightClick;
		else
			b = false;
		if(b)
			released = false;
		return b;
	}

	public static double getX() {
		return x;
	}

	public static double getY() {
		return y;
	}
	
	

}
