package view.start;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class Message extends JDialog{
	JLabel message;
	public Message(JFrame f, String s) {
		super(f, "Illegal Action");
		message = new JLabel("<html> <body style='text-align: center; font-size: large;'>"+s+"</body></html>");
		this.add(message);
		this.setSize(100,100);
		this.setVisible(true);
	}
}
