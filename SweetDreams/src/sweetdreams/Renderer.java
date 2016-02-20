package sweetdreams;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.event.*;

public class Renderer {

	private static final int WIDTH = 400, HEIGHT = 400, HALF_HEIGHT = 200;

	private final Canvas top, bottom;
	private final BufferStrategy topStrat, bottomStrat;

	public Renderer() {
		JFrame window = new JFrame("A fantastic game for all the family");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel container = new JPanel();
		container.setLayout(new BorderLayout());
		top = new Canvas();
		top.setPreferredSize(new Dimension(WIDTH, HALF_HEIGHT));
		container.add(top, BorderLayout.NORTH);
		bottom = new Canvas();
		bottom.setPreferredSize(new Dimension(WIDTH, HALF_HEIGHT));
		container.add(bottom, BorderLayout.SOUTH);
		window.setContentPane(container);
		window.setVisible(true);
		window.pack();
		window.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				System.exit(0);
			}
		});

		// canvas.setIgnoreRepaint(true);
		top.createBufferStrategy(2);
		topStrat = top.getBufferStrategy();
		bottom.createBufferStrategy(2);
		bottomStrat = bottom.getBufferStrategy();
	}

	public void update(Environment top, Environment bottom) {

		Graphics g = topStrat.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HALF_HEIGHT);
		top.draw(g, WIDTH, HEIGHT);
		bottom.draw(g, WIDTH, HALF_HEIGHT);
		g.dispose();
		topStrat.show();

		g = bottomStrat.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HALF_HEIGHT);
		top.draw(g, WIDTH, HEIGHT);
		bottom.draw(g, WIDTH, HALF_HEIGHT);
		g.dispose();
		bottomStrat.show();
		
	}
}
