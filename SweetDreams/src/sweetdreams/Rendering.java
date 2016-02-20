package sweetdreams;

import javax.swing.*;
import java.awt.*;

public class Rendering extends JPanel {

	private boolean isFullscreen;
	
	private final JFrame windowed, fullscreen;
	
	public Rendering() {
		isFullscreen = false;
		windowed = new JFrame("A fantastic game for all the family");
		fullscreen = null;
	}
	
	public void paintComponent(Graphics g2) {
		
		Graphics2D g = (Graphics2D) g2;
		
		
		
	}
	
}
