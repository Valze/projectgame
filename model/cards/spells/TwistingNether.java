package model.cards.spells;

import java.util.ArrayList;

import model.cards.*;
import model.cards.minions.Minion;

public class TwistingNether extends Spell implements AOESpell {
	
	public TwistingNether() {
		super("Twisting Nether", 8, Rarity.EPIC);
	}
	public void performAction(ArrayList<Minion> oppField , ArrayList<Minion> curField) {
		for(Minion i : oppField) {
			i.minionDeath();
		}
		for(Minion j : curField) {
			j.minionDeath();
		}
	}
}
