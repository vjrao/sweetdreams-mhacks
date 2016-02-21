package sweetdreams;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
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
		// top.addElement(new Crate(100, 105, 10, 0));
		// top.addElement(new Crate(100, 400, 0, -100));
		// top.addElement(new Virus(0.2, 2, 0, 90, 15, 0));
		// top.addElement(new Virus(1.0, 2, 400, 120, -100, 0));

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