package prv.DragRace.gfx;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.transform.Rotate;

public class ImageUtils {

	public static Image loadImageFrom(String url) {
		Image image = new Image(url);
		return image;
	}
	
	/**
	 * Crop an image 
	 * @param image The image that will be cropped
	 * @param x X Coordinate of the upper left corner of the cropped image
	 * @param y Y Coordinate of the upper left corner of the cropped image
	 * @param width Width of cropped image
	 * @param height Height of cropped image
	 * @return Image
	 */
	public static Image crop(Image image, int x, int y, int width, int height) {
		PixelReader pr = image.getPixelReader();
		WritableImage newImage = new WritableImage(pr, x, y, width, height);
		return (Image) newImage;
	}
	
	public static void rotate(GraphicsContext gc, double angle, double px, double py) {
		Rotate r = new Rotate(angle, px, py);
		gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
	}

	public static void drawRotatedImage(GraphicsContext gc, Image image, double angle, double tlpx, double tlpy) {
		gc.save(); // saves the current state on stack, including the current
					// transform
		rotate(gc, angle, tlpx + image.getWidth() / 2, tlpy + image.getHeight() / 2);
		gc.drawImage(image, tlpx, tlpy);
		gc.restore(); // back to original state (before rotation)
	}

}
