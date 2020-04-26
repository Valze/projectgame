package view.start;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;
import engine.Game;
import exceptions.FullHandException;
import model.heroes.Hunter;
import model.heroes.Paladin;
import model.heroes.Hero;
public class Main {
	public static void main(String[] args) throws IOException, CloneNotSupportedException, FullHandException {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(600, 600);
		window.setLayout(new BorderLayout());
		Hero hunter = new Hunter();
		Hero paladin = new Paladin();
		Game game = new Game(hunter, paladin);
		HeroPanel firstHero = new HeroPanel(hunter, false); 
		//HeroPanels are responsible for rendering each Hero's
		//HP, mana, hero power, and hand
		HeroPanel secondHero = new HeroPanel(paladin, true);
		window.add(BorderLayout.SOUTH, firstHero);
		JTextArea text = new JTextArea();
		window.add(BorderLayout.CENTER, text);
		window.add(BorderLayout.NORTH, secondHero);
		window.setVisible(true);
	}
}