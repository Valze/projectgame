package view.start;

import javax.swing.JButton;

import model.cards.spells.Spell;

public class SpellButton extends JButton {
	Spell spell;
	public SpellButton(Spell spell) {
		super(spell.toString());
		this.spell = spell;
	}
}
