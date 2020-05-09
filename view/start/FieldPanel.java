package view.start;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import model.heroes.Hero;

public class FieldPanel extends JSplitPane {
	
	HeroField firstField;
	HeroField secondField;
	public FieldPanel(Hero first, Hero second) {
		firstField = new HeroField(first.getField().toArray());
		secondField = new HeroField(second.getField().toArray());
		this.setOrientation(JSplitPane.VERTICAL_SPLIT);
		this.setBottomComponent(firstField);
		this.setTopComponent(secondField);
		this.setResizeWeight(0.5);
		this.setSize(200,200);
	}
}
