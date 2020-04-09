package model.heroes;

import java.io.IOException;
import java.util.ArrayList;

import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.NotEnoughManaException;
import exceptions.NotYourTurnException;
import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.*;

public class Mage extends Hero {
	public Mage() throws IOException, CloneNotSupportedException {
		super("Jaina Proudmoore");
	}
	public void buildDeck() throws IOException, CloneNotSupportedException {
		ArrayList<Card> mageDeck = this.getDeck();
		Spell polymorph = new Polymorph();
		Spell flamestrike = new Flamestrike();
		Spell pyroblast = new Pyroblast();
		Spell[] mageSpells = {polymorph, polymorph, flamestrike,
				flamestrike, pyroblast, pyroblast};
		for(int i = 0; i<mageSpells.length; i++) {
			mageDeck.add(mageSpells[i].clone());
		}
		ArrayList<Minion> minions = getAllNeutralMinions("neutral_minions.csv");
		ArrayList<Minion> minionHand = getNeutralMinions(minions, 13);
		for(int i = 0; i<minionHand.size(); i++) {
			Minion temp = minionHand.get(i);
			temp.setListener(this);
			mageDeck.add(temp.clone());
		
		}
		Minion kalycgos = new Minion("Kalycgos", 10, Rarity.LEGENDARY, 4, 12, false, false, false);
		kalycgos.setListener(this);
		mageDeck.add(kalycgos.clone());
		shuffle(mageDeck);
	}
	public void useHeroPower(Hero target) throws NotEnoughManaException, HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException, FullFieldException, CloneNotSupportedException {
		super.useHeroPower();
		target.setCurrentHP(target.getCurrentHP() - 1);
		
	}
	public void useHeroPower(Minion target) throws NotEnoughManaException, HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException, FullFieldException, CloneNotSupportedException {
		super.useHeroPower();
		if(target.isDivine()) {
			target.setDivine(false);
		}
		else {
			target.setCurrentHP(target.getCurrentHP() - 1);
		}
	}
}
