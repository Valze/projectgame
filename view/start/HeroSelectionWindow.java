package view.start;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

import model.heroes.*;


public class HeroSelectionWindow extends JFrame implements MouseListener {
	
	public class Heroes{
		HeroButton[] heroes;
		public Heroes() throws CloneNotSupportedException, IOException {
			heroes = new HeroButton[5];
			heroes[0] = new HeroButton(new Hunter());
			heroes[1] = new HeroButton(new Mage());
			heroes[2] = new HeroButton(new Warlock());
			heroes[3] = new HeroButton(new Paladin());
			heroes[4] = new HeroButton(new Priest());	
		}
		public void addListeners(HeroSelectionWindow s) {
			for(int i =0; i<heroes.length; i++) {
				heroes[i].addMouseListener(s);
			}
		}
	}
	
	public class DisplaySelection extends JSplitPane{
		JLabel hero1;
		JLabel hero2;
		JSplitPane showHeroes;
		JButton confirm;
		public DisplaySelection() {
			super();
			this.setOrientation(JSplitPane.VERTICAL_SPLIT);
			this.setResizeWeight(0.5);
			showHeroes = new JSplitPane();
			showHeroes.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
			showHeroes.setResizeWeight(0.5);
			hero1 = new JLabel("Hero 1 not chosen.");
			hero2 = new JLabel("Hero 2 not chosen.");
			showHeroes.setLeftComponent(hero1);
			showHeroes.setRightComponent(hero2);
			confirm = new JButton("Confirm");
			this.setTopComponent(showHeroes);
			this.setBottomComponent(confirm);
		}
		public void updateHeroes(Hero update) {
			if(FirstHero==null && temp==null) {
				hero1 = new JLabel(update.getName());
			}
			if(FirstHero==null && temp!=null) {
				hero1 = new JLabel(update.getName());
			}
			if(FirstHero!=null) {
					if(temp==null) {
						hero1 = new JLabel(FirstHero.getName());
					}
					else
						hero2 = new JLabel(update.getName());
			}
			showHeroes.setLeftComponent(hero1);
			showHeroes.setRightComponent(hero2);
			this.revalidate();
			showHeroes.repaint();
			this.repaint();
			
			//else {
			//	hero1 = new JLabel(temp.getName());
			//	else {
			//		hero2 = new JLabel(temp.getName());
			//	}
			//}
		}
		public void addListener(HeroSelectionWindow s) {
			confirm.addMouseListener(s);
		}
	}
	
	Heroes heroes;
	JPanel selectHeroes;
	DisplaySelection display;
	JSplitPane Splitter;
	Hero FirstHero;
	Hero SecondHero;
	Hero temp;
	
	public HeroSelectionWindow () throws CloneNotSupportedException, IOException {
		super();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Hero Selection");
		heroes = new Heroes();
		heroes.addListeners(this);
		this.Splitter = new JSplitPane();
		Splitter.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		Splitter.setResizeWeight(0.5);
		Splitter.setSize(600,600);
		Splitter.setVisible(true);
		selectHeroes = new JPanel();
		selectHeroes.setLayout(new GridLayout(3,2));
		selectHeroes.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		for(int i = 0; i<heroes.heroes.length;i++) {
			selectHeroes.add(heroes.heroes[i]);
		}
		Splitter.setLeftComponent(selectHeroes);
		display = new DisplaySelection();
		display.addListener(this);
		Splitter.setRightComponent(display);
		this.add(Splitter);
		this.setVisible(true);
		this.setSize(700,700);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		for(int i = 0; i<heroes.heroes.length;i++) {
			if(e.getSource() == heroes.heroes[i]) {
				temp = heroes.heroes[i].related;
			}
		}
		if(e.getSource() == display.confirm) {
			if(temp==null) {
				Message message = new Message(this, "You must select a hero");
			}
			else {
				if(FirstHero==null) {
					FirstHero = temp;
					temp = null;
				}
				else {
					if(SecondHero==null)
						SecondHero = temp;
				}
			}
		}
		
		display.updateHeroes(temp);

	}	
	@Override
	public void mouseEntered(MouseEvent e) {
		Hero derp = null;
		for(int i = 0; i<heroes.heroes.length;i++) {
			if(e.getSource() == heroes.heroes[i]) {
				derp = heroes.heroes[i].related;
			}
		}
		if(derp!=null) {
			display.updateHeroes(derp);
		}
	}
	@Override
	public void mouseExited(MouseEvent e) {
		if(temp != null)
			display.updateHeroes(temp);
		else {
			if(FirstHero == null) {
				display.hero1 = new JLabel("Hero 1 not chosen.");
				display.showHeroes.setLeftComponent(display.hero1);
			}
			else {
				display.hero2 = new JLabel("Hero 2 not chosen.");
				display.showHeroes.setRightComponent(display.hero2);
			}
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
}
