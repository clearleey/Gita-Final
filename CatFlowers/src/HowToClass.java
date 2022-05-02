import java.awt.image.ImageObserver;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;

public class HowToClass extends JFrame implements ActionListener {
	
	JButton btnBackToStart = new JButton("Back to Start");

	public static void main(String[] args) {
        // declare a frame for form
        HowToClass frame = new HowToClass();
        frame.setSize(600, 600);
        frame.setVisible(true);
    }

	public HowToClass(){
        super("˚ ༘♡ flower CATastrophe ⋆｡˚ ❀");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // set layout manager
        setLayout(new FlowLayout());
		
        add(btnBackToStart);
        btnBackToStart.addActionListener(this);

    } 
	
	public void paint(Graphics g){
		
		super.paint(g);
		
		Toolkit t=Toolkit.getDefaultToolkit();
		Image i = t.getImage("howTo.PNG");
		g.drawImage(i, 120, 100, (ImageObserver) this);
		
	}
	
	public void Update(Graphics gr){
		
		//call the paint method
		paint(gr);
	}

	public void actionPerformed(ActionEvent event) {
		Object objSource = event.getSource();
		
		if(objSource == btnBackToStart){
        	
        	new CatFlowers();
        	
       }
	}
}
