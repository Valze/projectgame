package model.cards.minions;

import exceptions.InvalidTargetException;
import model.cards.Card;
import model.cards.Rarity;
import model.heroes.Hero;

public class Minion extends Card {
	private int attack;
	private int maxHP;
	private int currentHP;
	private boolean taunt;
	private boolean divine;
	private boolean sleeping;
	private boolean attacked;
	private MinionListener listener;
	
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
	public void setListener(MinionListener listener) {
		this.listener = listener;
	}
	public void minionDeath() {
		
	}
	public void attack(Minion target) {
		if(!this.isDivine() && !target.isDivine()) {
			this.setCurrentHP(this.currentHP-target.attack);
			target.setCurrentHP(target.currentHP-this.attack);
		}
		else if (this.isDivine() && target.isDivine()) {
			this.setDivine(false);
			target.setDivine(false);
		}
		else if (!this.isDivine()) {
			target.setDivine(false);
			this.setCurrentHP(this.currentHP-target.attack);
		}
		else if (!target.isDivine()) {
			this.setDivine(false);
			target.setCurrentHP(target.currentHP-this.attack);
		}
	}
	public void attack(Hero target) throws InvalidTargetException {
		if(this instanceof Icehowl) {
			throw new InvalidTargetException("Icehowl cannot target heroes");
		}
		else {
			target.setCurrentHP(target.getCurrentHP() - this.attack);
		}
	}
	public Minion clone() throws CloneNotSupportedException {
		return (Minion) super.clone();
	}
}
