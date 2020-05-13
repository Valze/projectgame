package view.start;

import javax.swing.JButton;

import model.heroes.Hero;

public class HeroButton extends JButton {
	Hero related;
	public HeroButton(Hero related, String heroInfo) {
		super(heroInfo);
		this.related = related;
	}
	public HeroButton(Hero related) {
		super("<html><h1 style='text-align: center;'>"+related.getName()+"</h1></html>");
		this.related = related;
	}
}
