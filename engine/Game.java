package engine;

import model.heroes.*;

public class Game {

	private Hero firstHero;
	private Hero secondHero;
	private Hero currentHero;
	private Hero opponent;
	
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
	
}
