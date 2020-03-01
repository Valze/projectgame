package model.cards;

public abstract class Card {
	private String name;
	private int manaCost;
	private Rarity rarity;
	public Card() {
		
	}
	public Card(String name, int manaCost, Rarity rarity) {
		this.name = name;
		this.manaCost = manaCost;
		this.rarity = rarity;
	}
	public String getName() {
		return this.name;
	}
	public int getManaCost() {
		return this.manaCost;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setManaCost(int manaCost) {
		this.manaCost = manaCost;
	}
}
