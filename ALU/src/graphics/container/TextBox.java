package graphics.container;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import numerics.ALU;
import tools.StringToBase;

public class TextBox extends JTextPane {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7615562889238941283L;

	int width, height;
	SimpleAttributeSet attribs;

	int activeGroup = 0;
	
	ALU alu;

	public TextBox(int width, int height) {
		
		alu = new ALU();
		
		this.width = width;
		this.height = height;

		this.attribs = new SimpleAttributeSet();

		StyleConstants.setAlignment(this.attribs, StyleConstants.ALIGN_RIGHT);
		StyleConstants.setFontSize(this.attribs, 81);

		this.setPreferredSize(new Dimension(this.width - 30, this.height));
		this.setEnabled(false);
		this.setDisabledTextColor(Color.BLACK);

		this.setParagraphAttributes(attribs, true);

		this.setBackground(Color.WHITE);
		this.setForeground(Color.BLACK);

	}

	public void setNumber(String text) {
		if (activeGroup == 1) {
			this.setText(text);
			alu.loadFirstNumber(text);
			activeGroup = 0;
		}
		if (this.getText().length() <= 1) {
			this.setText(text);
			alu.loadFirstNumber(text);
		} else if (this.getText().length() == 2) {
			this.setText(this.getText().substring(0,2) + text);
			alu.loadSecondNumber(text);
		} else {
			this.setText(this.getText().substring(0,2) + text);
			alu.loadSecondNumber(text);
		}
	}

	public void setOperator(String text) {
		if (activeGroup == 0) {
			if (this.getText().length() > 2) {
				this.setText(this.getText().substring(0, 1) + text + this.getText().substring(2));
				alu.loadOpCode(text.charAt(0));
			} else if (this.getText().length() >= 1 && this.getText().length() <= 2) {
				this.setText(this.getText().substring(0, 1) + text);
				alu.loadOpCode(text.charAt(0));
			}
			System.out.println(this.alu.getOpCode());
		}
	}

	public void evaluate() {
		alu.printData();
		String result = alu.evaluate();
		activeGroup = 1;
		this.setText(StringToBase.fromBinary(result));
		alu.reset();
	}
}
