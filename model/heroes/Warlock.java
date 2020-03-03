package model.heroes;

import java.io.IOException;
import java.util.ArrayList;

import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.*;

public class Warlock extends Hero {
	public Warlock() throws IOException {
		super("Gul'dan");
	}
	public void buildDeck() throws IOException{
		ArrayList<Card> warlockDeck = this.getDeck();
		Spell curse = new CurseOfWeakness();
		Spell soul = new SiphonSoul();
		Spell nether = new TwistingNether();
		Spell[] warlockSpells = {curse, curse, soul, 
				soul, nether, nether};
		for(int i = 0; i<warlockSpells.length; i++) {
			warlockDeck.add(warlockSpells[i]);
		}
		ArrayList<Minion> minions = getAllNeutralMinions("test_minion.csv");
		ArrayList<Minion> minionHand = getNeutralMinions(minions, 13);
		for(int i = 0; i<minionHand.size(); i++) {
			warlockDeck.add(minionHand.get(i));
		}
		Minion wilfred = new Minion("Wilfred Fizzlebang", 6, Rarity.LEGENDARY, 4, 4, false, false, false);
		warlockDeck.add(wilfred);
		shuffle(warlockDeck);
	}
}
