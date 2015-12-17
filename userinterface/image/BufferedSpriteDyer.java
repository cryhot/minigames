package userinterface.image;

import java.awt.image.BufferedImage;
import java.awt.Color;

import util.BufferedTransformation;

public final class BufferedSpriteDyer<Img,Tint> extends BufferedTransformation<Img,Tint> {
	private static final int BUFFERED_IMAGE = 0;
	private static final int ANIMATED_SPRITE = 1;
	private final int type;
	
	public BufferedSpriteDyer(Img source) {
		super(source);
		if (source==null)
			this.type = -1;
		else if (source instanceof BufferedImage)
			this.type = BUFFERED_IMAGE;
		else if (source instanceof AnimatedSprite)
			this.type = ANIMATED_SPRITE;
		else
			throw new ClassCastException("Img doesn't accept "+source.getClass().getCanonicalName());
	}
	
	protected Img transform(Tint tint) {
		Color col;
		if (tint instanceof Color)
			col = (Color)tint;
		else if (tint instanceof BufferedImage)
			col = ImageCrafter.ambiantColor( (BufferedImage)tint );
		else if (tint instanceof AnimatedSprite)
			col = ImageCrafter.ambiantColor( (AnimatedSprite)tint );
		else
			throw new ClassCastException("unexpected exception");
		Object result;
		switch (this.type) {
			case BUFFERED_IMAGE:
				result = ImageCrafter.dye(
					ImageCrafter.copy( (BufferedImage)(this.getSource()) )
				,col);
				break;
			case ANIMATED_SPRITE:
				result = ImageCrafter.dye(
					((AnimatedSprite)this.getSource()).clone()
				,col);
				break;
			default:
				result = null;
		}
		@SuppressWarnings("unchecked")
		Img res = (Img)result;
		return res;
	}
	
	@Override
	public Img get(Tint tint) {
		if (!( tint==null || tint instanceof Color || tint instanceof BufferedImage || tint instanceof AnimatedSprite ))
			throw new ClassCastException("Tint doesn't accept "+tint.getClass().getCanonicalName());
		return super.get(tint);
	}
	
}