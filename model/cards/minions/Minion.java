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
		setAttack(attack);
		this.maxHP = maxHP;
		setCurrentHP(maxHP);
		this.taunt = taunt;
		this.divine = divine;
		this.sleeping = !charge;
		this.attacked = false;
	}
	public int getAttack() {
		return this.attack;
	}
	public int getMaxHP() {
		return this.maxHP;
	}
	public int getCurrentHP() {
		return this.currentHP;
	}
	public boolean isTaunt() {
		return this.taunt;
	}
	public boolean isDivine() {
		return this.divine;
	}
	public boolean isSleeping() {
		return this.sleeping;
	}
	public boolean isAttacked() {
		return this.attacked;
	}
	public void setAttack(int attack) {
		if(attack<0) {
			this.attack = 0;
			return;
		}
		
		this.attack = attack;
	}
	public void setMaxHP(int maxHP) {
		this.maxHP = maxHP;
	}
	public void setCurrentHP(int currentHP) {
		if(currentHP>this.maxHP) {
			return;
		}
		this.currentHP = currentHP;
	}
	public void setTaunt(boolean taunt) {
		this.taunt = taunt;
	}
	public void setDivine(boolean divine) {
		this.divine = divine;
	}
	public void setSleeping(boolean sleeping) {
		this.sleeping = sleeping;
	}
	public void setAttacked(boolean attacked) {
		this.attacked = attacked;
	}
}
