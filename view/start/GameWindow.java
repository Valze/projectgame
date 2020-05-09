package view.start;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import engine.Game;
import engine.GameListener;
import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.NotEnoughManaException;
import exceptions.NotYourTurnException;
import model.cards.minions.Minion;
import model.heroes.Hero;
import model.heroes.Hunter;
import model.heroes.Paladin;
import model.heroes.Priest;
import model.heroes.Warlock;
import model.heroes.Mage;

public class GameWindow extends JFrame implements MouseListener, GameListener  {
	Game game;
	HeroPanel firstHero;
	FieldPanel field;
	HeroPanel secondHero;
	JButton endTurn;
	JLabel selected;
	Minion selectedMinion;
	
	public GameWindow(Hero p1, Hero p2) throws FullHandException, CloneNotSupportedException {
		super();
		game = new Game(p1, p2);
		game.setListener(this);
		this.firstHero = new HeroPanel(game.getCurrentHero(), false);
		this.secondHero = new HeroPanel(game.getOpponent(), true);
		this.endTurn = new JButton("End turn");
		this.field = new FieldPanel(game.getCurrentHero(), game.getOpponent());
		endTurn.addMouseListener(this);
		firstHero.heroPower.addMouseListener(this);
		secondHero.heroPower.addMouseListener(this);
		this.setLayout(new BorderLayout());
		this.add(BorderLayout.SOUTH, firstHero);
		this.add(BorderLayout.EAST, endTurn);
		JTextArea text = new JTextArea();
		this.add(BorderLayout.CENTER, field);
		this.add(BorderLayout.NORTH, secondHero);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 600);
		this.setVisible(true);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		try {
			if (e.getSource()== this.endTurn) {
				game.endTurn();
				selectedMinion = null;
				if(selected!=null) {
					this.remove(selected);
				}
			}
			if (e.getSource()==firstHero.heroPower) {
				try {
					game.getCurrentHero().useHeroPower();
				} catch (NotEnoughManaException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (HeroPowerAlreadyUsedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NotYourTurnException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FullFieldException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if (e.getSource()==secondHero.heroPower) {
				//pop-out window for illegal action
			}
			//might be a good idea to put all of the minion attack logic in a separate method
			//and call it here
			//TODO: allow player to actually play a card
			for(int i = 0; i<field.firstField.cards.length; i++) {
				if (e.getSource()==field.firstField.cards[i]) {
					selectedMinion = field.firstField.cards[i].minion;
				}
			}
			Minion toAttack = null;
			for(int j = 0; j<field.secondField.cards.length; j++) {
				if (e.getSource()==field.secondField.cards[j]) {
					toAttack = field.secondField.cards[j].minion;
				}
			}
			if(selectedMinion!=null && toAttack!=null) {
				if(!selectedMinion.isSleeping()) {
					selectedMinion.attack(toAttack);
				}
				else {
					//output message about minion being asleep
				}
			}
			//first remove old GUI components
			this.remove(firstHero);
			this.remove(secondHero);
			this.remove(field);
			//second initialize new GUI components
			firstHero = new HeroPanel(game.getCurrentHero(), false);
			secondHero = new HeroPanel(game.getOpponent(), true);
			field = new FieldPanel(game.getCurrentHero(), game.getOpponent());
			firstHero.heroPower.addMouseListener(this);
			secondHero.heroPower.addMouseListener(this);
			for(int i = 0; i<field.firstField.cards.length; i++) {
				field.firstField.cards[i].addMouseListener(this);
			}
			for(int j = 0; j<field.secondField.cards.length; j++) {
				field.secondField.cards[j].addMouseListener(this);
			}
			//add them back to the screen
			this.add(BorderLayout.SOUTH, firstHero);
			this.add(BorderLayout.NORTH, secondHero);
			this.add(BorderLayout.CENTER, field);
			this.revalidate();
			if(selectedMinion!=null) {
				if(selected!=null) {
					this.remove(selected);
				}
				selected = new JLabel(selectedMinion.toString());
				selected.setVisible(true);
				this.add(BorderLayout.WEST, selected);	
			}
			firstHero.repaint();
			secondHero.repaint();
			field.repaint();
			if(this.selected!=null) {
				selected.repaint();
			}
			this.repaint();
		} catch (FullHandException e1) {
			// TODO show FullHandException error
			e1.printStackTrace();
		} catch (CloneNotSupportedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		Minion toAttack = null;
		for(int j = 0; j<field.secondField.cards.length; j++) {
			if (e.getSource()==field.secondField.cards[j]) {
				toAttack = field.secondField.cards[j].minion;
			}
		}
		if(selectedMinion!=null && toAttack!=null) {
			if(selected!=null) {
				this.remove(selected);
			}
			selected = new JLabel("<html>"
					+ "<h1>RESULT OF ATTACK</h1>"
					+ "<br><h2>" + selectedMinion.getName()
					+ "<br>HP: " + (selectedMinion.getCurrentHP() - toAttack.getAttack())
					+ "</html>");
			selected.setVisible(true);
			this.add(BorderLayout.WEST, selected);
			this.revalidate();
			selected.repaint();
			this.repaint();
		}
	}
	@Override
	public void mouseExited(MouseEvent e) {
		
		
	}
	@Override
	public void onGameOver() {
		// TODO add game end logic
		
	}
}
