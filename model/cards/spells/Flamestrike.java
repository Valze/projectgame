package model.cards.spells;

import java.util.ArrayList;

import model.cards.*;
import model.cards.minions.Minion;

public class Flamestrike extends Spell implements AOESpell {

	public Flamestrike() {
		super("Flamestrike", 7, Rarity.BASIC);
	}
	public void performAction(ArrayList<Minion> oppField , ArrayList<Minion> curField){
		ArrayList<Minion> Dead = new ArrayList<Minion>();
		for(Minion i : oppField) {
			if(this.canHit(i)) {
				if(i.getCurrentHP()-4<=0) {
					Dead.add(i);
				}
				else {
					i.setCurrentHP(i.getCurrentHP()-4);
				}
			}
	}
		for(Minion m: Dead) {
			oppField.remove(m);
			m.setCurrentHP(m.getCurrentHP()-4);
		}
		
	}
}
