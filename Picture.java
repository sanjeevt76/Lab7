import java.awt.Color;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * Creates a graphical component
 */

/**
 * @author p0067706
 *
 */
public class Picture extends JComponent{

	/**
	 * Class data
	 */
	private Color c;
	private int x, y, width, height;
	private ImageIcon img;
	private boolean useImage;
	
	/**
	 * Default constructor
	 */
	public Picture() {
		this.c = Color.RED;
		this.x = 0; 
		this.y = 0;
		this.width = 100;
		this.height = 50;
		this.img = null;
		this.useImage = false;
		repaint();
	}
	
	/**
	 * Constructor to paint in different location
	 */
	public Picture (int x, int y, int width, int height, Color c){
		this.c = c;
		this.x = x; 
		this.y = y;
		this.width = width;
		this.height = height;
		this.img = null;
		this.useImage = false;
		repaint();
	}
	
	/**
	 * Constructor to paint a picture
	 */

	public Picture (int x, int y, ImageIcon img){
		this.x = x; 
		this.y = y;
		this.width = img.getIconWidth();
		this.height = img.getIconHeight();
		this.img = img;
		this.useImage = true;
		repaint();
	}
	/**
	 * This component's paint method
	 */
	public void paint (Graphics g){
		if (!useImage){
			g.setColor(this.c);
			g.drawRect(x, y, width, height);
		}
		else if (useImage){
			img.paintIcon(this, g, x, y);
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		JFrame f = new JFrame();
		
		Picture p0 = new Picture();
		f.setSize(400,300);
		f.add(p0);
		f.setVisible(true);
		
		JOptionPane.showMessageDialog(null, "wait");
		
		Picture p1 = new Picture (50, 50, 50, 100, Color.BLUE);
		f.remove(p0);
		f.add(p1);
		f.setVisible(true);
		
		JOptionPane.showMessageDialog(null, "wait");
		
		Picture p2 = new Picture (50, 50, new ImageIcon("minion.png"));
		f.remove(p1);
		f.add(p2);
		f.setVisible(true);

	}

}
