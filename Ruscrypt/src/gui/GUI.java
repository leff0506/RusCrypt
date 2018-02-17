package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowStateListener;
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
	private ArrayList<Component> dataF= new ArrayList<>();
	private ArrayList<String> param =new ArrayList<>();
	private boolean upF=false;
	private boolean selOn = false;
	public static boolean inChat = false;
	public static String inChatWith=null;
	
	private final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	private final Color COMMON = Color.WHITE;
	private MyTF login_tf;
	private MyPF password_tf;
	private JPanel friends_p;
	private JLabel selectOne;
	private MyTF name_tf;
	private MyTF surname_tf;
	private int temp;
	
	
	private Client client;
	public GUI(User user){
		createFrame();
		client = new Client(user,this);
		if(!user.isAuthorized()){
			authorization(user);
		}
		
	}
	private void createFrame(){
		frame = new JFrame("RusCrypt");
		frame.addWindowFocusListener(new WindowFocusListener() {
			
			@Override
			public void windowLostFocus(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void windowGainedFocus(WindowEvent e) {
				// TODO Auto-generated method stub
				if(upF){
					
					if(inChatWith==null) {
						updateFriends();
					}else {
						updateFriends(inChatWith);
					}
					
				}
				if(selOn){
					
					selectOne.setLocation(300,300);
					frame.repaint();
				}
				
			}
		});
			
			
		
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
	private void clearF_panel(){
		for(Component c : dataF){
			friends_p.remove(c);
			
		}
		dataF.clear();
		frame.repaint();
	}
	private void addToGl_panel(Component c){
		gl_panel.add(c);
		data.add(c);
		frame.repaint();
	}
	private void addToF_panel(Component c){
		friends_p.add(c);
		dataF.add(c);
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
	public void logged(){
		cleanGL_panel();
		
		friends_p = new JPanel();
		friends_p.setLocation(0,0);
		friends_p.setSize(300,gl_panel.getHeight());
		friends_p.setBackground(new Color(40,46,51));
		
		JPanel delimiter = new JPanel();
		delimiter.setLocation(friends_p.getX()+friends_p.getWidth(),0);
		delimiter.setSize(5,gl_panel.getHeight());
		delimiter.setBackground(new Color(29,33,38));
		
		JPanel messField = new JPanel();
		messField.setLocation(delimiter.getX()+delimiter.getWidth(),0);
		messField.setSize(gl_panel.getWidth()-(messField.getX()),gl_panel.getHeight());
		messField.setBackground(new Color(0,0,0,100));
		
		selectOne =new JLabel("<---  select one");
		selOn=true;
		selectOne.setSize(200,40);
		selectOne.setForeground(COMMON);
		selectOne.setFont(new Font("Verdana",Font.ITALIC,24));
		selectOne.setLocation(300,300);
		
		///
			client.send("get friends");
		///
		add(messField,selectOne);
		
		addToGl_panel(messField);
		addToGl_panel(delimiter);
		addToGl_panel(friends_p);
	}
	public void renderFriends(String txt){
		
		ArrayList<String> friends = new ArrayList<>();
		int i=0;
		int k =0;
		
		for(int z = 0 ; z < txt.length();z++){
			if(txt.charAt(z)==';'){
				k=z;
				if(!txt.substring(i, k).equals("")){
					friends.add(txt.substring(i, k)+'/');
				}
				
				i=k+1;
			}
		}
		
		 param = new ArrayList<>();
		
		
		for(String s:friends){
			i=0;
			k=0;
			for(int j = 0 ; j <s.length();j++){
				if(s.charAt(j)=='/'){
					k=j;
					if(!s.substring(i, k).equals("")){
						param.add(s.substring(i, k));
					}
					
					i=k+1;
				}
			}
			
		}
		upF=true;
		updateFriends();
		
	}
	public void updateFriendsOnline(String txt){
		for(int i = 0 ; i < param.size();i++){
			if(param.get(i).equals(txt)){
				param.set(i+1, "online");
			}
		}
		updateFriends();
		
	}
	public void updateFriendsOffline(String txt){
		for(int i = 0 ; i < param.size();i++){
			if(param.get(i).equals(txt)){
				param.set(i+1, "offline");
			}
		}
		updateFriends();
		
	}
	private void updateFriends(){
		clearF_panel();
		temp=0;
		for(int z=0;z<param.size();z++ ){
			
			if(z%2==0){
				if(!param.get(z).equals(client.login)){
					String sub =param.get(z); 
					JLabel label = new JLabel(sub);
					label.setSize(100,40);
					label.setLocation(20,temp*50);
					label.setFont(new Font("Verdana",Font.ITALIC,15));
					label.setForeground(COMMON);
					label.addMouseListener(new MouseListener() {
						
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
							label.setForeground(COMMON);
						}
						
						@Override
						public void mouseEntered(MouseEvent e) {
							// TODO Auto-generated method stub
							label.setForeground(Color.MAGENTA);
						}
						
						@Override
						public void mouseClicked(MouseEvent e) {
							// TODO Auto-generated method stub
							connectionWith(label.getText());
						}
					});
					addToF_panel(label);
					String sub1 =param.get(z+1); 
					JLabel label1 = new JLabel(sub1);
					label1.setSize(100,20);
					label1.setLocation(20,temp*50+20);
					label1.setFont(new Font("Verdana",Font.ITALIC,9));
					if(param.get(z+1).equals("online")){
						label1.setForeground(Color.GREEN);
					}else{
						label1.setForeground(Color.RED);
					}
					
					addToF_panel(label1);
					temp++;
				}
			}
		}
	}
	private void connectionWith(String login){
		if(!inChat) {
			client.send("connect:"+client.login+"/"+login);
			
		}
		
	}
	public void requestForMess(String txt){
		 JFrame ask = new JFrame();
		 ask.setAlwaysOnTop(true);
		 ask.setLocationRelativeTo(null);
		 ask.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		 ask.setSize(300,300);
		 ask.setResizable(false);
		 
		 
		 JPanel panel = new JPanel();
		 panel.setLayout(null);
		 frame.setVisible(false);
		 
		 
		 JLabel lab = new JLabel();
		 lab.setSize(200,100);
		 lab.setText(txt+" wants to start messeging");
		 lab.setLocation(ask.getWidth()/2-lab.getWidth()/2,20);
		 
		 
		 JLabel yes = new JLabel("Yes");
		 yes.setSize(100,50);
		 yes.setLocation(0,100);
		 yes.addMouseListener(new MouseListener() {
			
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
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				ask.dispose();
				frame.setVisible(true);
				client.send("request for mess is accepted:"+client.login+"/"+txt);
				
			}
		});
		 
		 JLabel no = new JLabel("No");
		 no.setSize(100,50);
		 no.setLocation(ask.getWidth()-no.getWidth(),100);
		 no.addMouseListener(new MouseListener() {
			
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
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				ask.dispose();
				frame.setVisible(true);
				client.send("request for mess is denied:"+client.login+"/"+txt);
			}
		});
		 panel.add(yes);
		 panel.add(no);
		 panel.add(lab);
		 
		 ask.add(panel);
		ask.setVisible(true);		
	}
	public void requestForMessDenied(String txt){
		JFrame natification = new JFrame();
		natification.setAlwaysOnTop(true);
		natification.setLocationRelativeTo(null);
		natification.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		natification.setSize(300,300);
		natification.setResizable(false);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		frame.setVisible(false);
		
		JLabel lab = new JLabel(txt+" denied your request for chatting");
		lab.setSize(250,50);
		lab.setLocation(natification.getWidth()/2-lab.getWidth()/2,10);
		
		JLabel gotIt = new JLabel("Got it");
		gotIt.setSize(100,50);
		gotIt.setLocation(natification.getWidth()/2-gotIt.getWidth()/2,natification.getHeight()-gotIt.getHeight()-50);
		gotIt.addMouseListener(new MouseListener() {
			
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
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				natification.dispose();
				frame.setVisible(true);
			}
		});
		
		panel.add(lab);
		panel.add(gotIt);
		natification.add(panel);
		natification.setVisible(true);
	}
	public void updateFriends(String login) {
		
		clearF_panel();
		temp=0;
		for(int z=0;z<param.size();z++ ){
			
			if(z%2==0){
				if(param.get(z).equals(login)){
					String sub =param.get(z); 
					JLabel label = new JLabel(sub);
					label.setSize(100,40);
					label.setLocation(20,temp*50);
					label.setFont(new Font("Verdana",Font.ITALIC,15));
					label.setForeground(COMMON);
					label.addMouseListener(new MouseListener() {
						
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
							label.setForeground(COMMON);
						}
						
						@Override
						public void mouseEntered(MouseEvent e) {
							// TODO Auto-generated method stub
							label.setForeground(Color.MAGENTA);
						}
						
						@Override
						public void mouseClicked(MouseEvent e) {
							// TODO Auto-generated method stub
							connectionWith(label.getText());
						}
					});
					addToF_panel(label);
					String sub1 =param.get(z+1); 
					JLabel label1 = new JLabel(sub1);
					label1.setSize(100,20);
					label1.setLocation(20,temp*50+20);
					label1.setFont(new Font("Verdana",Font.ITALIC,9));
					if(param.get(z+1).equals("online")){
						label1.setForeground(Color.GREEN);
					}else{
						label1.setForeground(Color.RED);
					}
					
					addToF_panel(label1);
					temp++;
				}
			}
		}
	}
}
