package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

import server_interaction.Client;
import user.User;

public class GUI {
	private JFrame frame;
	private ImagePanel gl_panel;
	private ArrayList<Component> data= new ArrayList<>();
	private final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	private JTextField login_tf;
	private JTextField password_tf;
	public GUI(User user){
		createFrame();
		Client client = new Client(user);
		if(!user.isAuthorized()){
			authorization(user);
		}
	}
	private void createFrame(){
		frame = new JFrame("RusCrypt");
		gl_panel = new ImagePanel();
		
		gl_panel.setBackground(Color.black);
		gl_panel.setLayout(null);
		try {
			gl_panel.setImage(ImageIO.read(new File("img/log_in_bg.jpg")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000,600);
		frame.setResizable(false);
		frame.add(gl_panel);
		frame.setLocation(screen.width/2-frame.getWidth()/2, screen.height/2-frame.getHeight()/2);
		frame.setVisible(true);
		
	}
	private void add(JPanel c,Component c1){
		c.add(c1);
		data.add(c);
		c.repaint();
	}
	private void authorization(User user){
		JPanel autho = new JPanel();
		autho.setLayout(null);
		autho.setSize(250,300);
		autho.setLocation(gl_panel.getWidth()/2-autho.getWidth()/2, gl_panel.getHeight()/2-autho.getWidth()/2);
		autho.setBackground(Color.BLACK);
		addToGl_panel(autho);
		login_tf = new JTextField();
		password_tf = new JTextField();
		login_tf.setSize(100,20);
		password_tf.setSize(100,20);
		
		password_tf.setLocation(0,50);
		add(autho,login_tf);
		add(autho,password_tf);
		
		JLabel submit = new JLabel("Submit");
		submit.setForeground(Color.red);
		submit.setSize(50,30);
		submit.setLocation(0,100);
		submit.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				
			}
		});
		add(autho,submit);
	}
	private void cleanGL_panel(){
		for(Component c : data){
			gl_panel.remove(c);
			
		}
		data.clear();
		frame.repaint();
	}
	private void addToGl_panel(Component c){
		gl_panel.add(c);
		data.add(c);
		frame.repaint();
	}
}
