package graphics.container;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CursePanel {

	private static String[] messages = {"Do you hate everything?", 
			"Can you hear me?", 
			"I'm always watching.",
			"You did this.",
			"It's your fault.",
			"Join me.",
			"You can hear me.",
			"It's time you put them in their place.",
			"End everything."};
	
	public static int size = messages.length;
	
	public static JPanel createCurse(int id) {
		JPanel p = new JPanel();
		p.setBackground(Color.BLACK);
		
		String text = messages[id];
		JLabel l = new JLabel(text);
		l.setFont(new Font("monospace", Font.PLAIN, 16));
		l.setForeground(Color.RED);
		
		p.setLayout(new GridBagLayout());
		p.add(l);
		p.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		return p;
	}
}
