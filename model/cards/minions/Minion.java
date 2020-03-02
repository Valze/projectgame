package model.cards.minions;

import model.cards.Card;
import model.cards.Rarity;

public class Minion extends Card {
	private int attack;
	private int maxHP;
	private int currentHP;
	private boolean taunt;
	private boolean divine;
	private boolean sleeping;
	private boolean attacked;
	public Minion(String name, int manaCost, Rarity rarity, int attack, int maxHP, boolean taunt, boolean divine, boolean charge) {
		super(name, manaCost, rarity);
		this.attack = attack;
		this.maxHP = maxHP;
		this.currentHP = maxHP;
		this.taunt = taunt;
		this.divine = divine;
		this.sleeping = !charge;
		this.attacked = !charge;
	}
}
