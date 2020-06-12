package graphics;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

import graphics.container.ButtonGroup;
import graphics.container.CursePanel;
import graphics.container.NumberGroup;
import graphics.container.OverPanel;
import graphics.container.TextBox;

public class Window extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2863164495410704033L;

	OverPanel op;

	ButtonGroup numbers;
	ButtonGroup operations;
	
	TextBox tb;
	
	private Timer timer;

	private int loops;
	private Random r;
	private boolean flipped = false;
	
	private JPanel curse;
	
	public Window(int width, int height, String title) {
		timer = new Timer(40, this);
		loops = 0;
		r = new Random();
		
		setTitle(title);
		setSize(width, height);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(3);
		setResizable(true);

		op = new OverPanel();
		tb = new TextBox(width, 100);
		
		numbers = new NumberGroup();
		operations = new ButtonGroup(6, 3, 2, 1, new String[] { "+", "-", "|", "&", "^", "="});
		
		//numbers.buttons[2].setEnabled(false);
		numbers.setUntoggleables(new int[] {2});
		
		numbers.setTextUpdator(tb);
		operations.setTextUpdator(tb);

		numbers.setFont("Arial", 40);
		operations.setFont("Arial", 40);
		op.textPanel.setSize(width, 150);
		op.textPanel.add(tb);
		
		op.buttonsPanel.add(numbers);
		op.buttonsPanel.add(operations);

		add(op);
	}

	public void start() {
		setVisible(true);
		timer.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		loops++;
		System.out.println(loops);
		
		int minSeconds = 30*1000/40;
		int maxSeconds = 120*1000/40;
		
		//r.nextInt(maxSeconds - minSeconds + 1)+minSeconds
		if (!flipped && loops%(r.nextInt(maxSeconds - minSeconds + 1)+minSeconds) == 0) {
			System.out.println("HERE");
			this.remove(op);
			curse = CursePanel.createCurse(r.nextInt(CursePanel.size-1));
			this.add(curse);
			this.setSize(801,600);
			System.out.println(flipped);
			loops = 0;
			flipped = true;
		}
		else if (loops != 0 && loops%5 == 0 && flipped) {
			System.out.println("Resetting");
			this.remove(curse);
			this.add(op);
			this.setSize(800,600);
			flipped = false;
		}
	}
}
