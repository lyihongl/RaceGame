package prv.DragRace.input;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import prv.DragRace.Game;

public class KeyInput implements EventHandler<KeyEvent> {

	private static boolean[] keys = new boolean[256];

	/**
	 * 0 = up, 1 = right, 2 = down, 3 = left
	 */
	private static boolean arrowKeys[] = new boolean[4];
	
	private static KeyCode keyCode;
	
	private static boolean released = true;
	
	
	
//	private KeyCode code;
	@Override
	public void handle(KeyEvent event) {
		// key pressed
		if (event.getEventType() == KeyEvent.KEY_PRESSED) {
			
//			System.out.println(event.getCode().getName());
			
			keyCode = event.getCode();
			
//			System.out.print(event.getText());
//			KeyCode.
//			System.out.println(event.getCode());
			if(event.getCode()==KeyCode.UP)
				arrowKeys[0] = true;
			
			if(event.getCode()==KeyCode.RIGHT)
				arrowKeys[1] = true;
			if(event.getCode() == KeyCode.DOWN)
				arrowKeys[2] = true;
			
			if(event.getCode()==KeyCode.LEFT)
				arrowKeys[3] = true;
			
			
			
			if (event.getCode().isLetterKey()) {
				String x = event.getText();
				char a[] = x.toCharArray();
				keys[a[0]] = true;
			}
		}
		

		// key released
		if (event.getEventType() == KeyEvent.KEY_RELEASED) {
			keyCode = null;
			released = true;
			if(event.getCode()==KeyCode.UP)
				arrowKeys[0] = false;
			
			if(event.getCode()==KeyCode.RIGHT)
				arrowKeys[1] = false;
			if(event.getCode() == KeyCode.DOWN)
				arrowKeys[2] = false;
			
			if(event.getCode()==KeyCode.LEFT)
				arrowKeys[3] = false;
			if (event.getCode().isLetterKey()) {
//				System.out.println(event.getText());
				String x = event.getText();
				char a[] = x.toCharArray();
				keys[a[0]] = false;
			}
		}
	}
	
	public static boolean arrowKeys(int i){
		boolean b;
		if (released)
			b = arrowKeys[i];
		else
			b = false;
		if (b)
			released = false;
		return b;
	}
	
	// gets the state of the key pressed
	public static boolean[] getKeys() {
		return keys;
	}
	
	/**
	 * @prv.DragRace.input.simulatePress Simulates a key action from within the code
	 * @param a char of the key desired
	 * @param b boolean value, keys[a] = b
	 */
	public static void simulatePress(char a, boolean b){
		keys[a] = b;
	}

	/**
	 * 
	 * is a buffered input; key a must be released before it can be pressed again
	 * 
	 * @param a desired char key input
	 * @return boolean value
	 */
	public static boolean oneClickKey(char a) {
		boolean b;
		if (released)
			b = keys[a];
		else
			b = false;
		if (b)
			released = false;
		return b;

	}
	public static boolean oneClickKey(int a) {
		boolean b;
		if (released)
			b = keys[a];
		else
			b = false;
		if (b)
			released = false;
		return b;

	}
	
	public static KeyCode getKeyCode(){
		return keyCode;
	}

}
