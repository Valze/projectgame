package model.heroes;

import java.util.ArrayList;
import java.io.*;
import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Icehowl;
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
	public Hero(String name) throws IOException{
		this.name = name;
		this.deck = new ArrayList<Card>();
		this.buildDeck();
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
	public static ArrayList<Minion> getAllNeutralMinions(String filePath) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		ArrayList<Minion> minions = new ArrayList<Minion>();
		while(br.readLine()!=null){
			String[] currentLine = br.readLine().split(",");
			String name = currentLine[0];
			if(name.equals("Icehowl")) {
				Icehowl icehowl = new Icehowl();
				minions.add(icehowl);
				continue;
			}
			int manaCost = Integer.parseInt(currentLine[1]);
			Rarity rarity = null;
			switch(currentLine[2]) {
			case "b": rarity = Rarity.BASIC;
			case "c": rarity = Rarity.COMMON;
			case "r": rarity = Rarity.RARE;
			case "e": rarity = Rarity.EPIC;
			case "l": rarity = Rarity.LEGENDARY;
			}
			int attack = Integer.parseInt(currentLine[3]);
			int maxHP = Integer.parseInt(currentLine[4]);
			boolean taunt = Boolean.parseBoolean(currentLine[5]);
			boolean divine = Boolean.parseBoolean(currentLine[6]);
			boolean charge = Boolean.parseBoolean(currentLine[7]);
			Minion minion = new Minion(name, manaCost, rarity, attack, maxHP, taunt, divine, charge);
			minions.add(minion);
		}
		br.close();
		return minions;
	}
	public final static ArrayList<Minion> getNeutralMinions(ArrayList<Minion> minions, int count) throws IOException{
		int[] repeated = new int[minions.size()];
		ArrayList<Minion> minionHand = new ArrayList<Minion>();
		int random = 0;
		for(int i = 0; i<count; i++) {
			random =(int) (Math.random()*(minions.size()));
			if(repeated[random]==2) {
				i--;
				continue;
			}
			Minion toadd = minions.get(random);
			minionHand.add(toadd);
			if(toadd.getRarity()==Rarity.LEGENDARY) {
				repeated[random] = 2;
			}
			else {
				repeated[random]++;
			}
		}
		return minionHand;
	}
	public abstract void buildDeck() throws IOException;
	public static void shuffle(ArrayList<Card> heroDeck) {
		int random = (int)(Math.random()*heroDeck.size());
		for(int shuffle = 0; shuffle<heroDeck.size(); shuffle++) {
			Card swap = heroDeck.get(shuffle);
			Card swap2 = heroDeck.get(random);
			if(random!= shuffle) {
				heroDeck.set(random, swap);
				heroDeck.set(shuffle, swap2);
			}
			else {
				shuffle--;
				continue;
			}
		}
	}
}
