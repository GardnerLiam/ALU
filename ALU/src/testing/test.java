package testing;
import javax.swing.JFrame;

import graphics.container.CursePanel;

public class test {
	public static void main(String[] args) {
	
		JFrame f = new JFrame();
		f.setSize(640,480);
		f.setLocationRelativeTo(null);
		f.setDefaultCloseOperation(3);
		f.setResizable(true);
		f.add(CursePanel.createCurse(2));
		f.setVisible(true);
	}
}
