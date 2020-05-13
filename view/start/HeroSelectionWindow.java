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
	JButton Hunter;
	JButton Mage;
	JButton Paladin;
	JButton Warlock;
	JButton Priest;
	JButton Confirm;
	JPanel Heroes;
	JPanel display;
	JSplitPane Splitter;
	JLabel Selected;
	Hero FirstHero;
	Hero SecondHero;
	boolean isConfirmed;
	Hero temp;
	public HeroSelectionWindow () {
		super();
		this.Splitter = new JSplitPane();
		Splitter.setOrientation(JSplitPane.HORIZONTAL_SPLIT);
		Splitter.setSize(400,400);
		Splitter.setVisible(true);
		Heroes = new JPanel();
		Heroes.setLayout(new GridLayout(3,2));
		Heroes.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		this.Hunter = new JButton("Hunter");
		this.Mage = new JButton("Mage");
		this.Paladin = new JButton("Paladin");
		this.Priest = new JButton("Priest");
		this.Warlock = new JButton("Warlock");
		Heroes.add(Hunter);
		Heroes.add(Mage);
		Heroes.add(Paladin);
		Heroes.add(Priest);
		Heroes.add(Warlock);
		Splitter.setLeftComponent(Heroes);
		display = new JPanel();
		display.setLayout(new GridLayout());
		display.setVisible(true);
		this.Confirm = new JButton("Confirm");
		display.add(Confirm);
		Splitter.setRightComponent(display);
		this.add(Splitter);
		this.isConfirmed = false;
		this.setVisible(true);
		this.setSize(500,500);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == this.Hunter) {
			try {
				temp = new Hunter();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(e.getSource() == this.Mage) {
				try {
					temp = new Mage();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
			if(e.getSource() == this.Paladin) {
				try {
					temp = new Paladin();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
			if(e.getSource() == this.Priest) {
				try {
					temp = new Priest();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
			if(e.getSource() == this.Warlock) {
				try {
					temp = new Warlock();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
	}
		if(e.getSource() == this.Confirm) {
			if(!isConfirmed) {
			FirstHero = temp;
			}
			
			
		}
}	

	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource() ==  this.Hunter) {
			Selected = new JLabel("<html>" + "<h1>Rexxar</h1>");
		}
		if(e.getSource() ==  this.Mage) {
			Selected = new JLabel("<html>" + "<h1>Jaina Proudmoore</h1>");
			}
		if(e.getSource() ==  this.Paladin) {
			Selected = new JLabel("<html>" + "<h1>Uther Lightbringer</h1>");
		}
		if (e.getSource() == this.Priest) {
			Selected = new JLabel("<html>" + "<h1>Anduin Wrynn</h1>");
		}
		if(e.getSource() == this.Warlock) {
			Selected = new JLabel("<html>" + "<h1>Gul'dan</h1>");
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
