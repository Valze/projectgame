package model.cards.spells;

import exceptions.InvalidTargetException;
import model.cards.*;
import model.cards.minions.Minion;

public class ShadowWordDeath extends Spell implements MinionTargetSpell {
	
	public ShadowWordDeath() {
		super("Shadow Word: Death", 3, Rarity.BASIC);
	}
	public void performAction(Minion m) throws InvalidTargetException {
		if(m.getAttack()>=5) {
			m.minionDeath();
		}
		else {
			throw new InvalidTargetException("Minion has less than 5 attack");
		}
	}
}
