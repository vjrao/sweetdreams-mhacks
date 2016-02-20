package sweetdreams;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Renderer {
	
	private static final int WIDTH = 400, HEIGHT = 400;
	
	private final Canvas canvas;
	private final BufferStrategy strat;

	public Renderer() {
		JFrame window = new JFrame("A fantastic game for all the family");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel container = new JPanel();
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		container.add(canvas);
		window.setContentPane(container);
		window.setVisible(true);
		window.pack();
		
//		canvas.setIgnoreRepaint(true);
		canvas.createBufferStrategy(2);
		strat = canvas.getBufferStrategy();
	}
	
	public void update(Object env) {
		
		Graphics g = strat.getDrawGraphics();
		
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		// for all objects in env, call obj.draw(g, WIDTH, HEIGHT)
		
		g.dispose();
		strat.show();
		
	}
}
