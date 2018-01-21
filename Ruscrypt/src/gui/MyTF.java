package gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;

class MyTF extends JTextField {
	int width,height;
	public MyTF(int width,int height){
		this.width = width;
		this.height=height;
		super.setSize(width,height);
	}
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        
        
        getRootPane().repaint();
        g.setColor(new Color(255,110,200));
        g.drawLine(0, height-1, width, height-1);
    }

}
