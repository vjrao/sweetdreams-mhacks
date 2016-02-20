package sweetdreams;

import entities.Character;

public class PlayerEnvironment extends Environment {

	private static final double MAX_SPEED = 100, MIN_SPEED = 0;
	private static final double KEYBOARD_IMPULSE = 5;

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
	public void update(long tdelta) {
		player.update(tdelta);
		if (keys[0] || keys[4])
			player.v.y = -20;
		else if (keys[1] || keys[5])
			player.v.x = MIN_SPEED;
		else if (keys[2] || keys[6])
			player.v.y += KEYBOARD_IMPULSE;
		else if (keys[3] || keys[7])
			player.v.x = MAX_SPEED;
	}
}
