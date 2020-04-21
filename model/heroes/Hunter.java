package model.heroes;

import java.io.IOException;
import java.util.ArrayList;

import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.NotEnoughManaException;
import exceptions.NotYourTurnException;
import model.cards.*;
import model.cards.minions.Minion;
import model.cards.spells.*;

public class Hunter extends Hero {
	public Hunter() throws IOException, CloneNotSupportedException {
		super("Rexxar");
	}
	public void buildDeck() throws IOException, CloneNotSupportedException {
		ArrayList<Card> hunterDeck = this.getDeck();
		Spell kill = new KillCommand();
		Spell multi = new MultiShot();
		Spell[] hunterSpells = {kill, kill, multi, multi};
		for(int i = 0; i<hunterSpells.length; i++) {
			hunterDeck.add(hunterSpells[i].clone());
		}
		ArrayList<Minion> minions = getAllNeutralMinions("neutral_minions.csv");
		ArrayList<Minion> minionHand = getNeutralMinions(minions, 15);
		for(int i = 0; i<minionHand.size(); i++) {
			Minion temp = minionHand.get(i);
			temp.setListener(this);
			hunterDeck.add(temp.clone());

		}
		Minion kingKrush = new Minion("King Krush", 9, Rarity.LEGENDARY, 8, 8, false, false, true);
		kingKrush.setListener(this);
		hunterDeck.add(kingKrush.clone());
		shuffle(hunterDeck);
	}
	public void useHeroPower() throws NotEnoughManaException, HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException, FullFieldException, CloneNotSupportedException {
		super.useHeroPower();
		this.getListener().damageOpponent(2);
	}
}
