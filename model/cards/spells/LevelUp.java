package model.cards.spells;

import java.util.ArrayList;

import model.cards.*;
import model.cards.minions.Minion;

public class LevelUp extends Spell implements FieldSpell {
	
	public LevelUp() {
		super("Level Up!", 6, Rarity.EPIC);
	}
	public void performAction(ArrayList<Minion>field) {
		for(Minion i : field) {
			if(i.getName()=="Silver Hand Recruit") {
				i.setAttack(i.getAttack()+1);
				i.setCurrentHP(i.getCurrentHP()+1);
				i.setMaxHP(i.getMaxHP()+1);
			}
		}
	}

}
