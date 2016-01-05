package userinterface.graphicalinterface;

import java.util.Set;
import java.util.List;
import java.util.ArrayList;

import userinterface.render.*;
import userinterface.image.*;
import core.game.*;
import core.board.Case;
import core.board.Board;
import core.board.Paradigm;
import util.Property;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Color;

import java.awt.Dimension;
import java.awt.Point;

public class GraphicalView extends FramedCanvas {
	private GlobalViewer viewer;
	private SpriteSheet spriteSheet;
	private BufferedImage frame_background;
	private BufferedImage frame_selection;
	private BufferedImage frame_tiles;
	
	private int tile_width,  tileBorder_width;
	private int tile_height, tileBorder_height;
	private int width, height;
	private List<Color> playerColor;
	
	public GraphicalView(GlobalViewer viewer) {
		super(50);
		if (viewer==null)
			throw new NullPointerException();
		this.viewer = viewer;
		this.spriteSheet = new SpriteSheet(new java.io.File("C:/JR/Programmation/Java/Ghosts/sprites design/sprites"));
		this.grade();
	}
	
	/** Evalue les dimentions du plateau, son apparence et les autres paramètres constants.
	 */
	private void grade() {
		this.grade_playerColors();
		this.grade_tiles();
		this.grade_dimentions();
		this.setPreferredSize(new Dimension(this.width,this.height));
		this.frame_background = new BufferedImage(this.width,this.height,BufferedImage.TYPE_INT_RGB );
		this.frame_selection  = new BufferedImage(this.width,this.height,BufferedImage.TYPE_INT_ARGB);
		this.frame_tiles      = new BufferedImage(this.width,this.height,BufferedImage.TYPE_INT_ARGB);
		this.redraw_tiles();
	}
	
	/** Evalue la taille d'une case en pixels.
	 */
	private void grade_tiles() {
		switch (this.viewer.getBoard().paradigm) {
			case SQUARE:
				this.tile_width  = this.spriteSheet.TILE_SQUARE.getWidth() ;
				this.tile_height = this.spriteSheet.TILE_SQUARE.getHeight();
				this.tileBorder_width  = (this.spriteSheet.BORDER_SQUARE.getWidth() -this.tile_width )/2;
				this.tileBorder_height = (this.spriteSheet.BORDER_SQUARE.getHeight()-this.tile_height)/2;
				break;
		}
	}
	
	/** Evalue la taille du plateau en pixels.
	 */
	private void grade_dimentions() {
		Board b = this.viewer.getBoard();
		int x = b.getXMax()-b.getXMin()+1;
		int y = b.getYMax()-b.getYMin()+1;
		switch (b.paradigm) {
			case SQUARE:
				this.width  = this.tileBorder_width  + x*(this.tile_width +this.tileBorder_width );
				this.height = this.tileBorder_height + y*(this.tile_height+this.tileBorder_height);
				break;
		}
	}
	
	/** Evalue la couleur de chaque joueur.
	 */
	private void grade_playerColors() {
		List<Player> players = this.viewer.getLevel().getPlayers();
		this.playerColor = new ArrayList<Color>(players.size());
		switch (players.size()) {
			case 1:
				this.playerColor.add(new Color(1f,1f,1f,0f));
				break;
			case 2:
				this.playerColor.add(new Color(1f,1f,1f,0f));
				this.playerColor.add(new Color(.1f,.1f,.1f,.95f));
				break;
			case 3:
				this.playerColor.add(new Color(1f,0f,0f,1f));
				this.playerColor.add(new Color(0f,1f,0f,1f));
				this.playerColor.add(new Color(0f,0f,1f,1f));
				break;
			default:
				for (Player p:players)
					this.playerColor.add(new java.awt.Color( (int)(Math.random()*3)*80, (int)(Math.random()*3)*80, (int)(Math.random()*3)*80 ));
		}
	}
	
	/** Recalcule les frames d'arrière plan, correspondant au plateau.
	 */
	private void redraw_tiles() {
		Board b = this.viewer.getBoard();
		int minX = b.getXMin();
		int minY = b.getYMin();
		int maxX = b.getXMax();
		int maxY = b.getYMax();
		Graphics2D graph_bg = this.frame_background.createGraphics();
		Graphics2D graph_tl = this.frame_tiles.createGraphics();
		graph_bg.setBackground(new Color(30,0,0));
		graph_tl.setBackground(new Color(0,0,0,1));
		graph_bg.clearRect(0,0,this.width,this.height);
		graph_tl.clearRect(0,0,this.width,this.height);
		switch (this.viewer.getBoard().paradigm) {
			case SQUARE:
				for(int y=minY;y<=maxY;y++)
					for(int x=minX;x<=maxX;x++) {
						Case c = b.getCase(x,y);
						if (c.isInside()) {
							Point coords = this.caseToCoordinates(c);
							int x_ = (int)(coords.getX());
							int y_ = (int)(coords.getY());
							graph_bg.drawImage(this.spriteSheet.BORDER_SQUARE,x_-this.tileBorder_width,y_-this.tileBorder_height,null);
							graph_tl.drawImage(c.isEscape()?this.spriteSheet.TILE_SQUARE_ESCAPE:this.spriteSheet.TILE_SQUARE,x_,y_,null);
						}
					}
				break;
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.drawImage(g,this.frame_background,0,0);
		this.drawImage(g,this.frame_selection,0,0);
		this.drawImage(g,this.frame_tiles,0,0);
		this.paintPawns(g);
		
	}
	
	private void paintPawns(Graphics g) {
		Set<Pawn> onBoard = this.viewer.getLevel().getPawns( new Property<Pawn>() {
			protected boolean validate(Pawn p) { return p.isPlaced(); }
			} );
		for (Pawn p:onBoard) {
			Point coords = this.caseToCoordinates(p.getCase());
			paintPawn(g,p,(int)(coords.getX()),(int)(coords.getY()));
		}
	}
	
	private void paintPawn(Graphics g,Pawn p,int x,int y) {
		this.drawImage(g,this.spriteSheet.GHOST(this.playerColor.get(p.getOwner().getIndex())),x,y);
		this.drawImage(g,this.spriteSheet.GHOST_EYES(this.getSoul(p)),x,y);
	}
	
	/** Renvoie l'image animée de l'âme du fantôme.
	 */
	private AnimatedSprite getSoul(Pawn p) {
		Soul s;
		try {
			s = this.viewer.getSoul(p);
		} catch(UnsupportedOperationException e) {
			return null;
		}
		if(s.equals(s.SOUL_GOOD)) 
			return this.spriteSheet.SOUL_GOOD;
		if(s.equals(s.SOUL_SGOOD))
			return this.spriteSheet.SOUL_SGOOD;
		if(s.equals(s.SOUL_BAD))
			return this.spriteSheet.SOUL_BAD;
		if(s.equals(s.SOUL_SBAD))
			return this.spriteSheet.SOUL_SBAD;
		if(s.equals(s.SOUL_KNIGHT))
			return this.spriteSheet.SOUL_KNIGHT;
		return this.spriteSheet.SOUL_UNKNOWN;
	}
	
	/** Calcule les coordonnées en pixels d'une case donnée.
	 * @param c  la case à évaluer
	 * @return  les coordonnées du coin suppérieur gauche de la case
	 */
	private Point caseToCoordinates(Case c) {
		Board b = this.viewer.getBoard();
		switch (b.paradigm) {
			case SQUARE:
				return new Point(
					this.tileBorder_width  + (c.x-b.getXMin())*(this.tile_width +this.tileBorder_width ),
					this.tileBorder_height + (b.getXMax()-c.y)*(this.tile_height+this.tileBorder_height)
				);
			default:
				return null;
		}
	}
	
	
}