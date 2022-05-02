
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.event.KeyListener;
import java.util.Collections;
import java.util.Observer;

import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

public class CatFlowers extends JFrame implements ActionListener{

    JButton btnHowTo = new JButton("How To");
    JButton btnStart = new JButton("Start");

    // main is the first method to run - method means function
    public static void main(String[] args) {
        // declare a frame for form
        CatFlowers frame = new CatFlowers();
        frame.setSize(600, 600);
        frame.setVisible(true);
        
    }
    // declare the constructor for the project
    // the constructor
    public CatFlowers(){
        super("˚ ༘♡ flower CATastrophe ⋆｡˚ ❀");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
            // set layout manager
            //
        setLayout(new FlowLayout());
       // btnHowTo.setBounds(250,300,20,10);
        add(btnHowTo);
        add(btnStart);
       // btnHowTo.setSize(new DimensionUIResource(200, 200));
        //btnHowTo.setSize(200, 200);
       // btnStart.setAlignmentY(500);
       
        btnHowTo.addActionListener(this);
        btnStart.addActionListener(this);
 

    } 
    
    public void paint(Graphics g){
		
 		super.paint(g);
 		
 		Toolkit t=Toolkit.getDefaultToolkit();
 		Image i = t.getImage("title.PNG");
 		g.drawImage(i, 25, 100, (ImageObserver) this);
 		
 	}
 	
 	public void Update(Graphics gr){
 		
 		//call the paint method
 		paint(gr);
 	}

    public void actionPerformed(ActionEvent event){
        Object objSource = event.getSource();
        
        if(objSource == btnHowTo){
        	
        	new HowToClass();
        	
       }
        
        if(objSource == btnStart){
        	
        	new Game();

        }     

    }

}


