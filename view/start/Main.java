package view.start;

import java.awt.*;

import java.io.IOException;

import javax.swing.*;
import engine.Game;
import exceptions.FullHandException;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.heroes.Hero;
import model.heroes.Hunter;
import model.heroes.Paladin;
import model.heroes.Priest;
import model.heroes.Warlock;
import model.heroes.Mage;
public class Main {
	public static void main(String[] args) throws IOException, CloneNotSupportedException, FullHandException {
		//Hero hunter = new Paladin();
		//Hero paladin = new Mage();
		//TODO: replace hunter,paladin with heroes from a selection screen
		//How?
		//Subclass JFrame like in GameWindow, then create its specific GUI components for
		//hero selection, then return selected heroes here and pass them to GameWindow
		HeroSelectionWindow select = new HeroSelectionWindow();
		while(!(select.FirstHero != null && select.SecondHero != null)) {
			System.out.println();
		}
		select.dispose();
		GameWindow main = new GameWindow(select.FirstHero, select.SecondHero);
	}
}