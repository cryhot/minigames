package userinterface.render;

import java.io.File;
import java.awt.image.BufferedImage;
import java.awt.Color;

import userinterface.image.*;

/**
 * <code>SpriteSheet</code> est la classe permettant de charger en mémoire la bibliothèque d'image de Ghost.
 * Elle propose plein d'attributs <code>public</code> à utiliser en lecture seule, pour le bon déroulement de votre programme.
 * Ces attributs sont pour la plupart des sprites et image.
 * <br><br>
 * Cette classe doit bien être instanciée pour fonctionner correctement - les attributs seront alors accessibles par le biais de l'instance créée.
 * @see BufferedImage
 * @see AnimatedSprite
 * @see OneTimeAnimatedSprite
 */
public final class SpriteSheet {
	private File source;
	private int loaded;
	private int failed;
	private int step;
	
	public BufferedImage TILE_SQUARE;
	public BufferedImage TILE_SQUARE_ESCAPE;
	public BufferedImage TILE_HEXAGON;
	public BufferedImage TILE_HEXAGON_ESCAPE;
	public BufferedImage BORDER_SQUARE;
	public BufferedImage BORDER_HEXAGON;
	
	private BufferedSpriteDyer<       AnimatedSprite,Color> GHOST;
	private BufferedSpriteDyer<OneTimeAnimatedSprite,Color> GHOST_FALLING;
	private BufferedSpriteDyer<OneTimeAnimatedSprite,Color> GHOST_RAISING;
	
	public AnimatedSprite SOUL_UNKNOWN;
	public AnimatedSprite SOUL_GOOD;
	public AnimatedSprite SOUL_BAD;
	public AnimatedSprite SOUL_SGOOD;
	public AnimatedSprite SOUL_SBAD;
	public AnimatedSprite SOUL_KNIGHT;
	public AnimatedSprite SOUL_JOKER;
	
	private BufferedSpriteDyer<AnimatedSprite,Object> GHOST_EYES;
	
	public AnimatedSprite        GHOST(Color c) { return GHOST.get(c); }
	public AnimatedSprite        GHOST()        { return GHOST.get(null); }
	public OneTimeAnimatedSprite GHOST_FALLING(Color c) { return GHOST_FALLING.get(c); }
	public OneTimeAnimatedSprite GHOST_FALLING()        { return GHOST_FALLING.get(null); }
	public OneTimeAnimatedSprite GHOST_RAISING(Color c) { return GHOST_RAISING.get(c); }
	public OneTimeAnimatedSprite GHOST_RAISING()        { return GHOST_RAISING.get(null); }
	
	public AnimatedSprite GHOST_EYES(Color c)          { return GHOST_EYES.get(c); }
	public AnimatedSprite GHOST_EYES(BufferedImage i)  { return GHOST_EYES.get(i); }
	public AnimatedSprite GHOST_EYES(AnimatedSprite s) { return GHOST_EYES.get(s); }
	public AnimatedSprite GHOST_EYES()                 { return GHOST_EYES.get(null); }
	
	/**
	 * Construit une nouvelle spritesheet à partir d'un fichier.
	 * Si les images ne sont pas chargées automatiquement, ce sera à l'utilisateur d'appeler la méthode {@link #reload()}.
	 * @param source  le fichier où se trouvent les sprites
	 * @param  load  indique si les images doivent être chargées à partir du fichier à la construction
	 */
	public SpriteSheet(File source,boolean load) {
		this.source = source;
		if (load)
			this.reload();
		else {
			this.loaded = 0;
			this.failed = 0;
			this.step = -1;
			GHOST         = new BufferedSpriteDyer<       AnimatedSprite,Color>(null);
			GHOST_FALLING = new BufferedSpriteDyer<OneTimeAnimatedSprite,Color>(null);
			GHOST_RAISING = new BufferedSpriteDyer<OneTimeAnimatedSprite,Color>(null);
			GHOST_EYES    = new BufferedSpriteDyer<       AnimatedSprite,Object>(null);
		}
	}
	
	/**
	 * Construit une nouvelle spritesheet à partir d'un fichier.
	 * Par défaut, les images sont chargées automatiquement.
	 * @param source  le fichier où se trouvent les sprites
	 */
	public SpriteSheet(File source) {
		this(source,true);
	}
	
	private File localFile(String filename) {
		if (filename==null || this.source==null || !this.source.isDirectory())
			return null;
		return new File(this.source,filename);
	}
	
	private void loadIncrement(Object loadedObj) {
		this.loaded++;
		if (loadedObj==null)
			this.failed++;
	}
	
	private BufferedImage reloadBufferedImage(String filename,BufferedImage defaultImg) {
		BufferedImage img = ImageCrafter.read(this.localFile(filename));
		loadIncrement(img);
		return img!=null?img:defaultImg;
	}
	private BufferedImage reloadBufferedImage(String filename) {
		return this.reloadBufferedImage(filename,null);
	}
	
	private AnimatedSprite reloadAnimatedSprite(String filename,AnimatedSprite defaultSprite) {
		AnimatedSprite sprite = new AnimatedSprite(this.localFile(filename));
		if (sprite.frames()<=0)
			sprite = null;
		loadIncrement(sprite);
		return sprite!=null?sprite:defaultSprite;
	}
	private AnimatedSprite reloadAnimatedSprite(String filename) {
		return this.reloadAnimatedSprite(filename,null);
	}
	
	private OneTimeAnimatedSprite reloadOneTimeAnimatedSprite(String filename,OneTimeAnimatedSprite defaultSprite) {
		OneTimeAnimatedSprite sprite = new OneTimeAnimatedSprite(this.localFile(filename));
		if (sprite.frames()<=0)
			sprite = null;
		loadIncrement(sprite);
		return sprite!=null?sprite:defaultSprite;
	}
	private OneTimeAnimatedSprite reloadOneTimeAnimatedSprite(String filename) {
		return this.reloadOneTimeAnimatedSprite(filename,null);
	}
	
	/**
	 * Charge en mémoire les images à partir du fichier source du spritesheet.
	 * Cette opération est couteuse en temps, veillez à l'utiliser à bon escient.
	 */
	public void reload() {
		this.loaded = 0;
		this.failed = 0;
		this.step = 0;
		
		TILE_SQUARE         = this.reloadBufferedImage("Tile_square.png");          step++;
		TILE_SQUARE_ESCAPE  = this.reloadBufferedImage("Tile_square_escape.png");   step++;
		TILE_HEXAGON        = this.reloadBufferedImage("Tile_hexagon.png");         step++;
		TILE_HEXAGON_ESCAPE = this.reloadBufferedImage("Tile_hexagon_escape.png");  step++;
		BORDER_SQUARE       = this.reloadBufferedImage("Border_square.png");        step++;
		BORDER_HEXAGON      = this.reloadBufferedImage("Border_hexagon.png");       step++;
		
		GHOST         = new BufferedSpriteDyer<       AnimatedSprite,Color>( this.reloadAnimatedSprite("Ghost.gif")                );  step++;
		GHOST_FALLING = new BufferedSpriteDyer<OneTimeAnimatedSprite,Color>( this.reloadOneTimeAnimatedSprite("Ghost_falling.gif") );  step++;
		GHOST_RAISING = new BufferedSpriteDyer<OneTimeAnimatedSprite,Color>( ImageCrafter.reverse(GHOST_FALLING.getSource())       );  step++;
		
		SOUL_UNKNOWN  = this.reloadAnimatedSprite("Soul_unknown.gif"            );  step++;
		SOUL_GOOD     = this.reloadAnimatedSprite("Soul_good.gif"  ,SOUL_UNKNOWN);  step++;
		SOUL_BAD      = this.reloadAnimatedSprite("Soul_bad.gif"   ,SOUL_UNKNOWN);  step++;
		SOUL_SGOOD    = this.reloadAnimatedSprite("Soul_Sgood.gif" ,SOUL_GOOD   );  step++;
		SOUL_SBAD     = this.reloadAnimatedSprite("Soul_Sbad.gif"  ,SOUL_BAD    );  step++;
		SOUL_KNIGHT   = this.reloadAnimatedSprite("Soul_knight.gif",SOUL_UNKNOWN);  step++;
		SOUL_JOKER    = this.reloadAnimatedSprite("Soul_joker.gif" ,SOUL_UNKNOWN);  step++;
		
		GHOST_EYES = new BufferedSpriteDyer<AnimatedSprite,Object>( this.reloadAnimatedSprite("Ghost_eyes.gif") );  step++;
		GHOST_EYES.preload(SOUL_GOOD,SOUL_BAD,SOUL_SGOOD,SOUL_SBAD,SOUL_KNIGHT,SOUL_JOKER);  step+=6;
		
		this.step = -1;
	}
	
	public int getLoaded() { return this.loaded; }
	public int getCorrectlyLoaded() { return this.loaded-this.failed; }
	public int getWronglyLoaded() { return this.failed; }
	
	public int getStep() { return this.step; }
	public int getMaxStep() { return 22; }
	public boolean isLoading() { return this.step>=0; }
	
	public String toString() {
		String filename;
		try {
			filename = this.source.getCanonicalPath();
		} catch (NullPointerException e) {
			filename = "<NoSource>";
		} catch (java.io.IOException e) {
			filename = "<SourceNotFound> "+this.source.getAbsolutePath();
		}
		return "SpriteSheet : "+filename+" ("+(this.loaded-this.failed)+"/"+this.loaded+" sprites loaded )";
	}
	
}