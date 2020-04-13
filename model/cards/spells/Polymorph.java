package model.cards.spells;

import model.cards.*;
import model.cards.minions.Minion;

public class Polymorph extends Spell implements MinionTargetSpell {
	
	public Polymorph() {
		super("Polymorph", 4, Rarity.BASIC);
	}
	public void performAction(Minion m) {
		m.setName("Sheep");
		m.setCurrentHP(1);
		m.setMaxHP(1);
		m.setAttack(1);
		m.setManaCost(1);
		m.setDivine(false);
		m.setTaunt(false);
		m.setSleeping(true);
	}
	
}
