package model.cards.spells;

import model.cards.*;
import model.cards.minions.Minion;
import model.heroes.Hero;

public class Pyroblast extends Spell implements HeroTargetSpell, MinionTargetSpell{

	public Pyroblast() {
		super("Pyroblast", 10, Rarity.EPIC);
	}
	public void performAction(Hero h) {
		h.setCurrentHP(h.getCurrentHP()-10);
	}
	
	public void performAction(Minion m) {
		if(canHit(m))
			m.setCurrentHP(m.getCurrentHP()-10);
	}
	
}
