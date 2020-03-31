package engine;

import exceptions.CannotAttackException;
import exceptions.FullFieldException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughManaException;
import exceptions.NotSummonedException;
import exceptions.NotYourTurnException;
import exceptions.TauntBypassException;
import model.cards.Card;
import model.cards.minions.Minion;
import model.heroes.*;
 
public class Game implements ActionValidator {

	private Hero firstHero;
	private Hero secondHero;
	private Hero currentHero;
	private Hero opponent;
	private GameListener listener;
	
	public Game() {}
	
	public Game(Hero p1, Hero p2) {
		firstHero = p1;
		secondHero = p2;
		
		int r = (int) (Math.random()*6);
		currentHero = (r%2 == 0)? p1 : p2;
		opponent = (!(r%2 == 0))? p1 : p2;
		currentHero.setTotalManaCrystals(1);
		currentHero.setCurrentManaCrystals(1);;
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
		
	}
	public void validateAttack(Minion attacker,Minion target) throws CannotAttackException,NotSummonedException,TauntBypassException,InvalidTargetException{
		
	}
	public void validateAttack(Minion attacker,Hero target) throws CannotAttackException,NotSummonedException,TauntBypassException,InvalidTargetException{
		
	}
	public void validateManaCost(Card card) throws NotEnoughManaException{
		
	}
	public void validatePlayingMinion(Minion minion) throws FullFieldException{
		
	}
	public void validateUsingHeroPower(Hero hero) throws NotEnoughManaException{
		
	}
	
}
