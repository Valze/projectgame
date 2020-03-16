package model.heroes;

import java.io.IOException;
import java.util.ArrayList;

import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.*;

public class Paladin extends Hero {
	public Paladin() throws IOException {
		super("Uther Lightbringer");
	}
	public void buildDeck() throws IOException {
		ArrayList<Card> paladinDeck = this.getDeck();
		Spell seal = new SealOfChampions();
		Spell levelup = new LevelUp();
		Spell[] paladinSpells = {seal, seal, levelup, levelup};
		for(int i = 0; i<paladinSpells.length; i++) {
			paladinDeck.add(paladinSpells[i]);
		}
		ArrayList<Minion> minions = getAllNeutralMinions("neutral_minions.csv");
		ArrayList<Minion> minionHand = getNeutralMinions(minions, 15);
		for(int i = 0; i<minionHand.size(); i++) {
			paladinDeck.add(minionHand.get(i));
		}
		Minion tirion = new Minion("Tirion Fordring", 4, Rarity.LEGENDARY, 6, 6, true, true, false);
		paladinDeck.add(tirion);
		shuffle(paladinDeck);
	}
}
