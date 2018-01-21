package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import server_interaction.Client;
import user.User;

public class GUI {
	private JFrame frame;
	private ImagePanel gl_panel;
	private ArrayList<Component> data= new ArrayList<>();
	private final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	private final Color COMMON = Color.WHITE;
	private MyTF login_tf;
	private MyPF password_tf;
	
	private MyTF name_tf;
	private MyTF surname_tf;
	
	private Client client;
	public GUI(User user){
		createFrame();
		client = new Client(user);
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
		frame.setSize(1200,740);
		frame.setResizable(false);
		frame.add(gl_panel);
		frame.setLocation(screen.width/2-frame.getWidth()/2, screen.height/2-frame.getHeight()/2);
		frame.setVisible(true);
		
	}
	private void add(JPanel c,Component c1){
		c.add(c1);
		data.add(c);
		frame.repaint();
	}
	private void authorization(User user){
		cleanGL_panel();
		
		ImagePanel autho = new ImagePanel();
		autho.setLayout(null);
		autho.setSize(400,340);
		try {
			autho.setImage(ImageIO.read(new File("img/log_in_bg_1.jpg")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		autho.setLocation(gl_panel.getWidth()/2-autho.getWidth()/2, gl_panel.getHeight()/2-autho.getWidth()/2);
		
		autho.setBackground(new Color(0, 0, 0, 150));
		addToGl_panel(autho);
		
		
		JPanel shadow1 = new JPanel();
		shadow1.setSize(3,340);
		shadow1.setBackground(new Color(0,0,0,140));
		shadow1.setLocation(autho.getX()+autho.getWidth(),autho.getY()+5);
		addToGl_panel(shadow1);
		
		JPanel shadow2 = new JPanel();
		shadow2.setSize(395,3);
		shadow2.setBackground(new Color(0,0,0,140));
		shadow2.setLocation(autho.getX()+5,autho.getY()+autho.getHeight());
		addToGl_panel(shadow2);
		
		JLabel rusCrypt = new JLabel("RusCrypt");
		rusCrypt.setForeground(COMMON);
		rusCrypt.setFont(new Font("Verdinia",Font.ITALIC,30));
		rusCrypt.setSize(200,50);
		rusCrypt.setLocation(autho.getX()+10,autho.getY()-rusCrypt.getHeight());
		addToGl_panel(rusCrypt);
		
		JLabel login_l = new JLabel("Login");
		login_l.setForeground(COMMON);
		login_l.setFont(new Font("Verdinia",Font.ITALIC,20));
		login_l.setSize(130,50);
		login_l.setLocation(60,100);
		add(autho,login_l);
		
		login_tf = new MyTF(315,20);
		login_tf.setBackground(new Color(0,0,0,0));
		login_tf.setBorder(BorderFactory.createEmptyBorder());
		login_tf.setForeground(COMMON);
		login_tf.setHorizontalAlignment(JTextField.CENTER);
		login_tf.setLocation(40,150);
		add(autho,login_tf);
		
		JLabel password_l = new JLabel("Password");
		password_l.setForeground(COMMON);
		password_l.setFont(new Font("Verdinia",Font.ITALIC,20));
		password_l.setSize(130,50);
		password_l.setLocation(50,180);
		add(autho,password_l);
		
		password_tf = new MyPF(315,20);
		password_tf.setBackground(new Color(0,0,0,0));
		password_tf.setBorder(BorderFactory.createEmptyBorder());
		password_tf.setForeground(COMMON);
		password_tf.setHorizontalAlignment(JTextField.CENTER);
		password_tf.setLocation(40,230);
		password_tf.setEchoChar('*');
		add(autho,password_tf);
		
		JLabel autho_l = new JLabel("Authorization");
		autho_l.setForeground(COMMON);
		autho_l.setFont(new Font("Verdinia",Font.ITALIC,25));
		autho_l.setSize(150,50);
		autho_l.setLocation(autho.getWidth()/2-autho_l.getWidth()/2,10);
		add(autho,autho_l);
		
		JLabel submit = new JLabel("submit");
		submit.setFont(new Font("Verdinia",Font.ITALIC,20));
		submit.setForeground(COMMON);
		submit.setSize(80,50);
		submit.setLocation(autho.getWidth()-submit.getWidth(),autho.getHeight()-submit.getHeight());
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
				user.setLogin(login_tf.getText());
				user.setPassword(password_tf.getText());
				login_tf.setText("");
				password_tf.setText("");
				client.send("authorization:"+user.getLogin()+"/"+user.getPassword());
				
			}
		});

		JLabel sign_up = new JLabel("sign up");
		sign_up.setFont(new Font("Verdinia",Font.ITALIC,20));
		sign_up.setForeground(COMMON);
		sign_up.setSize(70,40);
		sign_up.setLocation(autho.getX()+autho.getWidth()-sign_up.getWidth(),autho.getY()-sign_up.getHeight());
		sign_up.addMouseListener(new MouseListener() {
			
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
				signUp(user);
				
			}
		});
		addToGl_panel(sign_up);
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
	private void signUp(User user){
		cleanGL_panel();
		ImagePanel sign_up_p = new ImagePanel();
		sign_up_p.setLayout(null);
		sign_up_p.setSize(400,340);
		try {
			sign_up_p.setImage(ImageIO.read(new File("img/log_in_bg_1.jpg")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sign_up_p.setLocation(gl_panel.getWidth()/2-sign_up_p.getWidth()/2, gl_panel.getHeight()/2-sign_up_p.getWidth()/2);
		
		sign_up_p.setBackground(new Color(0, 0, 0, 150));
		addToGl_panel(sign_up_p);
		
		JPanel shadow1 = new JPanel();
		shadow1.setSize(3,340);
		shadow1.setBackground(new Color(0,0,0,140));
		shadow1.setLocation(sign_up_p.getX()+sign_up_p.getWidth(),sign_up_p.getY()+5);
		addToGl_panel(shadow1);
		
		JPanel shadow2 = new JPanel();
		shadow2.setSize(395,3);
		shadow2.setBackground(new Color(0,0,0,140));
		shadow2.setLocation(sign_up_p.getX()+5,sign_up_p.getY()+sign_up_p.getHeight());
		addToGl_panel(shadow2);
		
		JLabel rusCrypt = new JLabel("RusCrypt");
		rusCrypt.setForeground(COMMON);
		rusCrypt.setFont(new Font("Verdinia",Font.ITALIC,30));
		rusCrypt.setSize(200,50);
		rusCrypt.setLocation(sign_up_p.getX()+10,sign_up_p.getY()-rusCrypt.getHeight());
		addToGl_panel(rusCrypt);
		
		JLabel sign_up_l = new JLabel("Registration");
		sign_up_l.setForeground(COMMON);
		sign_up_l.setFont(new Font("Verdinia",Font.ITALIC,25));
		sign_up_l.setSize(150,50);
		sign_up_l.setLocation(sign_up_p.getWidth()/2-sign_up_l.getWidth()/2,10);
		add(sign_up_p,sign_up_l);
		
		JLabel login_l = new JLabel("Login");
		login_l.setForeground(COMMON);
		login_l.setFont(new Font("Verdinia",Font.ITALIC,20));
		login_l.setSize(130,50);
		login_l.setLocation(60,100);
		add(sign_up_p,login_l);
		
		login_tf = new MyTF(130,20);
		login_tf.setBackground(new Color(0,0,0,0));
		login_tf.setBorder(BorderFactory.createEmptyBorder());
		login_tf.setForeground(Color.WHITE);
		login_tf.setHorizontalAlignment(JTextField.CENTER);
		login_tf.setLocation(40,150);
		add(sign_up_p,login_tf);
		
		JLabel password_l = new JLabel("Password");
		password_l.setForeground(COMMON);
		password_l.setFont(new Font("Verdinia",Font.ITALIC,20));
		password_l.setSize(130,50);
		password_l.setLocation(50,180);
		add(sign_up_p,password_l);
		
		password_tf = new MyPF(130,20);
		password_tf.setBackground(new Color(0,0,0,0));
		password_tf.setBorder(BorderFactory.createEmptyBorder());
		password_tf.setForeground(COMMON);
		password_tf.setHorizontalAlignment(JTextField.CENTER);
		password_tf.setLocation(40,230);
		password_tf.setEchoChar('*');
		add(sign_up_p,password_tf);
		
		JLabel name_l = new JLabel("Name");
		name_l.setForeground(COMMON);
		name_l.setFont(new Font("Verdinia",Font.ITALIC,20));
		name_l.setSize(130,50);
		name_l.setLocation(255,100);
		add(sign_up_p,name_l);
		
		name_tf  = new MyTF(130,20);
		name_tf .setBackground(new Color(0,0,0,0));
		name_tf .setBorder(BorderFactory.createEmptyBorder());
		name_tf .setForeground(COMMON);
		name_tf .setHorizontalAlignment(JTextField.CENTER);
		name_tf .setLocation(250,150);
		add(sign_up_p,name_tf);
		
		JLabel surname_l = new JLabel("Surname");
		surname_l.setForeground(COMMON);
		surname_l.setFont(new Font("Verdinia",Font.ITALIC,20));
		surname_l.setSize(130,50);
		surname_l.setLocation(255,180);
		add(sign_up_p,surname_l);
		
		surname_tf  = new MyTF(130,20);
		surname_tf .setBackground(new Color(0,0,0,0));
		surname_tf .setBorder(BorderFactory.createEmptyBorder());
		surname_tf .setForeground(COMMON);
		surname_tf .setHorizontalAlignment(JTextField.CENTER);
		surname_tf .setLocation(250,230);
		add(sign_up_p,surname_tf);
		
		add(sign_up_p,name_tf);
		add(sign_up_p,surname_tf);
		
		
		JLabel submit = new JLabel("submit");
		submit.setFont(new Font("Verdinia",Font.ITALIC,20));
		submit.setForeground(COMMON);
		submit.setSize(80,50);
		submit.setLocation(sign_up_p.getWidth()-submit.getWidth(),sign_up_p.getHeight()-submit.getHeight());
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
				registration(user);
			}
		});

		JLabel sign_in = new JLabel("sign in");
		sign_in.setFont(new Font("Verdinia",Font.ITALIC,20));
		sign_in.setForeground(COMMON);
		sign_in.setSize(70,40);
		sign_in.setLocation(sign_up_p.getX()+sign_up_p.getWidth()-sign_in.getWidth(),sign_up_p.getY()-sign_in.getHeight());
		sign_in.addMouseListener(new MouseListener() {
			
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
				
				authorization(user);
			}
		});
		addToGl_panel(sign_in);
		add(sign_up_p,submit);
	}
	private void registration(User user){
		user.setLogin(login_tf.getText());
		user.setPassword(password_tf.getText());
		user.setName(name_tf.getText());
		user.setSurname((surname_tf.getText()));
		login_tf.setText("");
		password_tf.setText("");
		name_tf.setText("");
		surname_tf.setText("");
		client.send("registration:"+user.getLogin()+"/"+user.getPassword()+"/"+user.getName()+"/"+user.getSurname());
	}
}
