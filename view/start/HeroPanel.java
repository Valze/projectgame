package view.start;
import javax.swing.*;

import model.heroes.Hero;

import java.awt.*;
public class HeroPanel extends JPanel{
	public class CardList extends JList{
		public CardList(Object[] cards) {
			super(cards);
			this.setVisibleRowCount(1);
			this.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		}
		public CardList update(Object[] cards) {
			CardList updated = new CardList(cards);
			return updated;
		}
	}
	JLabel heroName;
	CardList cards;
	JButton endTurn;
	public HeroPanel(Hero h, boolean Up) {
		super(new BorderLayout());
		String heroInfo = "<div style='text-align:center;'>" + "<h1>"+h.getName() + "</h1>"
						+ "<br>HP: " + h.getCurrentHP() 
						+"<br>Mana: "+ h.getCurrentManaCrystals()+"</div>";
		heroName = new JLabel("<html> "+ heroInfo + "</html>");
		cards = new CardList(h.getHand().toArray());
		endTurn = new JButton("End turn");
		JPanel internal = new JPanel(); //JPanel to center Hero information
		internal.add(heroName);
		Box box = new Box(BoxLayout.Y_AXIS); //Layout used to center said JPanel
        box.add(Box.createVerticalGlue());
        box.add(internal);     
        box.add(Box.createVerticalGlue());
		//endTurn.addActionListener();
		this.add(BorderLayout.CENTER, box);
		if(Up) { //if the hero is supposed to be located in the northern
			//part of the screen, place his cards above him
			//otherwise place them below him
			this.add(BorderLayout.NORTH, cards);
		}
		else {
			this.add(BorderLayout.SOUTH, cards);
		}
		this.add(BorderLayout.EAST, endTurn); //will change later to be
											//one endturn button
		//this.setSize(new Dimension(50,50));
		//this.setMaximumSize(new Dimension(100, 100));
		this.setVisible(true);
	}
}
