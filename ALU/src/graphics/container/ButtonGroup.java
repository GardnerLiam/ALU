package graphics.container;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class ButtonGroup extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5866957610088910261L;

	int buttonAmount;

	int rows, cols;
	int type;

	private int selected;

	public JToggleButton[] buttons;

	public int[] untoggleables = new int[0];

	String[] buttonText;

	Font font;

	ActionListener action;

	TextBox tb;

	public ButtonGroup(int buttonAmount, int rows, int cols, int type, String[] buttonText) {
		this.buttonAmount = buttonAmount;

		this.rows = rows;
		this.cols = cols;
		this.type = type;
		this.buttonText = buttonText;

		this.buttons = new JToggleButton[this.buttonAmount];

		this.action = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				System.out.println(((JToggleButton) e.getSource()).getSize());
				if (type == 1) {
					String text = ((JToggleButton) e.getSource()).getText();
					if (((JToggleButton) e.getSource()).getText().equals("=")) {
						tb.evaluate();
					} else {
						tb.setOperator(text);
					}
				}

				buttons[selected].setSelected(true);
				for (int i = 0; i < buttonAmount; i++) {

					if (i != selected) {
						buttons[i].setSelected(false);
					}

				}
			}
		};

		for (int i = 0; i < this.buttonAmount; i++) {
			this.buttons[i] = new JToggleButton(this.buttonText[i]);
			this.buttons[i].setName(Integer.toString(i));
			this.buttons[i].addActionListener(this.action);
			this.add(this.buttons[i]);
		}

		this.setLayout(new GridLayout(this.rows, this.cols));
	}

	public void setUntoggleables(int[] index) {
		this.untoggleables = new int[index.length];
		for (int i = 0; i < index.length; i++) {
			this.untoggleables[i] = index[i];
		}
	}

	public void setFont(String fontName, int size) {
		this.font = new Font(fontName, Font.PLAIN, size);
		for (int i = 0; i < this.buttonAmount; i++) {
			this.buttons[i].setFont(this.font);
		}
	}

	public void setTextUpdator(TextBox tb) {
		this.tb = tb;
	}
}
