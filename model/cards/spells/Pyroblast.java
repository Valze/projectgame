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
		if(h.getCurrentHP()<=0) {
			h.heroDeath();
		}
	}
	public void performAction(Minion m) {
		if(m.isDivine() == true) {
			m.setDivine(false);
		}
		else {
		m.setCurrentHP(m.getCurrentHP()-10);
		if(m.getCurrentHP()<=0) {
			m.minionDeath();
		}
		}
	}
}
