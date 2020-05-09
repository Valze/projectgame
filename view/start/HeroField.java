package view.start;
import javax.swing.*;

import model.cards.minions.Minion;
import model.heroes.Hero;

public class HeroField extends JPanel {
	public class MinionButton extends JButton{
		Minion minion;
		public MinionButton(Minion minion, Hero h) {
			super(minion.toString());
			this.minion = minion;
			this.minion.setListener(h);
		}
	}
	MinionButton[] cards;
	public HeroField(Object[] minions, Hero h) {
		this.cards = new MinionButton[minions.length];
		for(int i = 0; i<cards.length; i++) {
			cards[i] = new MinionButton((Minion)minions[i], h);
			this.add(cards[i]);
		}
	}
}
