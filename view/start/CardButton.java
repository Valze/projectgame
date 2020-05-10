package view.start;

import javax.swing.JButton;

import model.cards.Card;

abstract public class CardButton extends JButton {
	Card card;
	public CardButton(Card card) {
		super(card.toString());
		this.card = card;
	}
}
