package sweetdreams;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.event.*;
import java.awt.geom.AffineTransform;

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

	private static final int BORDER = 8;

	public void update(Environment top, Environment bottom) {

		int width = this.top.getWidth(), height = this.top.getHeight();

		AffineTransform scaled = AffineTransform.getScaleInstance(width / (double) WIDTH,
				height / (double) HALF_HEIGHT);

		Graphics2D g = (Graphics2D) topStrat.getDrawGraphics();
		AffineTransform init = g.getTransform();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		g.setTransform(scaled);
		top.draw(g, WIDTH, HALF_HEIGHT);
		g.setTransform(init);
		g.setColor(Color.WHITE);
		g.fillRect(0, height - BORDER / 2, width, BORDER / 2);
		g.dispose();
		topStrat.show();

		g = (Graphics2D) bottomStrat.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		g.setTransform(scaled);
		bottom.draw(g, WIDTH, HALF_HEIGHT);
		g.setTransform(init);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, BORDER / 2);
		g.dispose();
		bottomStrat.show();
	}
}
