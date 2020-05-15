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
import model.cards.spells.HeroTargetSpell;
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
	boolean selectTarget;
	Hero target;
	public GameWindow(Hero p1, Hero p2) throws FullHandException, CloneNotSupportedException {
		super();
		this.game = new Game(p1, p2);
		this.game.setListener(this);
		this.setLayout(new BorderLayout());
		this.updateGame();
		this.endTurn = new JButton("End turn");
		this.endTurn.addMouseListener(this);
		this.add(BorderLayout.EAST, endTurn);
		this.addListeners();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(600, 600);
		this.setVisible(true);
	}
	public void updateGame() {
		//first remove old GUI components
		
		if(firstHero!=null) {
			this.remove(firstHero);
			this.remove(secondHero);
			this.remove(field);
		}
		//second initialize new GUI components
		this.firstHero = new HeroPanel(game.getCurrentHero(), false);
		this.secondHero = new HeroPanel(game.getOpponent(), true);
		this.field = new FieldPanel(game.getCurrentHero(), game.getOpponent());
		//add them back to screen
		this.add(BorderLayout.SOUTH, firstHero);
		this.add(BorderLayout.CENTER, field);
		this.add(BorderLayout.NORTH, secondHero);
	}
	public void addListeners() {
		//listens to button clicks from both heroes' hands,
		//fields and hero powers
		firstHero.addListeners(this);
		secondHero.addListeners(this);
		field.addListeners(this);
	}
	public void handleHeroPowers(Hero target) throws FullHandException, CloneNotSupportedException {
		if(selectTarget) {
			System.out.print("HELLO");
			if(game.getCurrentHero() instanceof Priest) {
				try {
					if(target!=null && target!=game.getOpponent()) {
						((Priest)game.getCurrentHero()).useHeroPower(target);
						}
					else if(selectedCard!=null && selectedCard instanceof Minion) {
						((Priest)game.getCurrentHero()).useHeroPower((Minion)selectedCard);
						}
					}
				catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException
							| FullFieldException e1) {
						Message popup = new Message(this, e1.getMessage());
						e1.printStackTrace();
					}		
			}
			else {
				try {
					System.out.print("BYE");
					if(target!=null&&target!=game.getCurrentHero()) {
						((Mage)game.getCurrentHero()).useHeroPower(target);
						}
					else if(selectedCard!=null && selectedCard instanceof Minion) {
						((Mage)game.getCurrentHero()).useHeroPower((Minion)selectedCard);
						}
					}
				catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException
							| FullFieldException e1) {
						Message popup = new Message(this, e1.getMessage());
						e1.printStackTrace();
					}	
			}
			this.target = null;
			selectTarget = false;
		}
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
					if(firstHero.hero.related instanceof Mage || firstHero.hero.related instanceof Priest) {
						selectTarget = true;
					}
					else {
						game.getCurrentHero().useHeroPower();
					}
				} catch (NotEnoughManaException | HeroPowerAlreadyUsedException | NotYourTurnException
						| FullFieldException e1) {
					Message popup = new Message(this, e1.getMessage());
					e1.printStackTrace();
				}
				
			}
			if (e.getSource()==secondHero.heroPower) {
				Message popup = new Message(this, "Can't use other player's hero power");
			}
			//might be a good idea to put all of the minion attack/spellCast logic in a separate method
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
			if(e.getSource()==secondHero.hero) {
				target = secondHero.hero.related;
				handleHeroPowers(target);
			}
			if(e.getSource()==firstHero.hero) {
				target = firstHero.hero.related;
				handleHeroPowers(target);
			}
			
			if(selectedCard!=null && target!=null && target!=game.getCurrentHero()) {
				if(selectedCard instanceof Minion) {
					Minion selectedMinion = (Minion) selectedCard;
					try {
						game.getCurrentHero().attackWithMinion(selectedMinion, target);
					} 
					catch (CannotAttackException | NotYourTurnException | TauntBypassException
							| InvalidTargetException | NotSummonedException e1) {
						Message popup = new Message(this, e1.getMessage());
						e1.printStackTrace();
					}
				}
				else {
					Spell selectedSpell = (Spell) selectedCard;
					if(selectedSpell instanceof HeroTargetSpell) {
						try {
							game.getCurrentHero().castSpell((HeroTargetSpell)selectedSpell, target);
						} catch (NotYourTurnException | NotEnoughManaException e1) {
							Message popup = new Message(this, e1.getMessage());
							e1.printStackTrace();
						}
					}
				}
				target = null;
				selectedCard = null;
			}
			if(selectedCard!=null && toAttack!=null) {
				if(selectedCard instanceof Minion) {
					Minion selectedMinion = (Minion) selectedCard;
					try {
						game.getCurrentHero().attackWithMinion(selectedMinion, toAttack);
					} 
					catch (CannotAttackException | NotYourTurnException | TauntBypassException
							| InvalidTargetException | NotSummonedException e1) {
						Message popup = new Message(this, e1.getMessage());
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
							Message popup = new Message(this, e1.getMessage());
							e1.printStackTrace();
						}
					}
					else if(selectedSpell instanceof LeechingSpell) {
						try {
							game.getCurrentHero().castSpell((LeechingSpell)selectedSpell, toAttack);
						} catch (NotYourTurnException | NotEnoughManaException e1) {
							Message popup = new Message(this, e1.getMessage());
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
							Message popup = new Message(this, e1.getMessage());
							e1.printStackTrace();
						}
					}
					else {
						selectedCard = (Spell) firstHero.cards.hand[handCard].card;
						if(selectedCard instanceof AOESpell) {
							try {
								game.getCurrentHero().castSpell((AOESpell) selectedCard, game.getOpponent().getField());
							} catch (NotYourTurnException | NotEnoughManaException e1) {
								Message popup = new Message(this, e1.getMessage());
								e1.printStackTrace();
							}
						}
						else if(selectedCard instanceof AOESpell) {
							try {
								game.getCurrentHero().castSpell((AOESpell) selectedCard, game.getOpponent().getField());
							} catch (NotYourTurnException | NotEnoughManaException e1) {
								Message popup = new Message(this, e1.getMessage());
								e1.printStackTrace();
							}
						}
						else if(selectedCard instanceof FieldSpell) {
							try {
								game.getCurrentHero().castSpell((FieldSpell) selectedCard);
								
							} catch (NotYourTurnException | NotEnoughManaException e1) {
								Message popup = new Message(this, e1.getMessage());
								e1.printStackTrace();
							}
						}
					}
				}
			}
			for(int handCard2 = 0; handCard2<secondHero.cards.hand.length;handCard2++) {
				if (e.getSource()==secondHero.cards.hand[handCard2]) {
					Message popup = new Message(this, "Can't select other player's cards");
				}
			}
			
			updateGame();
			addListeners();
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
			endTurn.repaint();
			if(this.selected!=null) {
				selected.repaint();
			}
			this.repaint();
		} catch (FullHandException e1) {
			Message popup = new Message(this, e1.getMessage());
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
		if(game.getCurrentHero().getCurrentHP()!=0) {
			Message message = new Message(this, game.getCurrentHero().getName() +" has won!");
		}
		else {
			Message message = new Message(this, game.getOpponent().getName() +" has won!");
		}
	}
}
