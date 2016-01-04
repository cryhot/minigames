package userinterface.graphicalinterface;

import java.util.Set;

import userinterface.render.*;
import core.game.GlobalViewer;
import core.game.Player;
import core.game.Pawn;
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
	
	public GraphicalView(GlobalViewer viewer) {
		super(50);
		if (viewer==null)
			throw new NullPointerException();
		this.viewer = viewer;
		this.spriteSheet = new SpriteSheet(new java.io.File("C:/JR/Programmation/Java/Ghosts/sprites design/sprites"));
		this.grade();
	}
	
	/** Evalue les dimentions du plateau et son apparence.
	 */
	private void grade() {
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
		this.drawImage(g,this.spriteSheet.GHOST(),x,y);
		this.drawImage(g,this.spriteSheet.GHOST_EYES(),x,y);
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