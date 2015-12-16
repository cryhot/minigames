package userinterface.image;

import java.awt.image.BufferedImage;
import java.util.Collection;

public class OneTimeAnimatedSprite extends AnimatedSprite {
	
	public OneTimeAnimatedSprite(Collection<? extends BufferedImage> frames) {
		super(frames);
	}
	
	public OneTimeAnimatedSprite(Object stream) {
		super(stream);
	}
	
	@Override
	public OneTimeAnimatedSprite clone() {
		return new OneTimeAnimatedSprite(this.cloneFrames());
	}
	
	@Override
	public BufferedImage toImage(long frame) {
		int size = this.frames();
		if (frame>=size)
			frame = size-1;
		if (frame<0)
			frame = 0;
		return super.toImage(frame);
	}
	
}