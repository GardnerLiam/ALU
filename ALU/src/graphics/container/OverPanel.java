package graphics.container;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class OverPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7204570011379236924L;

	public JPanel textPanel;
	
	public JPanel inputs;
	public JPanel buttonsPanel;
	public JPanel tablePanel;
	
	
	public OverPanel() {
		setLayout(new GridLayout(2,1));
		
		textPanel = new JPanel();
		inputs = new JPanel();
		buttonsPanel = new JPanel();
		tablePanel = new JPanel();
		
		buttonsPanel.setLayout(new GridLayout(1,2));
		
		add(textPanel);
		add(inputs);
		inputs.add(buttonsPanel);
		inputs.add(tablePanel,BorderLayout.WEST);
	}
}
