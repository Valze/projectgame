package model.cards.spells;

import java.util.ArrayList;

import model.cards.*;
import model.cards.minions.Minion;

public class HolyNova extends Spell implements AOESpell {

	public HolyNova() {
		super("Holy Nova", 5, Rarity.BASIC);
	}
	
	public void performAction(ArrayList<Minion> oppField , ArrayList<Minion> curField){
		ArrayList<Integer> Dead = new ArrayList<Integer>();
		for(Minion i : oppField) {
			if(i.isDivine()==true) {
				i.setDivine(false);
			}
			else {
			i.setCurrentHP(i.getCurrentHP()-4);
			if(i.getCurrentHP()<=0) {
				Dead.add(oppField.indexOf(i));
			}
			for(Minion j: curField) {
				j.setCurrentHP(j.getCurrentHP()+2);
			}
		}
	}
		for(Integer i : Dead) {
			oppField.get(i).minionDeath();
			
		}
	}
}