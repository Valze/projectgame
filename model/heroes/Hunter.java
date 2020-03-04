package model.heroes;

import java.io.IOException;
import java.util.ArrayList;

import model.cards.*;
import model.cards.minions.Minion;
import model.cards.spells.*;

public class Hunter extends Hero {
	public Hunter() throws IOException {
		super("Rexxar");
	}
	public void buildDeck() throws IOException {
		ArrayList<Card> hunterDeck = this.getDeck();
		Spell kill = new KillCommand();
		Spell multi = new MultiShot();
		Spell[] hunterSpells = {kill, kill, multi, multi};
		for(int i = 0; i<hunterSpells.length; i++) {
			hunterDeck.add(hunterSpells[i]);
		}
		ArrayList<Minion> minions = getAllNeutralMinions("neutral_minions.csv");
		ArrayList<Minion> minionHand = getNeutralMinions(minions, 15);
		for(int i = 0; i<minionHand.size(); i++) {
			hunterDeck.add(minionHand.get(i));
		}
		Minion kingKrush = new Minion("King Krush", 9, Rarity.LEGENDARY, 8, 8, false, false, true);
		hunterDeck.add(kingKrush);
		shuffle(hunterDeck);
	}
}
