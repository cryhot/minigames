package userinterface.image;

import java.awt.image.BufferedImage;

/** Une <code>AnimatedImage</code> représente une image animée, possédant une image propre pour chaque frame d'affichage.
 * @see BufferedImage
 */
public abstract class AnimatedImage {
	
	/** Renvoie l'image associée à la frame demandée.
	 * @param frame  la frame demandée
	 * @return  l'image correspondante
	 */
	public abstract BufferedImage toImage(long frame);
	
	public static AnimatedImage notNull(AnimatedImage image) {
		if (image!=null)
			return image;
		else
			return AnimatedSprite.NULL_SPRITE;
	}
	
}