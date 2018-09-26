package prv.DragRace.input;

import javafx.event.EventHandler;
import javafx.scene.input.ScrollEvent;

public class ScrollInput implements EventHandler<ScrollEvent> {

	private static double scrollY;
	private static boolean scrolling = false;
	private static int counter = 0;

	@Override
	public void handle(ScrollEvent event) {
		scrolling = true;
		counter++;
		// System.out.println(event.getDeltaY());
		// scrollY = event.getDeltaY();
		// System.out.println(scrollY);
		scrollY = event.getDeltaY();
	}

	private static int previousCount = 0;

	public static double getScrollY() {

		if (previousCount != counter) {
			previousCount = counter;
			counter = 0;
		} else {
			scrolling = false;
		}
		if (scrolling)
			return scrollY;
		else
			return 0;
	}

}
