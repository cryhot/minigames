import userinterface.image.*;
import userinterface.render.*;

/** Programme de test pour les sprites animés.
 * Ce fichier à été laissé intentionellement dans le package final.
 * @see userinterface.image
 * @see userinterface.render
 */
@SuppressWarnings("serial")
public class TestSprite extends javax.swing.JFrame {
	static SpriteSheet sp;
	static {
		System.out.println("Starting...");
		sp = new SpriteSheet(new java.io.File("resources/sprites"));
		System.out.println(sp); 
	}
	
	private static AnimatedSprite ghost = new AnimatedSprite(new java.io.File("Ghost.gif"));
	private static AnimatedSprite eyes = new AnimatedSprite(new java.io.File("Ghost_eyes.gif"));
	private static OneTimeAnimatedSprite fall = new OneTimeAnimatedSprite(new java.io.File("Ghost_falling.gif"));
	private static OneTimeAnimatedSprite grow = ImageCrafter.reverse(fall);
	
	public static void main(String... args) {
		TestSprite window = new TestSprite();
		// window.setLayout(new java.awt.GridLayout(0,2));
		
		// ImageCrafter.dye((AnimatedSprite)ghost,new java.awt.Color(.1f,.1f,.1f,.95f)); // DYE
		// ImageCrafter.dye((AnimatedSprite)ghost,new java.awt.Color(.0f,.0f,.0f,.97f)); // DYE
				
		AnimatedImage[] imglist = new AnimatedImage[35*20];
		for (int i=0;i<imglist.length;i++)
			// imglist[i] = new ShiftedAnimatedImage(sp.GHOST(),(int)(Math.random()*8));
			// imglist[i] = new ShiftedAnimatedImage(sp.GHOST_FALLING(),(int)(Math.random()*100));
			// imglist[i] = new TransitionAnimatedImage((int)(Math.random()*200),sp.GHOST_RAISING(),sp.GHOST()); //monter
			imglist[i] = new TransitionAnimatedImage(sp.GHOST(),sp.GHOST_FALLING(),8+(int)(Math.random()*200)); //descendre
		FramedCanvas c = new FramedCanvas(50) { // CANVAS
			AnimatedImage[] l = imglist;
			public void paintComponent(java.awt.Graphics g) {
				super.paintComponent(g);
				//*
				for (int x=0;x<=34;x++)
					for (int y=0;y<=19;y++) {
						this.drawImage(g,sp.BORDER_SQUARE,68*x,68*y);
						this.drawImage(g,sp.TILE_SQUARE,68*x+4,68*y+4);
						this.drawImage(g,sp.GHOST(new java.awt.Color( (int)(Math.random()*3)*80, (int)(Math.random()*3)*80, (int)(Math.random()*3)*80 )),68*x+4,68*y+4); // SYNCHRO
						this.drawImage(g,sp.GHOST_EYES(),68*x+4,68*y+4);
						// this.drawImage(g,l[35*y+x],68*x+4,68*y+4); // ASYNCHRO
					}
				/*/
				double n = 10;
				n = Math.pow(1.01,this.getFrame()-20*5);
				// n = Math.pow(1.1,20*5-this.getFrame());
				// n = Math.pow(1.1+this.getFrame()/100.,Math.cos(this.getFrame()/10.)+.5);
				// n = 4.+Math.random()/10.;
				for (int x=0;x<=35/n;x++)
					for (int y=0;y<=20/n;y++) {
						this.drawImage(g,sp.BORDER_SQUARE,(int)( 68*x*n ),(int)( 68*y*n ),(int)( 72*n ),(int)( 72*n ));
						this.drawImage(g,sp.TILE_SQUARE,(int)( (68*x+4)*n ),(int)( (68*y+4)*n ),(int)( 72*n ),(int)( 72*n ));
						this.drawImage(g,((x+y)%2==0?sp.SOUL_GOOD:sp.GHOST()),(int)( (68*x+4)*n ),(int)( (68*y+4)*n ),(int)( 64*n ),(int)( 64*n )); // COMPARAISON
						this.drawImage(g,((x+y)%2==0?null:sp.GHOST_EYES(sp.SOUL_GOOD)),(int)( (68*x+4)*n ),(int)( (68*y+4)*n ),(int)( 64*n ),(int)( 64*n ));
						// this.drawImage(g,sp.SOUL_GOOD,(int)( (66*x+4+12)*n ),(int)( (66*y+4+8)*n ),(int)( 64*n ),(int)( 64*n )); // 3D
					}
				//*/
				g.setColor(new java.awt.Color(255,64,8));
				g.fillRect(30,30,80, 5);
				g.fillRect(30,30, 5,80);
				g.fillRect(this.getWidth()-110,this.getHeight()- 35,80, 5);
				g.fillRect(this.getWidth()- 35,this.getHeight()-110, 5,80);
			}
		};
		window.getContentPane().add(c);
		
		window.setVisible(true);
		
	}
	
	public TestSprite() {
		super();
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		this.setTitle("test de sprites");
		this.setSize(600,400);
		this.setBackground(new java.awt.Color(255,127,255));
	}

}