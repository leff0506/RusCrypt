package gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.JPanel;

public class  ImagePanel extends JPanel{
	 private Image image;
	 
	 public Image getImage() {
	     return image;
	 }
	
	 public void setImage(Image image) {
	     this.image = image;
	 }
	 
	 public void paintComponent(Graphics g) {
	     super.paintComponent(g);
	     if(image != null){
	    	 Graphics2D g2D = (Graphics2D) g.create();
	         g2D.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	     }
	 }
}
