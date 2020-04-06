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

public class Priest extends Hero {
	public Priest() throws IOException, CloneNotSupportedException {
		super("Anduin Wrynn");
	}
	public void buildDeck() throws IOException, CloneNotSupportedException {
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
	public void useHeroPower(Hero target) throws NotEnoughManaException, HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException, FullFieldException, CloneNotSupportedException {
		super.useHeroPower();
		target.setCurrentHP(target.getCurrentHP() + 2);
	}
	public void useHeroPower(Minion target) throws NotEnoughManaException, HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException, FullFieldException, CloneNotSupportedException {
		super.useHeroPower();
		target.setCurrentHP(target.getCurrentHP() + 2);
	}
}
