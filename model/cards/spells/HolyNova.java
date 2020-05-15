package model.cards.spells;

import java.util.ArrayList;

import model.cards.*;
import model.cards.minions.Minion;

public class HolyNova extends Spell implements AOESpell {

	public HolyNova() {
		super("Holy Nova", 5, Rarity.BASIC);
	}
	
	public void performAction(ArrayList<Minion> oppField , ArrayList<Minion> curField){
		ArrayList<Minion> Dead = new ArrayList<Minion>();
		for(Minion i : oppField) {
			if(this.canHit(i)) {
				if(i.getCurrentHP()-2<=0) {
					Dead.add(i);
				}
				else {
					i.setCurrentHP(i.getCurrentHP()-2);
				}
			}
		}
		
		for(Minion j: curField) {
			j.setCurrentHP(j.getCurrentHP()+2);
			if(j.getCurrentHP()>=j.getMaxHP()) {
				j.setCurrentHP(j.getMaxHP());
			}
		}
	
		for(Minion i : Dead) {
			i.setCurrentHP(i.getCurrentHP()-2);
		}
	}
}