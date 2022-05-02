import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.event.KeyListener;
import java.util.Collections;
import javax.swing.JFrame;

/*
* to do
* timer --> to keep track of time
* orders using arrays
* make sure character cant leave screen
* add flowers above each flower
*/

class Game extends JFrame implements ActionListener, KeyListener {
   // VARIABLES FOR MOVING THE CHARACTER
   int catX = 20;
   int catY = 20;

   // COORDINATES FROM FLOWER POTS
       // lilacs
   String typeOfFlower = "";
   int lilacX = -30;
   int lilacY = 270;
   int tulipX = -30;
   int tulipY = 320;

   int flowerX = catX + 20;
   int flowerY = catY + 20;
   boolean keyPress = false;
   int direction = 0; // 0 - up, 1- right, 2- down, 3-left
   int xySpeed = 50;

   // VARIABLES FOR ORDERS
   // which flower
   int flowerCoordX = 450;
   int flowerCoordY = 450;
   boolean orderComplete = false;

   // which customer
   int customerX = 500;
   int customerY = 500;


   int numOfFinishedOrders = 0;
   boolean orderNeeded = true;
   boolean orderFinished = false;

   int level = 1;

    int lives = 5;
    JButton btnStartGame = new JButton("Start");
    JButton btnPauseGame = new JButton("Game");
    JTextArea txaTimer = new JTextArea("\n",2,5);	
	Timer levelTimer = new Timer (100, this);
    String timerString;
    int timer = 0;
   boolean pausePlay = true; // true - play, false - pause
   

   // LAYOUT OF WINDOW
   public Game() {
       // ADDING BACKGROUND IMAGE
       setTitle("˚ ༘♡ flower CATastrophe ⋆｡˚ ❀");
       setSize(900, 900);
       setLocationRelativeTo(null);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       setVisible(true);
       addKeyListener(this);

       setLayout(new BorderLayout());
       setContentPane(new JLabel(new ImageIcon("background.JPG")));
       setLayout(new FlowLayout());

       setSize(599, 599);
       setSize(600, 600);
   }

   // MOVING CHARACTER
   public void actionPerformed(ActionEvent event) {
       Object objSource = event.getSource();

       //startTimer();
       if (pausePlay == true) {
           timer += 1;
           System.out.println(timer);
       }
       if(objSource == btnStartGame){
			
        startTheTimer();
        timerString = levelTimer.toString();
        txaTimer.setText(timerString);
         
    }
    
    else if (lives == 0)
    {	
        stopTheTimer();
        //output YOU LOSE
    }

    if(objSource == btnPauseGame){
        
        stopTheTimer();
         
    }


   }
   // KEY PRESSES
   public void keyPressed(KeyEvent e) {
       requestFocus();
       int key = e.getKeyCode();
       if (key == 37) {
           //System.out.println("left");
           catX -= xySpeed;
           flowerX -= xySpeed;
           direction = 3;
           keyPress = true;

       } else if (key == 39) {
           //System.out.println("right");
           catX += xySpeed;
           flowerX += xySpeed;
           direction = 1;
           keyPress = true;

       } else if (key == 38) {
           //System.out.println("down");
           catY -= xySpeed;
           flowerY -= xySpeed;
           direction = 2;
           keyPress = true;

       } else if (key == 40) {
           //System.out.println("up");
           catY += xySpeed;
           flowerY += xySpeed;
           direction = 0;
           keyPress = true;
       }

       // key p --> for pause and play
       if (key == 80) {
           if (pausePlay) {
               //pauseTimer();
           } else if (!pausePlay) {
               //startTimer();
           }
       }
       repaint();
   }

   public void keyReleased(KeyEvent e) {
       int key = e.getKeyCode();
       if (key == 37 || key == 38 || key == 39 || key == 40) {
           keyPress = false;
           repaint();
       }
   }

   public void keyTyped(KeyEvent e) {
   }

   public void paint(Graphics g) {
       super.paint(g);

       // MOVING THE CAT
       Toolkit t = Toolkit.getDefaultToolkit();
       if (keyPress == true) {
           if (direction == 0) {
               Image i = t.getImage("catMove1D.PNG");
               g.drawImage(i, catX, catY, this);
           } else if (direction == 1) {
               Image i = t.getImage("catMove1R.PNG");
               g.drawImage(i, catX, catY, this);
           } else if (direction == 2) {
               Image i = t.getImage("catMove1U.PNG");
               g.drawImage(i, catX, catY, this);
           } else if (direction == 3) {
               Image i = t.getImage("catMove1L.PNG");
               g.drawImage(i, catX, catY, this);
           }
       }
       else {
           if (direction == 0) {
               Image i = t.getImage("catMove2D.PNG");
               g.drawImage(i, catX, catY, this);
           } else if (direction == 1) {
               Image i = t.getImage("catMove2R.PNG");
               g.drawImage(i, catX, catY, this);
           } else if (direction == 2) {
               Image i = t.getImage("catMove2U.PNG");
               g.drawImage(i, catX, catY, this);
           } else if (direction == 3) {
               Image i = t.getImage("catMove2L.PNG");
               g.drawImage(i, catX, catY, this);
           }
       }

       System.out.println(catX);
       System.out.println(catY);

       // get pick up flowers
       if (Math.abs(catX-lilacX) < 20 && Math.abs(catY-lilacY) < 20){
           typeOfFlower = "lilac";
       }
       else if(Math.abs(catX-tulipX) < 20 && Math.abs(catY-tulipY) < 20){
           typeOfFlower = "tulip";
       }

       if(typeOfFlower == "lilac"){
           Image i = t.getImage("miniLilacs.PNG");
           g.drawImage(i, flowerX, flowerY, this);
       }
       else if(typeOfFlower == "tulip"){
           Image i = t.getImage("miniTulips.PNG");
           g.drawImage(i, flowerX, flowerY, this);
       }



       // GIVING THE USER AN ORDER
       if (orderNeeded == true){
           int flower = orders();

           if (flower == 0) {
               System.out.println(flower);
               Image i = t.getImage("lilacs.PNG");
               g.drawImage(i, flowerCoordX, flowerCoordY, this);
           }
           else if (flower == 1) {
               System.out.println(flower);
               Image i = t.getImage("tulips.PNG");
               g.drawImage(i, flowerCoordX, flowerCoordY, this);
           }
           else if (flower == 2) {
               System.out.println(flower);
               Image i = t.getImage("daisies.PNG");
               g.drawImage(i, flowerCoordX, flowerCoordY, this);
           }
           else if (flower == 3) {
               System.out.println(flower);
               Image i = t.getImage("peonies.PNG");
               g.drawImage(i, flowerCoordX, flowerCoordY, this);
           }
           else if (flower == 4) {
               System.out.println(flower);
               Image i = t.getImage("roses.PNG");
               g.drawImage(i, flowerCoordX, flowerCoordY, this);
           }
           else if (flower == 5) {
               System.out.println(flower);
               Image i = t.getImage("sunflowers.PNG");
               g.drawImage(i, flowerCoordX, flowerCoordY, this);
           }
           else if (flower == 6) {
               System.out.println(flower);
               Image i = t.getImage("hydrangeas.PNG");
               g.drawImage(i, flowerCoordX, flowerCoordY, this);
           }
           else if (flower == 7) {
               System.out.println(flower);
               Image i = t.getImage("lilies.PNG");
               g.drawImage(i, flowerCoordX, flowerCoordY, this);
           }
           // }

           String cat = customerOrder();

           if(cat == "chai"){
               Image i = t.getImage("chai.png");
               g.drawImage(i, customerX, customerY, this);
           }
           else if (cat == "molly"){
               Image i = t.getImage("molly.png");
               g.drawImage(i, customerX, customerY, this);
           }
           else if (cat == "louie"){
               Image i = t.getImage("louie.png");
               g.drawImage(i, customerX, customerY, this);
           }
           else if (cat == "chanel"){
               Image i = t.getImage("chanel.png");
               g.drawImage(i, customerX, customerY, this);
           }

           orderNeeded = false;
       }

   }

public void startTheTimer(){
		
    levelTimer.start();
}

public void stopTheTimer(){
    
    levelTimer.stop();
}
   /*
   public void startTimer (){
       pausePlay = true;
       levelTimer.start();
   }

   public void pauseTimer(){
       pausePlay = false;
       levelTimer.stop();
   }
   */


   public int orders() {
       int[] orders = {0,1, 2, 3, 4, 5, 6, 7};
       // "lilacs", "tulips", "daisies", "peonies", "roses", "sunflowers", "hydrangeas" , "lilies"
       int randomNum = (int) Math.floor(Math.random() * (9 - 1));
       int flower = orders[randomNum];

       return flower;
   }

   public String customerOrder() {
       String[] customers = {"chai", "molly", "louie", "chanel"};
       int ran = (int) Math.floor(Math.random() * (5-1));
       String customer = customers[ran];

       return customer;
   }


}

