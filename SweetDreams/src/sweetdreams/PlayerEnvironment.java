package sweetdreams;

import entities.Character;

public class PlayerEnvironment extends Environment {

	private static final double KEYBOARD_IMPULSE = 0.5;

	private final Character player;

	public PlayerEnvironment() {
		player = new Character(0, 0.05);
		addElement(player);
	}

	/**
	 * up, left, down, right, w, a, s, d
	 */
	private volatile boolean[] keys = new boolean[8];

	public void setKey(int index, boolean val) {
		keys[index] = val;
	}

	// here do special handling of keys in case some are dropped
	public void update() {
		if (keys[0] || keys[4])
			player.a.y = -5;
		else if (keys[1] || keys[5])
			player.v.x -= KEYBOARD_IMPULSE;
		else if (keys[2] || keys[6])
			player.v.y += KEYBOARD_IMPULSE;
		else if (keys[3] || keys[7])
			player.v.x += KEYBOARD_IMPULSE;
	}
}