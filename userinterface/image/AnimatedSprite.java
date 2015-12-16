package userinterface.image;

import java.awt.image.BufferedImage;

import java.util.Collection;
import java.util.ArrayList;

/** Un <code>AnimatedSprite</code> est une séquence d'images à afficher en boucle (comme un GIF).
 * Les données de chaque {@link BufferedImage} sont contenues dans le sprite animé.
 */
public class AnimatedSprite extends AnimatedImage implements Iterable<BufferedImage>, Cloneable {
	private ArrayList<BufferedImage> frames;
	
	public static final AnimatedSprite NULL_SPRITE = new AnimatedSprite(null);
	
	/** Construit un sprite animé à partir d'une liste d'images.
	 * @param frames  la liste des images
	 */
	public AnimatedSprite(Collection<? extends BufferedImage> frames) {
		if (frames!=null)
			this.frames = new ArrayList<BufferedImage>(frames);
		else
			this.frames = new ArrayList<BufferedImage>(0);
	}
	
	/** Construit un sprite animé à partir d'un stream (fichier, etc...).
	 * @param stream  un fichier, etc...
	 */
	public AnimatedSprite(Object stream) {
		this(ImageCrafter.readGif(stream));
	}
	
	@Override
	public AnimatedSprite clone() {
		return new AnimatedSprite(this.cloneFrames());
	}
	
	protected ArrayList<BufferedImage> cloneFrames() {
		return ImageCrafter.copy(this.frames);
	}
	
	@Override
	public BufferedImage toImage(long frame) {
		int size = this.frames();
		if (size==0)
			return null;
		int f = (int)(frame%size);
		return this.frames.get( f<0 ? f+size : f );
	}
	
	/** Renvoie la période du sprite animé, c'est à dire son nombre d'image.
	 * @return  la période de ce sprite animé
	 */
	public int frames() {
		return this.frames.size();
	}
	
	@Override
	public java.util.Iterator<BufferedImage> iterator() {
		return this.frames.iterator();
	}
	
	public static AnimatedSprite notNull(AnimatedSprite image) {
		return (AnimatedSprite) AnimatedImage.notNull(image);
	}
	
}