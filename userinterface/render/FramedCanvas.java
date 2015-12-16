package userinterface.render;

import javax.swing.JPanel;
import java.awt.LayoutManager;

import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.Graphics;

import java.awt.Image;
import java.awt.image.BufferedImage;
import userinterface.image.AnimatedImage;

public class FramedCanvas extends JPanel {
	private transient long frame;
	private final int spf;
	private Timer timer;
	
	public static final int DEFAULT_SECOND_PER_FRAME = 100;
	
	public FramedCanvas(int spf) {
		super(true);
		this.frame = 0;
		this.spf = spf;
		this.start();
	}
	public FramedCanvas(int spf,LayoutManager layout) {
		super(layout,true);
		this.frame = 0;
		this.spf = spf;
		this.start();
	}
	public FramedCanvas() {
		this(DEFAULT_SECOND_PER_FRAME);
	}
	public FramedCanvas(LayoutManager layout) {
		this(DEFAULT_SECOND_PER_FRAME,layout);
	}
	
	private void start() {
		this.timer = new Timer(this.spf,this.new TaskPerformer());
		this.timer.start();
	}
	
	protected void nextFrame() {
		this.repaint();
		this.frame++;
	}
	
	protected BufferedImage extractImage(AnimatedImage img) {
		if (img == null)
			return null;
		return img.toImage(this.getFrame());
	}
	
	protected final void drawImage(Graphics g, Image img, int x, int y) {
		g.drawImage(img,x,y,this);
	}
	protected final void drawImage(Graphics g, Image img, int x, int y, int width, int height) {
		g.drawImage(img,x,y,width,height,this);
	}
	protected final void drawImage(Graphics g, AnimatedImage img, int x, int y) {
		this.drawImage(g,this.extractImage(img),x,y);
	}
	protected final void drawImage(Graphics g, AnimatedImage img, int x, int y, int width, int height) {
		this.drawImage(g,this.extractImage(img),x,y,width,height);
	}
	
	public final long getFrame() {
		return this.frame;
	}
	
	public final int getSpf() {
		return this.timer.getDelay();
	}
	
	public final void setSpf(int spf) {
		this.timer.setDelay(spf);
		this.timer.setInitialDelay(spf);
	}
	
	private class TaskPerformer implements ActionListener {
		public void actionPerformed(ActionEvent evt) {
			FramedCanvas.this.nextFrame();
		}
	}
	
}