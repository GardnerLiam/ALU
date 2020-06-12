package graphics.container;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JToggleButton;

import numerics.FourBit;

public class NumberGroup extends ButtonGroup{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2036537091419632712L;
	
	
	int selected = 0;
	int isNegative = 0; 
	
	public NumberGroup() {
		super(12, 4, 3, 0, new String[] {"-","+","c", "6", "7", "8", "3", "4", "5", "0", "1", "2" });
		
		buttons[5].setEnabled(false);
		buttons[1].setSelected(true);
		buttons[1].setEnabled(false);
		
		ActionListener cs = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tb.alu.reset();
				tb.setText("");
			}
		};

		
		ActionListener numericAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				selected = Integer.parseInt(((JToggleButton) e.getSource()).getName());
				String text = ((JToggleButton) e.getSource()).getText();
				if (isNegative == 1) {
					text = "-" + text;
				}else {
					if (text.charAt(0) == '-'){
						text = text.substring(1);
					}
				}
				tb.setNumber(text);
				buttons[selected].setSelected(true);
				for (int i = 2; i < buttonAmount; i++) {					
					if (i != selected) {
						buttons[i].setSelected(false);
					}
					
				}
			}
		};
		
		ActionListener signAction = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String s = ((JToggleButton) e.getSource()).getText();
				
				if (tb.getText().length() == 4) {
					tb.setText(new FourBit(tb.getText()).Complement().toString());
				}
				
				isNegative = (s.equals("-"))?1:0;
				buttons[(isNegative==1)?0:1].setSelected(true);
				
				buttons[5].setEnabled(isNegative == 1);
				
				buttons[(isNegative==1)?0:1].setEnabled(false);
				buttons[isNegative].setSelected(false);
				buttons[isNegative].setEnabled(true);
			}
		};
		
		for (int i = 0; i < this.buttonAmount; i++) {
			for (ActionListener al : buttons[i].getActionListeners().clone()) {
				buttons[i].removeActionListener(al);
			}
			
			if (i==2) {
				buttons[i].addActionListener(cs);
			}
			
			else if (i > 2) {
				buttons[i].addActionListener(numericAction);
			}else {
				buttons[i].addActionListener(signAction);
			}
		}
	}

}
