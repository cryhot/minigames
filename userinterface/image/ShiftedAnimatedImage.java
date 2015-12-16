package userinterface.image;

import java.awt.image.BufferedImage;

public class ShiftedAnimatedImage extends AnimatedImage {
	private AnimatedImage image;
	private long start;
	
	public ShiftedAnimatedImage(AnimatedImage image, long start) {
		this.image = AnimatedImage.notNull(image);
		this.start = start;
	}
	
	@Override
	public BufferedImage toImage(long frame) {
		return this.image.toImage(frame-start);
	}
	
}