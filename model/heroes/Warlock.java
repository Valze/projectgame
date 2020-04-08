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

public class Warlock extends Hero {
	public Warlock() throws IOException, CloneNotSupportedException {
		super("Gul'dan");
	}
	public void buildDeck() throws IOException, CloneNotSupportedException{
		ArrayList<Card> warlockDeck = this.getDeck();
		Spell curse = new CurseOfWeakness();
		Spell soul = new SiphonSoul();
		Spell nether = new TwistingNether();
		Spell[] warlockSpells = {curse, curse, soul, 
				soul, nether, nether};
		for(int i = 0; i<warlockSpells.length; i++) {
			warlockDeck.add(warlockSpells[i].clone());
		}
		ArrayList<Minion> minions = getAllNeutralMinions("neutral_minions.csv");
		ArrayList<Minion> minionHand = getNeutralMinions(minions, 13);
		for(int i = 0; i<minionHand.size(); i++) {
			Minion temp = minionHand.get(i);
			temp.setListener(this);
			warlockDeck.add(temp.clone());
		}
		Minion wilfred = new Minion("Wilfred Fizzlebang", 6, Rarity.LEGENDARY, 4, 4, false, false, false);
		wilfred.setListener(this);
		warlockDeck.add(wilfred.clone());
		shuffle(warlockDeck);
	}
	public void useHeroPower() throws NotEnoughManaException, HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException, FullFieldException, CloneNotSupportedException {
		super.useHeroPower();
		this.drawCard();
		this.setCurrentHP(this.getCurrentHP() - 2);
	}
}
