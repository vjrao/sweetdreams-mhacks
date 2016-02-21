package sweetdreams;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.event.*;
import java.awt.geom.AffineTransform;

public class Renderer {

	public static final int WIDTH = 1600, HEIGHT = 700, HALF_HEIGHT = HEIGHT / 2;

	private final Canvas top, bottom;
	private final BufferStrategy topStrat, bottomStrat;

	private final PlayerEnvironment keyCallbacks;

	public Renderer(PlayerEnvironment pe) {
		keyCallbacks = pe;
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
		window.add(container);
		window.setVisible(true);
		window.pack();
		window.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				handleKeys(e.getKeyCode(), true);
			}

			public void keyReleased(KeyEvent e) {
				handleKeys(e.getKeyCode(), false);
			}

			public void handleKeys(int keycode, boolean state) {
				switch (keycode) {
				case KeyEvent.VK_ESCAPE:
					System.exit(0);
					break;
				case KeyEvent.VK_UP:
					keyCallbacks.setKey(0, state);
					break;
				case KeyEvent.VK_LEFT:
					keyCallbacks.setKey(1, state);
					break;
				case KeyEvent.VK_DOWN:
					keyCallbacks.setKey(2, state);
					break;
				case KeyEvent.VK_RIGHT:
					keyCallbacks.setKey(3, state);
					break;
				case KeyEvent.VK_W:
					keyCallbacks.setKey(4, state);
					break;
				case KeyEvent.VK_A:
					keyCallbacks.setKey(5, state);
					break;
				case KeyEvent.VK_S:
					keyCallbacks.setKey(6, state);
					break;
				case KeyEvent.VK_D:
					keyCallbacks.setKey(7, state);
					break;
				}

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
		// AffineTransform init = g.getTransform();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		// g.setTransform(scaled);
		top.draw(g, WIDTH, HALF_HEIGHT);
		// g.setTransform(init);
		g.setColor(Color.WHITE);
		g.fillRect(0, height - BORDER / 2, width, BORDER / 2);
		g.dispose();
		topStrat.show();

		g = (Graphics2D) bottomStrat.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, width, height);
		// g.setTransform(scaled);
		bottom.draw(g, WIDTH, HALF_HEIGHT);
		// g.setTransform(init);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, BORDER / 2);
		g.dispose();
		bottomStrat.show();
	}
}
