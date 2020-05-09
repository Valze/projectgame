package view.start;

import javax.swing.JButton;

import model.cards.minions.Minion;
import model.heroes.Hero;

public class MinionButton extends JButton{
	Minion minion;
	public MinionButton(Minion minion) {
		super(minion.toString());
		this.minion = minion;
	}
}