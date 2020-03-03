package model.heroes;

import java.io.IOException;
import java.util.ArrayList;

import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.*;

public class Mage extends Hero {
	public Mage() throws IOException {
		super("Jaina Proudmoore");
	}
	public void buildDeck() throws IOException {
		ArrayList<Card> mageDeck = this.getDeck();
		Spell polymorph = new Polymorph();
		Spell flamestrike = new Flamestrike();
		Spell pyroblast = new Pyroblast();
		Spell[] mageSpells = {polymorph, polymorph, flamestrike,
				flamestrike, pyroblast, pyroblast};
		for(int i = 0; i<mageSpells.length; i++) {
			mageDeck.add(mageSpells[i]);
		}
		ArrayList<Minion> minions = getAllNeutralMinions("neutral_minions.csv");
		ArrayList<Minion> minionHand = getNeutralMinions(minions, 13);
		for(int i = 0; i<minionHand.size(); i++) {
			mageDeck.add(minionHand.get(i));
		}
		Minion kalycgos = new Minion("Kalycgos", 10, Rarity.LEGENDARY, 4, 12, false, false, false);
		mageDeck.add(kalycgos);
		shuffle(mageDeck);
	}
}
