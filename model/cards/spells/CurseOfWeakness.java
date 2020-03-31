package model.cards.spells;

import java.util.ArrayList;

import model.cards.*;
import model.cards.minions.Minion;

public class CurseOfWeakness extends Spell implements AOESpell {

	public CurseOfWeakness() {
		super("Curse of Weakness", 2, Rarity.RARE);
	}
	public void performAction(ArrayList<Minion> oppField , ArrayList<Minion> curField) {
		for(Minion i : oppField) {
			i.setAttack(i.getAttack() -2);
		}
	}
}
