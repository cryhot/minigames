package userinterface.image;

import java.awt.image.BufferedImage;

public class TransitionAnimatedImage extends AnimatedImage {
	private AnimatedImage befImage;
	private AnimatedImage traImage;
	private AnimatedImage aftImage;
	private long start;
	private long end;
	
	public TransitionAnimatedImage(AnimatedImage befImage, AnimatedImage traImage, AnimatedImage aftImage, long start, long length) {
		this.befImage = befImage;
		this.traImage = AnimatedImage.notNull(traImage);
		this.aftImage = aftImage;
		this.start = start;
		this.end = this.start+(length>=0?length:-length);
	}
	
	public TransitionAnimatedImage(AnimatedImage befImage, OneTimeAnimatedSprite traImage, long end) {
		this(befImage,traImage,null,end-AnimatedSprite.notNull(traImage).frames(),AnimatedSprite.notNull(traImage).frames());
	}
	
	public TransitionAnimatedImage(long start, OneTimeAnimatedSprite traImage, AnimatedImage aftImage) {
		this(null,traImage,aftImage,start,AnimatedSprite.notNull(traImage).frames());
	}
	
	@Override
	public BufferedImage toImage(long frame) {
		if ( befImage!=null && frame<this.start )
			return this.befImage.toImage(frame);
		if ( aftImage!=null && frame>=this.end )
			return this.aftImage.toImage(frame);
		return this.traImage.toImage(frame-start);
	}
	
}