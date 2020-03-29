package model.heroes;

import java.util.ArrayList;

import engine.ActionValidator;

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
	private HeroListener listener;//READ and WRITE
	private ActionValidator validator;//WRITE ONLY
	public Hero(String name) throws IOException{
		this.name = name;
		setCurrentHP(30);
		this.heroPowerUsed = false;
		this.currentManaCrystals = totalManaCrystals;
		this.deck = new ArrayList<Card>();
		this.field = new ArrayList<Minion>();
		this.hand = new ArrayList<Card>();
		this.buildDeck();
	}
	public String getName() {
		return this.name;
	}
	public int getCurrentHP() {
		return this.currentHP;
	}
	public boolean isHeroPowerUsed() {
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
	public HeroListener getListener() {
		return listener;
	}
	
	public void setCurrentHP(int currentHP) {
		if(currentHP>30) {
			return;
		}
		this.currentHP = currentHP;
	}
	public void setHeroPowerUsed(boolean heroPowerUsed) {
		this.heroPowerUsed = heroPowerUsed;
	}
	public void setTotalManaCrystals(int totalManaCrystals) {
		if(totalManaCrystals > 10) {
			this.totalManaCrystals = 10;
		}
		else {
			this.totalManaCrystals = totalManaCrystals;
		}
	}
	public void setCurrentManaCrystals(int currentManaCrystals) {
		if(currentManaCrystals > 10) {
			this.currentManaCrystals = 10;
			return;
		}
		this.currentManaCrystals = currentManaCrystals;
	}
	public void setListener(HeroListener listener) {
		this.listener = listener;
	}
	
	public void setValidator(ActionValidator validator) {
		this.validator = validator;
	}
	public final static ArrayList<Minion> getAllNeutralMinions(String filePath) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		ArrayList<Minion> minions = new ArrayList<Minion>();
		String Line = br.readLine();
		while(Line!=null){
			String[] currentLine = Line.split(",");
			String name = currentLine[0];
			if(name.equals("Icehowl")) {
				Icehowl icehowl = new Icehowl();
				minions.add(icehowl);
				Line = br.readLine();
				continue;
			}
			int manaCost = Integer.parseInt(currentLine[1]);
			Rarity rarity = null;
			switch(currentLine[2]) {
				case "b": rarity = Rarity.BASIC; break;
				case "c": rarity = Rarity.COMMON; break;
				case "r": rarity = Rarity.RARE; break;
				case "e": rarity = Rarity.EPIC; break;
				case "l": rarity = Rarity.LEGENDARY; break;
			}
			int attack = Integer.parseInt(currentLine[3]);
			int maxHP = Integer.parseInt(currentLine[4]);
			boolean taunt = Boolean.parseBoolean(currentLine[5]);
			boolean divine = Boolean.parseBoolean(currentLine[6]);
			boolean charge = Boolean.parseBoolean(currentLine[7]);
			Minion minion = new Minion(name, manaCost, rarity, attack, maxHP, taunt, divine, charge);
			minions.add(minion);
			Line = br.readLine();
		}
		br.close();
		return minions;
	}
	public final static ArrayList<Minion> getNeutralMinions(ArrayList<Minion> minions, int count) throws IOException{
		int[] repeated = new int[minions.size()];
		ArrayList<Minion> minionHand = new ArrayList<Minion>();
		int random = 0;
		int failures = 0;
		while(minionHand.size()<count) {
			random =(int) (Math.random()*(minions.size()));
			if(minionHand.size() == count -1) {
				for(int j = 0; j<repeated.length;j++) {
					if(repeated[j] !=2) {
						random = j;
						break;
					}
				}
			}
			else if(repeated[random]==2) {
				continue;
			}
			Minion buffer = minions.get(random);
			Minion toadd = new Minion(buffer.getName(),buffer.getManaCost(), buffer.getRarity(),
					buffer.getAttack(), buffer.getMaxHP(), buffer.isTaunt(), buffer.isDivine(), buffer.isSleeping());
			minionHand.add(toadd);
			if(toadd.getRarity()==Rarity.LEGENDARY) {
				repeated[random] = 2;
				continue;
			}
			repeated[random]++;
			
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
			}
			random = (int)(Math.random()*heroDeck.size());
		}
	}
}
