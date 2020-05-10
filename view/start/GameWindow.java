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
import exceptions.CannotAttackException;
import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughManaException;
import exceptions.NotSummonedException;
import exceptions.NotYourTurnException;
import exceptions.TauntBypassException;
import model.cards.Card;
import model.cards.minions.Minion;
import model.cards.spells.AOESpell;
import model.cards.spells.FieldSpell;
import model.cards.spells.LeechingSpell;
import model.cards.spells.MinionTargetSpell;
import model.cards.spells.Spell;
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
	Card selectedCard;
	
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
		for(int handButton = 0; handButton<firstHero.cards.hand.length;handButton++) {
			firstHero.cards.hand[handButton].addMouseListener(this);
		}
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
				selectedCard = null;
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
			//might be a good idea to put all of the minion attack/spelCast logic in a separate method
			//and call it here
			//TODO: allow player to actually play a card, DONE
			//TODO: allow player to cast spells, done
			//TODO: allow player to target enemy hero with minions or spells
			Minion toAttack = null;
			for(int j = 0; j<field.secondField.cards.length; j++) {
				if (e.getSource()==field.secondField.cards[j]) {
					toAttack = (Minion) field.secondField.cards[j].card;
				}
			}
			for(int i = 0; i<field.firstField.cards.length; i++) {
				if (e.getSource()==field.firstField.cards[i]) {
					if(selectedCard!=null && selectedCard instanceof Spell) {
						toAttack = (Minion) field.firstField.cards[i].card;
					}
					else {
						selectedCard = (Minion) field.firstField.cards[i].card;
					}
					
				}
			}
			
			if(selectedCard!=null && toAttack!=null) {
				if(selectedCard instanceof Minion) {
					Minion selectedMinion = (Minion) selectedCard;
					try {
						game.getCurrentHero().attackWithMinion(selectedMinion, toAttack);
					} 
					catch (CannotAttackException | NotYourTurnException | TauntBypassException
							| InvalidTargetException | NotSummonedException e1) {
						// TODO Handle exceptions and output errors
						e1.printStackTrace();
					}
					selectedCard = null;
					
				}
				else {
					Spell selectedSpell = (Spell) selectedCard;
					if(selectedSpell instanceof MinionTargetSpell) {
						try {
							game.getCurrentHero().castSpell((MinionTargetSpell)selectedSpell, toAttack);
						} catch (NotYourTurnException | NotEnoughManaException | InvalidTargetException e1) {
							// TODO Handle exceptions and output errors
							e1.printStackTrace();
						}
					}
					else if(selectedSpell instanceof LeechingSpell) {
						try {
							game.getCurrentHero().castSpell((LeechingSpell)selectedSpell, toAttack);
						} catch (NotYourTurnException | NotEnoughManaException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					selectedCard = null;
				}
			}
			Minion minionToPlay = null;
			for(int handCard = 0; handCard<firstHero.cards.hand.length;handCard++) {
				if (e.getSource()==firstHero.cards.hand[handCard]) {
					if(firstHero.cards.hand[handCard].card instanceof Minion) {
						minionToPlay = (Minion) firstHero.cards.hand[handCard].card;
						try {
							game.getCurrentHero().playMinion(minionToPlay);
						} catch (NotYourTurnException | NotEnoughManaException | FullFieldException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else {
						selectedCard = (Spell) firstHero.cards.hand[handCard].card;
						if(selectedCard instanceof AOESpell) {
							try {
								game.getCurrentHero().castSpell((AOESpell) selectedCard, game.getOpponent().getField());
							} catch (NotYourTurnException | NotEnoughManaException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						else if(selectedCard instanceof AOESpell) {
							try {
								game.getCurrentHero().castSpell((AOESpell) selectedCard, game.getOpponent().getField());
							} catch (NotYourTurnException | NotEnoughManaException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
						else if(selectedCard instanceof FieldSpell) {
							try {
								game.getCurrentHero().castSpell((FieldSpell) selectedCard);
								
							} catch (NotYourTurnException | NotEnoughManaException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
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
			for(int handButton = 0; handButton<firstHero.cards.hand.length;handButton++) {
				firstHero.cards.hand[handButton].addMouseListener(this);
			}
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
			if(selectedCard!=null) {
				if(selected!=null) {
					this.remove(selected);
				}
				selected = new JLabel(selectedCard.toString());
				selected.setVisible(true);
				this.add(BorderLayout.WEST, selected);	
			}
			this.revalidate();
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
				toAttack = (Minion) field.secondField.cards[j].card;
			}
		}
		if(selectedCard!=null && toAttack!=null && selectedCard instanceof Minion) {
			Minion selectedMinion = (Minion) selectedCard;
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
