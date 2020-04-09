package model.heroes;

import java.util.ArrayList;

import engine.ActionValidator;
import exceptions.CannotAttackException;
import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughManaException;
import exceptions.NotSummonedException;
import exceptions.NotYourTurnException;
import exceptions.TauntBypassException;

import java.io.*;
import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Icehowl;
import model.cards.minions.Minion;
import model.cards.minions.MinionListener;
import model.cards.spells.AOESpell;
import model.cards.spells.FieldSpell;
import model.cards.spells.HeroTargetSpell;
import model.cards.spells.LeechingSpell;
import model.cards.spells.MinionTargetSpell;
import model.cards.spells.*;

public abstract class Hero implements MinionListener{
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
	public Hero(String name) throws IOException, CloneNotSupportedException{
		this.name = name;
		setCurrentHP(30);
		this.heroPowerUsed = false;
		this.currentManaCrystals = totalManaCrystals;
		this.deck = new ArrayList<Card>();
		this.field = new ArrayList<Minion>();
		this.hand = new ArrayList<Card>();
		this.fatigueDamage = 0;
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
		if(currentHP<=0) {
			this.heroDeath();
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
	public void useHeroPower() throws NotEnoughManaException,
	HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException,
	FullFieldException, CloneNotSupportedException {
		this.validator.validateUsingHeroPower(this);
		this.validator.validateTurn(this);
		this.setCurrentManaCrystals(this.currentManaCrystals - 2);
		this.setHeroPowerUsed(true);
	}
	public void playMinion(Minion m) throws NotYourTurnException, NotEnoughManaException, FullFieldException{
		this.validator.validateTurn(this);
		this.validator.validateManaCost(m);
		if(this.field.size()==7) {
			throw new FullFieldException();
		}
		this.hand.remove(m);
		this.field.add(m);
	}
	public void attackWithMinion(Minion attacker, Minion target) throws
	CannotAttackException, NotYourTurnException, TauntBypassException,
	InvalidTargetException, NotSummonedException{
		this.validator.validateTurn(this);
		this.validator.validateAttack(attacker, target);
		attacker.attack(target);
	}
	public void attackWithMinion(Minion attacker, Hero target) throws
	CannotAttackException, NotYourTurnException, TauntBypassException,
	InvalidTargetException, NotSummonedException{
		this.validator.validateTurn(this);
		this.validator.validateAttack(attacker, target);
		attacker.attack(target);
	}
	public void castSpell(FieldSpell s) throws NotYourTurnException,
	NotEnoughManaException{
		this.validator.validateTurn(this);
		this.validator.validateManaCost((Card) s);
		s.performAction(this.field);
		this.hand.remove((Card)s);
		this.setCurrentManaCrystals(this.currentManaCrystals-(((Card) s).getManaCost()));
	}
	public void castSpell(AOESpell s, ArrayList<Minion> oppField) throws
	NotYourTurnException, NotEnoughManaException{
		this.validator.validateTurn(this);
		this.validator.validateManaCost((Card) s);
		s.performAction(oppField, this.field);
		this.hand.remove((Card)s);
		
		this.setCurrentManaCrystals(this.currentManaCrystals-(((Card) s).getManaCost()));
	}
	public void castSpell(MinionTargetSpell s, Minion m) throws NotYourTurnException,
	NotEnoughManaException, InvalidTargetException{
		this.validator.validateTurn(this);
		this.validator.validateManaCost((Card)s);
		s.performAction(m);
		this.hand.remove((Card)s);
		this.setCurrentManaCrystals(this.currentManaCrystals-(((Card) s).getManaCost()));
	}
	public void castSpell(HeroTargetSpell s, Hero h) throws NotYourTurnException,
	NotEnoughManaException{
		this.validator.validateTurn(this);
		this.validator.validateManaCost((Card)s);
		s.performAction(h);
		this.hand.remove((Card)s);
		this.setCurrentManaCrystals(this.currentManaCrystals-(((Card) s).getManaCost()));
	}
	public void castSpell(LeechingSpell s, Minion m) throws NotYourTurnException,
	NotEnoughManaException{
		this.validator.validateTurn(this);
		this.validator.validateManaCost((Card)s);
		int h = s.performAction(m);
		this.setCurrentHP(this.getCurrentHP() + h);
		this.hand.remove((Card)s);
		
		this.setCurrentManaCrystals(this.currentManaCrystals-(((Card) s).getManaCost()));
	}
	public Card drawCard() throws FullHandException, CloneNotSupportedException{
		if(this.deck.size()==0) {
			this.setCurrentHP(this.currentHP-this.fatigueDamage);
			this.fatigueDamage = this.fatigueDamage + 1;
			return null;
		}
		Card drawn = this.deck.get(0);
		if(this.hand.size()==10) {
			throw new FullHandException("You have a full hand", drawn);
		}
		this.deck.remove(drawn);
		if(this.deck.size()==0) {
			this.fatigueDamage = 1;
		}
		this.hand.add(drawn);

		return drawn;
		
	}
	public void endTurn() throws FullHandException, CloneNotSupportedException{
		listener.endTurn();
	}
	public void onMinionDeath(Minion m) {
		this.field.remove(m);
	}
    public void heroDeath() {
		listener.onHeroDeath();
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
	public final static ArrayList<Minion> getNeutralMinions(ArrayList<Minion> minions, int count) throws IOException, CloneNotSupportedException{
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
			Minion toadd = buffer.clone();
			minionHand.add(toadd);
			if(toadd.getRarity()==Rarity.LEGENDARY) {
				repeated[random] = 2;
				continue;
			}
			repeated[random]++;
			
		}
		return minionHand;
	}
	public abstract void buildDeck() throws IOException, CloneNotSupportedException;
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
