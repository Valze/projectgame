package model.cards.spells;

import java.util.ArrayList;

import model.cards.*;
import model.cards.minions.Minion;

public class Flamestrike extends Spell implements AOESpell {

	public Flamestrike() {
		super("Flamestrike", 7, Rarity.BASIC);
	}
	public void performAction(ArrayList<Minion> oppField , ArrayList<Minion> curField){
		ArrayList<Integer> Dead = new ArrayList<Integer>();
		for(Minion i : oppField) {
			i.setCurrentHP(i.getCurrentHP()-4);
			if(i.getCurrentHP()<=0) {
				Dead.add(oppField.indexOf(i));
			}
		}
		for(Integer i : Dead) {
			oppField.get(i).minionDeath();
			
		}
	}
}
