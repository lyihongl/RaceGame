package prv.DragRace.gfx;

import javafx.scene.image.Image;

public class Assets {
	public static Image WRX_STI, tsukuba;

	public static void init() {
		Image sheet = ImageUtils.loadImageFrom("file:res/SpriteSheet1.png");
		WRX_STI = ImageUtils.crop(sheet, 0, 0, 20, 32);
		tsukuba = new Image("file:res/Tsukuba-1.jpg", 7168, 5068, false, false);
	}
}
