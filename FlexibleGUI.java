import javax.swing.*;
/**
 * @(#)FlexibleGUI.java
 *
 *
 * @author 
 * @version 1.00 2014/11/7
 */

public class FlexibleGUI extends JFrame{
     private JTextField myText;
     private JButton btn; 
     private Picture pic; 
    /**
     * Creates a new instance of <code>FlexibleGUI</code>.
     */
    public FlexibleGUI() {
    	super("My Simple GUI");
    	
    	setLayout(null);
    	
  		myText = new JTextField("This is to enter text", 50);
  		btn = new JButton("My button");
  		pic = new Picture(new ImageIcon ("minion.png"));
  		
  		btn.setBounds(10,10,100,25);
  		add(btn);
  		
  		pic.setBounds(10,40,100,100);
  		add(pic);
  		
  		myText.setBounds(10,150,200,25);
  		add(myText);
  		
  		setSize(400,400);
  		setVisible(true);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new FlexibleGUI();
    }
}
