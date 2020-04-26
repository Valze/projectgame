package model.cards.spells;

import model.cards.*;
import model.cards.minions.Minion;

abstract public class Spell extends Card {
	
	public Spell() {}
	
	public Spell(String name, int manaCost ,Rarity rarity) {
		super(name, manaCost, rarity);
	}
	public boolean canHit(Minion m) {
		if(m.isDivine()) {
			m.setDivine(false);
			return false;
		}
		else {
			return true;
		}
	}
	public String toString() {
		return super.toString() + "</html>";
	}

}
