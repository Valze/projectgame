package view.start;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import model.heroes.Hero;


public class HeroSelectionWindow extends JFrame implements MouseListener {
	JButton Hunter;
	JButton Mage;
	JButton Paladin;
	JButton Warlock;
	JButton Priest;
	JButton Confirm;
	
	public HeroSelectionWindow(Hero p1 , Hero p2) {
		super();
		this.Hunter = new JButton("Hunter");
		this.Mage = new JButton("Mage");
		this.Paladin = new JButton("Paladin");
		this.Priest = new JButton("Priest");
		this.Warlock = new JButton("Warlock");
		this.Confirm = new JButton("Confirm");
		Hunter.addMouseListener(this);
		Mage.addMouseListener(this);
		Paladin.addMouseListener(this);
		Priest.addMouseListener(this);
		Warlock.addMouseListener(this);
		Confirm.addMouseListener(this);
		this.setLayout(new BorderLayout());
		this.add(BorderLayout.CENTER, Paladin);
		this.add(BorderLayout.CENTER, Mage);
		this.add(BorderLayout.CENTER, Priest);
		this.add(BorderLayout.CENTER, Warlock);
		this.add(BorderLayout.CENTER, Hunter);
		this.setSize(600, 600);
		this.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
