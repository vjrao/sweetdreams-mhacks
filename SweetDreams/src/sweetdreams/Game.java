package sweetdreams;

import java.io.File;

import javax.sound.sampled.*;
import entities.*;
import physics.PhysicsEngine;

public class Game {

	public static void main(String[] args) {

		System.setProperty("sun.java2d.opengl", "true");
		System.setProperty("sun.awt.noerasebackground", "true");

		System.out.println(
				"Hello, world! We're making good progress on a 2D OpenGL clone designed to revolutionize the graphical computing market!");

		final VirusEnvironment top = new VirusEnvironment();
		final PlayerEnvironment bottom = new PlayerEnvironment();

		bottom.addElement(new Ground(1.0, 0, Renderer.WIDTH, Renderer.HALF_HEIGHT, 40));
		bottom.addElement(new Crate(90, 0, 0, 0));

		new Thread(new Runnable() {
			public void run() {
				long t1 = System.nanoTime();
				for (;;) {
					long tcurr = System.nanoTime();
					long tdelta = tcurr - t1;
					// Empirically calculated constant to speed up things a
					// little
					tdelta *= 4;
					t1 = tcurr;

					// Updates:
					// sprite move animation
					// ground generate/vanish calculation

					// update player character with key impulses
					// update viruses with artificial impulses
					// update player world with virus influence
					// update virus world with player influence

					PhysicsEngine.update(top, tdelta / 400);
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
				Renderer renderer = new Renderer(bottom);
				for (;;) {
					renderer.update(top, bottom);
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
					}
				}
			}
		}).start();
		new Thread(new Runnable() {

			@Override
			public void run() {

				try {
					AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("fallill.wav"));
					Clip clip = AudioSystem.getClip();
					clip.open(audioInputStream);
					clip.start();
				} catch (Exception ex) {
				}
			}
		}).start();

	}

}