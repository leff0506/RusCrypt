package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;

import server.Server;

public class GUI {
	private JFrame frame;
	private JPanel panel;
	Server server;
	public GUI(){
	
		frame = new JFrame();
		frame.setSize(500,500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		JPanel panel = new JPanel();
		panel.setLayout(null);
		JTextArea jta = new JTextArea();
		 jta.setSize(440,440);
		
		
		jta.setText(" ");
		jta.setText("");

		JScrollPane scroll = new JScrollPane(jta);
//		scroll.setLayout(null);
		jta.setLineWrap(true);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setBounds(jta.getBounds());
		
		scroll.setWheelScrollingEnabled(true);
		panel.add(scroll);
		/*
		JMenuBar menuBar = new JMenuBar();
        

	    JMenu programme = new JMenu("Programme");
	   
	    
	    JMenuItem exit = new JMenuItem("Exit");

	    
	    programme.add(exit);
	    menuBar.add(programme);

	    frame.setJMenuBar(menuBar);
	 
	    */
		 frame.add(panel);
		
		 jta.setLocation(panel.getWidth()/2-jta.getWidth()/2, panel.getHeight()/2-jta.getHeight()/2);
		
		 frame.setVisible(true);
	   server = new Server(jta);
		
		/*exit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					server.getServer().close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				System.exit(0);
				
			}
		});*/
	  
	}
}
