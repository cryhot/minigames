package userinterface.image;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import java.awt.Color;

import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.PackedColorModel;

import java.awt.image.Raster;
import java.awt.image.WritableRaster;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import java.io.IOException;

/** Cette classe propose un ensemble de fonctions statiques pour manipuler les images.
 * Cette classe ne peut pas être instanciée.
 */
public final class ImageCrafter {
	
	private ImageCrafter() {
	}
	
	public static BufferedImage copy(BufferedImage img) {
		if (img==null)
			return null;
		return new BufferedImage(
			img.getColorModel(),
			img.copyData(null),
			img.getColorModel().isAlphaPremultiplied(),
			null
		);
	}
	
	public static ArrayList<BufferedImage> copy(ArrayList<BufferedImage> imgs) {
		ArrayList<BufferedImage> frames = new ArrayList<BufferedImage>(imgs.size());
		for (BufferedImage i:imgs)
			frames.add(copy(i));
		return frames;
	}
	
	public static <S extends AnimatedSprite> S copy(S img) {
		if (img==null)
			return null;
		@SuppressWarnings("unchecked")
		S result = (S)(img.clone());
		return result;
	}
	
	public static OneTimeAnimatedSprite reverse(OneTimeAnimatedSprite img) {
		ArrayList<BufferedImage> imgs = img.cloneFrames();
		ArrayList<BufferedImage> frames = new ArrayList<BufferedImage>(imgs.size());
		for (BufferedImage i:imgs)
			frames.add(0,i);
		return new OneTimeAnimatedSprite(frames);
	}
	
	public static BufferedImage dye(BufferedImage img,Color dye) {
		if (img==null)
			return null;
		ColorModel colormodel = img.getColorModel();
		if (!(colormodel instanceof ComponentColorModel || colormodel instanceof PackedColorModel))
			throw new UnsupportedOperationException("this method does not handle images with color model : "+colormodel.getClass().getName());
		if (dye==null)
			return img;
		WritableRaster raster = img.getRaster();
		int minX = raster.getMinX();
		int minY = raster.getMinY();
		int maxX = minX+raster.getWidth();
		int maxY = minY+raster.getHeight();
		float[] hsbDye = Color.RGBtoHSB(dye.getRed(),dye.getGreen(),dye.getBlue(),null);
		float intensity = (dye.getAlpha())/256f;
		for (int x = minX; x < maxX; x++)
			for (int y = minY; y < maxY; y++)
				raster.setPixel(x, y, dye(raster.getPixel(x,y,(int[]) null),hsbDye,intensity));
		return img;
	}
	
	public static AnimatedSprite dye(AnimatedSprite img,Color dye) {
		if (img==null)
			return null;
		for (BufferedImage i:img)
			dye(i,dye);
		return img;
	}
	
	private static int[] dye(int[] rgb,float[] hsbDye,float intensity) {
		// pour une meilleure méthode : https://git.gnome.org/browse/gimp/tree/app/base/colorize.c?h=gimp-2-8
		float[] hsb = Color.RGBtoHSB(rgb[0],rgb[1],rgb[2],null);
		
		hsb[0] = hsbDye[0];
		// hsb[1] = (1+hsb[1])*hsbDye[1]/2;
		hsb[1] = hsbDye[1];
		hsb[2] *= hsbDye[2];
		// hsb[2] = ( hsb[2]*hsbDye[2]+(hsb[2])*(1-hsbDye[2]) );
		// hsb[2] = (float)(0.2126*rgb[0]+0.7152*rgb[1]+0.0722*rgb[2])*hsbDye[2];
		
		int rgbHash = Color.HSBtoRGB(hsb[0],hsb[1],hsb[2]);
		// rgb[0] = (byte)( ( (rgbHash>>16)&0xFF )*intensity + rgb[0]*(1-intensity) );
		// rgb[1] = (byte)( ( (rgbHash>> 8)&0xFF )*intensity + rgb[1]*(1-intensity) );
		// rgb[2] = (byte)( (       rgbHash&0xFF )*intensity + rgb[2]*(1-intensity) );
		rgb[0] = (byte)mix( (rgbHash>>16)&0xFF , rgb[0] , intensity );
		rgb[1] = (byte)mix( (rgbHash>> 8)&0xFF , rgb[1] , intensity );
		rgb[2] = (byte)mix(       rgbHash&0xFF , rgb[2] , intensity );
		return rgb;
	}
	private static int[] dye(int[] rgb,float[] hsbDye) {
		return dye(rgb,hsbDye,1f);
	}
	
	private static int mix(int colorComp,int layer,double prop) {
		return (int)Math.sqrt( Math.pow(colorComp,2)*(prop) + Math.pow(layer,2)*(1-prop) );
	}
	private static int mix(int colorComp,int layer) {
		return mix(colorComp,layer,0.5);
	}
	
	public static Color ambiantColor(BufferedImage img) {
		if (img==null)
			return null;
		double weight = 0;
		double r = 0;
		double g = 0;
		double b = 0;
		Raster raster = img.getRaster();
		int minX = raster.getMinX();
		int minY = raster.getMinY();
		int maxX = minX+raster.getWidth();
		int maxY = minY+raster.getHeight();
		int[] rgb;
		double alpha;
		for (int x = minX; x < maxX; x++)
			for (int y = minY; y < maxY; y++) {
				rgb = raster.getPixel(x,y,(int[]) null);
				alpha = (rgb.length>3?rgb[3]/255D:1D);
				weight += alpha;
				r += Math.pow(rgb[0],2)*alpha;
				g += Math.pow(rgb[1],2)*alpha;
				b += Math.pow(rgb[2],2)*alpha;
			}
		if (weight==0)
			return null;
		return new Color( (int)Math.sqrt(r/weight), (int)Math.sqrt(g/weight), (int)Math.sqrt(b/weight) );
	}
	public static Color ambiantColor(AnimatedSprite img) {
		if (img==null)
			return null;
		double weight = 0;
		double r = 0;
		double g = 0;
		double b = 0;
		for (BufferedImage i:img) {
			Raster raster = i.getRaster();
			int minX = raster.getMinX();
			int minY = raster.getMinY();
			int maxX = minX+raster.getWidth();
			int maxY = minY+raster.getHeight();
			int[] rgb;
			double alpha;
			for (int x = minX; x < maxX; x++)
				for (int y = minY; y < maxY; y++) {
					rgb = raster.getPixel(x,y,(int[]) null);
					alpha = (rgb.length>3?rgb[3]/255D:1D);
					weight += alpha;
					r += Math.pow(rgb[0],2)*alpha;
					g += Math.pow(rgb[1],2)*alpha;
					b += Math.pow(rgb[2],2)*alpha;
				}
		}
		if (weight==0)
			return null;
		return new Color( (int)Math.sqrt(r/weight), (int)Math.sqrt(g/weight), (int)Math.sqrt(b/weight) );
	}
	
	public static BufferedImage read(Object stream) {
		try {
			return ImageIO.read(ImageIO.createImageInputStream(stream));
		} catch (IllegalArgumentException e) {
			return null;
		} catch (IOException e) {
			return null;
		}
	}
	
	public static ArrayList<BufferedImage> readGif(Object stream) {
		// code inspire de : stackoverflow.com/questions/8933893
		ArrayList<BufferedImage> frames;
		try {
			ImageReader reader = ImageIO.getImageReadersByFormatName("gif").next();
			reader.setInput(ImageIO.createImageInputStream(stream), false);
			int num = reader.getNumImages(true);
			frames = new ArrayList<BufferedImage>(num);
			for (int frameIndex = 0 ; frameIndex < num ; frameIndex++) {
				BufferedImage frame = reader.read(frameIndex);
				BufferedImage frameRgb = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_INT_ARGB);
				frameRgb.createGraphics().drawImage(frame, 0, 0, null);
				frames.add(frameRgb);
			}
		} catch (IllegalArgumentException e) {
			return null;
		} catch (IOException e) {
			return null;
		} catch (IllegalStateException e) {
			return null;
		}
		return frames;
	}
	
}