package model.heroes;

import java.util.ArrayList;

import model.cards.Card;
import model.cards.minions.Minion;

public abstract class Hero {
	private String name; //READ ONLY.
	private int currentHP;//default is 30, can never be higher
	private boolean heroPowerUsed;
	private int totalManaCrystals; //cannot be higher than 10
	private int currentManaCrystals;// not higher than 10 and Each turn, it starts with the value of the totalManaCrystals.
	private ArrayList<Card> deck; //READ ONLY
	private ArrayList<Minion> field;//READ ONLY
	private ArrayList<Card> hand; // READ ONLY
	private int fatigueDamage; //Neither READ nor WRITE
	public Hero(String name) {
		this.name = name; //will expand later
	}
	public String getName() {
		return this.name;
	}
	public int getCurrentHP() {
		return this.currentHP;
	}
	public boolean getHeroPowerUsed() {
		return this.heroPowerUsed;
	}
	public int getTotalManaCrystals() {
		return this.totalManaCrystals;
	}
	public int getCurrentManaCrystals() {
		return this.currentManaCrystals;
	}
	public ArrayList<Card> getDeck(){
		return this.deck;
	}
	public ArrayList<Minion> getField(){
		return this.field;
	}
	public ArrayList<Card> getHand(){
		return this.hand;
	}
	public void setCurrentHP(int currentHP) {
		this.currentHP = currentHP;
	}
	public void setHeroPowerUsed(boolean heroPowerUsed) {
		this.heroPowerUsed = heroPowerUsed;
	}
	public void setTotalManaCrystals(int totalManaCrystals) {
		this.totalManaCrystals = totalManaCrystals;
	}
	public void setCurrentManaCrystals(int currentManaCrystals) {
		this.currentManaCrystals = currentManaCrystals;
	}
}
