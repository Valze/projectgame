package model.cards.spells;

import model.cards.*;

public class Pyroblast extends Spell implements HeroTargetSpell, MinionTargetSpell{

	public Pyroblast() {
		super("Pyroblast", 10, Rarity.EPIC);
	}
	
}
