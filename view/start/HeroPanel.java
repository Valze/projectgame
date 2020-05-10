package view.start;
import javax.swing.*;

import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.Spell;
import model.heroes.Hero;

import java.awt.*;
public class HeroPanel extends JPanel{
	public class HeroHand extends JPanel{
		public CardButton[] hand;
		public JPanel concealed;
		public HeroHand(Object[] cards) {
			hand = new CardButton[cards.length];
			this.concealed = new JPanel();
			for(int i = 0; i<cards.length; i++) {
				if(cards[i] instanceof Minion) {
					hand[i] = new MinionButton((Minion)cards[i]);
				}
				else {
					hand[i] = new SpellButton((Spell)cards[i]);
				}
				Card hide = new Minion("HIDDEN", 0, Rarity.BASIC, 0, 0, false, false, false);
				concealed.add(new MinionButton((Minion)hide));
				this.add(hand[i]);
			}
		}
	}
	Hero related;
	JLabel heroName;
	JButton heroPower;
	HeroHand cards;
	JPanel cardHolder; //using a panel to center cards
	public void hideCards() {
		cardHolder.remove(cards);
		cardHolder.add(cards.concealed);
	}
	public void update() {
		
	}
	public HeroPanel(Hero h, boolean Up) {
		super(new BorderLayout());
		this.cardHolder = new JPanel();
		related = h;
		String heroInfo = "<div style='text-align:center;'>" + "<h1>"+h.getName() + "</h1>"
						+ "<br>HP: " + h.getCurrentHP() 
						+"<br>Mana: "+ h.getCurrentManaCrystals() +" out of " +h.getTotalManaCrystals()
						+"<br>Cards in deck: "+h.getDeck().size()+"</div>";
		heroName = new JLabel("<html> "+ heroInfo + "</html>");
		cards = new HeroHand(h.getHand().toArray());
		cardHolder.add(cards);
		heroPower = new JButton("<html> Use Hero Power </html>");
		JPanel internal = new JPanel(); //JPanel to center Hero information
		internal.add(heroName);
		internal.add(heroPower);
		Box box = new Box(BoxLayout.Y_AXIS); //Layout used to center said JPanel
        box.add(Box.createVerticalGlue());
        box.add(internal);     
        box.add(Box.createVerticalGlue());
		this.add(BorderLayout.CENTER, box);
		if(Up) { //if the hero is supposed to be located in the northern
			//part of the screen, place his cards above him
			//otherwise place them below him
			this.add(BorderLayout.NORTH, cardHolder);
			this.hideCards();
		}
		else {
			this.add(BorderLayout.SOUTH, cardHolder);
		}
		//this.setSize(new Dimension(50,50));
		//this.setMaximumSize(new Dimension(100, 100));
		this.setVisible(true);
	}
}
