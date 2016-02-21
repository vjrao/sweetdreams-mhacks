package sweetdreams;

import entities.PCharacter;

public class PlayerEnvironment extends Environment {

	private static final double MAX_SPEED = 50, MIN_SPEED = -50;
	private static final double KEYBOARD_IMPULSE = 5;

	private final PCharacter player;

	public PlayerEnvironment() {
		player = new PCharacter(0, 0.05);
		addElement(player);
	}

	/**
	 * up, left, down, right, w, a, s, d
	 */
	private volatile boolean[] keys = new boolean[9];

	public void setKey(int index, boolean val) {
		keys[index] = val;
	}

	// here do special handling of keys in case some are dropped
	public String update(long tdelta,String rules) {
		player.update(tdelta);
		if ((keys[0] || keys[4])&&player.onsurface){
			player.v.y = -60;
			player.onsurface=false;
		}
		else if ((keys[1] || keys[5])&&player.onsurface)
			player.v.x = Math.max(MIN_SPEED,player.v.x-KEYBOARD_IMPULSE);
		else if ((keys[2] || keys[6])&&player.onsurface){
			player.v.y =60;
			player.onsurface=false;
		}
		else if ((keys[3] || keys[7])&&player.onsurface)
			player.v.x = Math.min(MAX_SPEED,player.v.x+KEYBOARD_IMPULSE);
		if (keys[8]&&!rules.contains("g")) {
			rules+="g";
			System.out.println("YES");
		}
		return rules;
	}
}
