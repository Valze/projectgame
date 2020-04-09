package model.cards.spells;

import java.util.ArrayList;

import model.cards.*;
import model.cards.minions.Minion;

public class MultiShot extends Spell implements AOESpell {
	
	public MultiShot(){
		super("Multi-Shot", 4, Rarity.BASIC);
	}
	
	public void performAction(ArrayList<Minion> oppField , ArrayList<Minion> curField)  {
		int s = oppField.size();
		
		switch(s) {
		case 0: return;
		case 1: if(oppField.get(0).isDivine()) oppField.get(0).setDivine(false);
				else {
					oppField.get(0).setCurrentHP(oppField.get(0).getCurrentHP() - 3);
					if(oppField.get(0).getCurrentHP() <= 0) oppField.remove(0).minionDeath();
				}
				break;
		case 2: if(oppField.get(0).isDivine()) oppField.get(0).setDivine(false);
				else {
					oppField.get(0).setCurrentHP(oppField.get(0).getCurrentHP() - 3);
					if(oppField.get(0).getCurrentHP() <= 0) oppField.remove(0).minionDeath();
				}
				if(oppField.get(1).isDivine()) oppField.get(1).setDivine(false);
				else {
					oppField.get(1).setCurrentHP(oppField.get(1).getCurrentHP() - 3);
					if(oppField.get(1).getCurrentHP() <= 0) oppField.remove(1).minionDeath();
				}
				break;
		default:	if(oppField.get(oppField.size()/2).isDivine()) oppField.get(oppField.size()/2).setDivine(false);
					else {
						oppField.get(oppField.size()/2).setCurrentHP(oppField.get(oppField.size()/2).getCurrentHP() - 3);
						if(oppField.get(oppField.size()/2).getCurrentHP() <= 0) oppField.remove(oppField.size()/2).minionDeath();
					}
					if(oppField.get((oppField.size()/2)+1).isDivine()) oppField.get((oppField.size()/2)+1).setDivine(false);
					else {
						oppField.get((oppField.size()/2)+1).setCurrentHP(oppField.get((oppField.size()/2)+1).getCurrentHP() - 3);
						if(oppField.get((oppField.size()/2)+1).getCurrentHP() <= 0) oppField.remove((oppField.size()/2)+1).minionDeath();
					}
		}
	}
}
