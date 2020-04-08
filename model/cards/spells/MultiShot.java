package model.cards.spells;

import java.util.ArrayList;

import model.cards.*;
import model.cards.minions.Minion;

public class MultiShot extends Spell implements AOESpell {
	
	public MultiShot(){
		super("Multi-Shot", 4, Rarity.BASIC);
	}
	
	public void performAction(ArrayList<Minion> oppField , ArrayList<Minion> curField) {
		if(!oppField.isEmpty()) {
			
			int i = (int) (Math.random()*oppField.size());
			Minion temp = oppField.get(i);
			temp.setCurrentHP(temp.getCurrentHP()-3);
			oppField.add(temp);
			
			if(oppField.size() != 1) {
				int j = (int) (Math.random()*(oppField.size()-1));
				Minion t2 = oppField.get(i);
				t2.setCurrentHP(t2.getCurrentHP()-3);
				oppField.add(t2);
			}
		}
	}
}
