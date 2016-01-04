package userinterface.graphicalinterface;

import userinterface.render.*;
import core.game.GlobalViewer;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Color;

import java.awt.Dimension;

public class GraphicalView extends FramedCanvas {
	private GlobalViewer viewer;
	private SpriteSheet spriteSheet;
	private BufferedImage frame_background;
	private BufferedImage frame_selection;
	private BufferedImage frame_tiles;
	
	public GraphicalView(GlobalViewer viewer) {
		if (viewer==null)
			throw new NullPointerException();
		this.viewer = viewer;
		this.spriteSheet = new SpriteSheet(new java.io.File("C:/JR/Programmation/Java/Ghosts/sprites design/sprites"));
		this.setPreferredSize(new Dimension(500,300));
		this.setSize(this.getPreferredSize());
		this.redraw_tiles();
	}
	
	private void redraw_tiles() {
		this.frame_background = new BufferedImage(1,1)
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.drawImage(g,this.frame_background,0,0);
		this.drawImage(g,this.frame_selection,0,0);
		this.drawImage(g,this.frame_tiles,0,0);
		
		
		
		
	}
	
}