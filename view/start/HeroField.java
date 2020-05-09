package view.start;
import javax.swing.*;

import model.cards.minions.Minion;
import model.heroes.Hero;

public class HeroField extends JPanel {
	MinionButton[] cards;
	public HeroField(Object[] minions) {
		this.cards = new MinionButton[minions.length];
		for(int i = 0; i<cards.length; i++) {
			cards[i] = new MinionButton((Minion)minions[i]);
			this.add(cards[i]);
		}
	}
}
