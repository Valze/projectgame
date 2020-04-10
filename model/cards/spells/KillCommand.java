package model.cards.spells;

import exceptions.InvalidTargetException;
import model.cards.*;
import model.cards.minions.Minion;
import model.heroes.Hero;

public class KillCommand extends Spell implements HeroTargetSpell, MinionTargetSpell {

	public KillCommand() {
		super("Kill Command", 3, Rarity.COMMON);
	}
	public void performAction(Hero h) {
		h.setCurrentHP(h.getCurrentHP()-3);
	}
	
	public void performAction(Minion m) throws InvalidTargetException {
		if(canHit(m))
			m.setCurrentHP(m.getCurrentHP()-5);	
	}
	
}
