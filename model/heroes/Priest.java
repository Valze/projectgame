package model.heroes;

import java.io.IOException;
import java.util.ArrayList;

import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.*;

public class Priest extends Hero {
	public Priest() throws IOException {
		super("Anduin Wrynn");
	}
	public void buildDeck() throws IOException {
		ArrayList<Card> priestDeck = this.getDeck();
		Spell divSpirit = new DivineSpirit();
		Spell holyNova = new HolyNova();
		Spell shadowWordDeath = new ShadowWordDeath();
		Spell[] priestSpells = {divSpirit, divSpirit, holyNova, 
				holyNova, shadowWordDeath, shadowWordDeath};
		for(int i = 0; i<priestSpells.length; i++) {
			priestDeck.add(priestSpells[i]);
		}
		ArrayList<Minion> minions = getAllNeutralMinions("neutral_minions.csv");
		ArrayList<Minion> minionHand = getNeutralMinions(minions, 13);
		for(int i = 0; i<minionHand.size(); i++) {
			priestDeck.add(minionHand.get(i));
		}
		Minion velen = new Minion("Prophet Velen", 7, Rarity.LEGENDARY, 7, 7, false, false, false);
		priestDeck.add(velen);
		shuffle(priestDeck);
	}
}
