package model.cards.spells;

import java.util.ArrayList;

import model.cards.*;
import model.cards.minions.Minion;

public class MultiShot extends Spell implements AOESpell {
	
	public MultiShot(){
		super("Multi-Shot", 4, Rarity.BASIC);
	}
	public void performAction(ArrayList<Minion> oppField , ArrayList<Minion> curField) {
		
	}
}
