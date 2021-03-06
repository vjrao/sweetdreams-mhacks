package sweetdreams;

import entities.*;
import java.util.ArrayList;

public class VirusEnvironment extends Environment {

	public VirusEnvironment() {
		for (int i = 0; i < 10; i++)
			addElement(new Virus(Math.random() / 2 + 0.5, 1));
	}

	/**
	 * Viruses plan out their moves Note: max velocity before not stopping is 50
	 */
	public void update(long tdelta) {
		ArrayList<Virus> virii = new ArrayList<Virus>();
		ArrayList<CommandBlock> cblocks = new ArrayList<CommandBlock>();
		for (Entity e : elements())
			if (e instanceof Virus)
				virii.add((Virus) e);
			else if (e instanceof CommandBlock)
				cblocks.add((CommandBlock) e);

		for (Virus v : virii) {

			// Establish a target if necessary
			if (v.target == null || v.target.attackers == 0xf) {
				CommandBlock closest = null;
				double mindist = Integer.MAX_VALUE;
				int targetSide = -1;
				for (CommandBlock cb : cblocks) {
					if (cb.attackers == 0xf)
						return;
					double dist = v.pos.x - cb.pos.x;
					if ((closest == null || Math.abs(dist) < Math.abs(mindist))) {
						closest = cb;
						mindist = dist;
						if (dist < 0)
							if ((closest.attackers & 8) == 0)
								targetSide = 8;
							else if ((closest.attackers & 4) == 0)
								targetSide = 4;
							else if ((closest.attackers & 1) == 0)
								targetSide = 1;
							else
								targetSide = 2;
						else if ((closest.attackers & 4) == 0)
							targetSide = 4;
						else if ((closest.attackers & 2) == 0)
							targetSide = 2;
						else if ((closest.attackers & 1) == 0)
							targetSide = 1;
						else
							targetSide = 8;
					}
				}
				v.target = closest;
				v.targetSide = targetSide;
			}

			// ATTACK THE TARGET
			if (v.target == null) {
				v.accelerate(1);
				v.jump();
			} else {
				double dist = v.pos.x - v.target.pos.x;
				if (dist < 0 && v.targetSide == 2)
					v.accelerate(1);
				else if (dist > 0 && v.targetSide == 8)
					v.accelerate(-1);
				else if (Math.abs(dist) < 50)
					v.jump();
			}
		}
	}
}
