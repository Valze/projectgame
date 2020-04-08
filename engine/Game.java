package engine;

import exceptions.CannotAttackException;
import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughManaException;
import exceptions.NotSummonedException;
import exceptions.NotYourTurnException;
import exceptions.TauntBypassException;
import model.cards.Card;
import model.cards.minions.Minion;
import model.heroes.*;

public class Game implements ActionValidator , HeroListener {

	private Hero firstHero;
	private Hero secondHero;
	private Hero currentHero;
	private Hero opponent;
	private GameListener listener;
	
	public Game() {}
	
	public Game(Hero p1, Hero p2) throws FullHandException, CloneNotSupportedException{
		firstHero = p1;
		secondHero = p2;
		p1.setListener(this);
		p1.setValidator(this);
		p2.setListener(this);
		p2.setValidator(this);
		
		int r = (int) (Math.random()*6);
		currentHero = (r%2 == 0)? p1 : p2;
		opponent = (!(r%2 == 0))? p1 : p2;
		currentHero.setTotalManaCrystals(1);
		currentHero.setCurrentManaCrystals(1);;
		
		for(int i = 0; i<3; i++)
			currentHero.drawCard();
		for(int i = 0; i<4; i++)
			opponent.drawCard();
	}

	public Hero getCurrentHero() {
		return currentHero;
	}


	public Hero getOpponent() {
		return opponent;
	}

	public void setListener(GameListener listener) {
		this.listener = listener;
	}
	
	public void validateTurn(Hero user) throws NotYourTurnException{
		if(user != currentHero) {
			throw new NotYourTurnException("This is not your turn");
		}
	}
	public void validateAttack(Minion attacker,Minion target) throws CannotAttackException,NotSummonedException,TauntBypassException,InvalidTargetException{
		if(attacker.isSleeping() == true) {
			throw new CannotAttackException("Minion is Sleeping");
		}
		else if(!currentHero.getField().contains(attacker)) {
			throw new NotSummonedException("Minion is in hand");
		}
		else if(currentHero.getField().contains(target)) {
			throw new InvalidTargetException("Cannot attack friendly minions");
		}
		else if(attacker.getAttack()<=0) {
			throw new CannotAttackException("Minion has no attack power");
		}
		else if(!opponent.getField().contains(target)) {
			throw new NotSummonedException("Target not summoned");
		}
		else if(attacker.isAttacked()==true) {
			throw new CannotAttackException("Minion already attacked");
		}
		else {
			for(Minion i :opponent.getField()){
				if(i.isTaunt() == target.isTaunt()) {
					attacker.attack(target);
				}
				else if(i.isTaunt()==true) {
					throw new TauntBypassException("Taunt minion on field");
				}
			}
		}
	}
	public void validateAttack(Minion attacker,Hero target) throws CannotAttackException,NotSummonedException,TauntBypassException,InvalidTargetException{
		if(attacker.isSleeping() == true) {
			throw new CannotAttackException("Minion is Sleeping");
		}
		else if(!currentHero.getField().contains(attacker)) {
			throw new NotSummonedException("Minion is in hand");
		}
		else if(attacker.isAttacked()==true) {
			throw new CannotAttackException("Minion already attacked");
		}
		else if(currentHero == target) {
			throw new CannotAttackException("Cannot attack own hero");
		}
		else {
			for(Minion i :opponent.getField()){
				if(i.isTaunt()==true) {
					throw new TauntBypassException("Taunt minion on field");
				}
			}
		}
	}
	public void validateManaCost(Card card) throws NotEnoughManaException{
		if(card.getManaCost()> currentHero.getCurrentManaCrystals()) {
			throw new NotEnoughManaException("Not enough mana");
		}
	}
	public void validatePlayingMinion(Minion minion) throws FullFieldException{
		if(currentHero.getField().size()>=7) {
			throw new FullFieldException("Field is full");
		}
	}
	public void validateUsingHeroPower(Hero hero) throws NotEnoughManaException, HeroPowerAlreadyUsedException{
		if(hero.isHeroPowerUsed() == true) {
			throw new HeroPowerAlreadyUsedException("Hero power already used");
		}
		else if(hero.getCurrentManaCrystals()<2) {
			throw new NotEnoughManaException("Not enough mana");
		}
	
	}
	public void onHeroDeath() {
		
	}
	public void damageOpponent(int amount) {
		opponent.setCurrentHP(opponent.getCurrentHP() - amount);
	}
    public void endTurn() throws FullHandException,CloneNotSupportedException{
		Hero newHero;
		newHero = currentHero;
		currentHero = opponent;
		opponent = newHero;
    	
    	if(currentHero.getTotalManaCrystals()>=10) {
			currentHero.setCurrentManaCrystals(currentHero.getTotalManaCrystals());
			opponent.setCurrentManaCrystals(opponent.getTotalManaCrystals());
		}
		else {
			currentHero.setTotalManaCrystals(currentHero.getTotalManaCrystals()+1);
			opponent.setTotalManaCrystals(opponent.getTotalManaCrystals()+1);
			currentHero.setCurrentManaCrystals(currentHero.getTotalManaCrystals());
			opponent.setCurrentManaCrystals(opponent.getTotalManaCrystals());
		}
		currentHero.setHeroPowerUsed(false);
		opponent.setHeroPowerUsed(false);
		for(Minion i : currentHero.getField()) {
			i.setAttacked(false);
			i.setSleeping(false);
		}
		currentHero.drawCard();
	}
}
