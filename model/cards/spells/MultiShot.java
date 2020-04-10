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
		case 1: if(canHit(oppField.get(0)))	{	
					oppField.get(0).setCurrentHP(oppField.get(0).getCurrentHP() - 3);
					if(oppField.get(0).getCurrentHP() <= 0) oppField.remove(0).minionDeath();
				}
				break;
		default:	int i = (int)(Math.random()*oppField.size());
		
					if(canHit(oppField.get(i))) {
						oppField.get(i).setCurrentHP(oppField.get(i).getCurrentHP() - 3);
					
					if(oppField.size() == 1) {
						if(canHit(oppField.get(0))) {
							oppField.get(0).setCurrentHP(oppField.get(0).getCurrentHP() - 3);
					}
					
					else {
						int j;
						do { j = (int)(Math.random()*oppField.size()); }
						while(i == j);					
						if(canHit(oppField.get(j))) {
							oppField.get(j).setCurrentHP(oppField.get(j).getCurrentHP() - 3);
					}
		}
	}
}
		}
	}
}
