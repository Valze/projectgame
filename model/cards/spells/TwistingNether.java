package model.cards.spells;

import java.util.ArrayList;

import model.cards.*;
import model.cards.minions.Minion;

public class TwistingNether extends Spell implements AOESpell {
	
	public TwistingNether() {
		super("Twisting Nether", 8, Rarity.EPIC);
	}
	public void performAction(ArrayList<Minion> oppField , ArrayList<Minion> curField) {
		ArrayList<Minion> Dead = new ArrayList<Minion>();
		for(int i = 0; i<oppField.size()+curField.size();i++) {
			if(i<oppField.size()) {
				Dead.add(oppField.get(i));
			}
			if(i<curField.size()) {
				Dead.add(curField.get(i));
			}
		}
		//curField.clear();
		//oppField.clear();
		for(Minion m: Dead) {
			m.minionDeath();
		}
	}
}
