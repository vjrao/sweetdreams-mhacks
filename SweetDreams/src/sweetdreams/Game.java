package sweetdreams;

import entities.*;
import physics.PhysicsEngine;

public class Game {

	public static void main(String[] args) {

		System.setProperty("sun.java2d.opengl", "true");
		System.out.println("Hello, world! Trying to actually run a game here, if you would believe it!");

		final Environment top = new Environment(), bottom = new Environment();
		top.addElement(new Crate(100, 105, 10, 0));
		top.addElement(new Crate(100, 400, 0, -100));
		top.addElement(new Virus(0.2, 2, 0, 90, 15, 0));
		top.addElement(new Virus(1.0, 2, 400, 120, -100, 0));

		new Thread(new Runnable() {
			public void run() {
				long t1 = System.nanoTime();
				for (;;) {
					long tcurr = System.nanoTime();
					long tdelta = tcurr - t1;
					t1 = tcurr;
					PhysicsEngine.update(top, tdelta);
					PhysicsEngine.update(bottom, tdelta);
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
					}
				}
			}
		}).start();
		new Thread(new Runnable() {
			public void run() {
				Renderer renderer = new Renderer();
				for (;;) {
					renderer.update(top, bottom);
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
					}
				}
			}
		}).start();

	}

}