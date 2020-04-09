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

public class Paladin extends Hero {
	public Paladin() throws IOException, CloneNotSupportedException {
		super("Uther Lightbringer");
	}
	public void buildDeck() throws IOException, CloneNotSupportedException {
		ArrayList<Card> paladinDeck = this.getDeck();
		Spell seal = new SealOfChampions();
		Spell levelup = new LevelUp();
		Spell[] paladinSpells = {seal, seal, levelup, levelup};
		for(int i = 0; i<paladinSpells.length; i++) {
			paladinDeck.add(paladinSpells[i].clone());
		}
		ArrayList<Minion> minions = getAllNeutralMinions("neutral_minions.csv");
		ArrayList<Minion> minionHand = getNeutralMinions(minions, 15);
		for(int i = 0; i<minionHand.size(); i++) {
			 Minion temp = minionHand.get(i);
			 temp.setListener(this);
			 paladinDeck.add(temp.clone());
		}
		Minion tirion = new Minion("Tirion Fordring", 4, Rarity.LEGENDARY, 6, 6, true, true, false);
		tirion.setListener(this);
		paladinDeck.add(tirion.clone());
		shuffle(paladinDeck);
	}
	public void useHeroPower() throws NotEnoughManaException, HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException, FullFieldException, CloneNotSupportedException {
		super.useHeroPower();
		Minion silver = new Minion("Silver Hand Recruit", 1, Rarity.BASIC, 1,1,false,false,false);
		this.playMinion(silver);
	}
	
}
