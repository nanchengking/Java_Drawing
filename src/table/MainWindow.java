package table;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

public class MainWindow extends JFrame {

	private static Toole toole;
	private static DrawPanel drawPlace;
	
	int x1 = 0;
	int x2 = 0;
	int x3 = 0;
	int y1 = 0;
	int y2 = 0;
	int y3 = 0;

	private static MainWindow frame = new MainWindow();

	public void iniWindow() {
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		int a=screenSize.width/2-300;
		int b=screenSize.height/2-300;
		
		
		BorderLayout layout = new BorderLayout();
		frame.setLayout(layout);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(a,b, 600, 600);
		frame.setResizable(false);

		drawPlace = DrawPanel.getDrawPanel();

		toole = Toole.getToole();
		add(toole, BorderLayout.WEST);
		add(drawPlace, BorderLayout.CENTER);

		drawPlace.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				super.mousePressed(e);

				if (drawPlace.i == 0) {
					drawPlace.x1 = e.getX();
					drawPlace.y1 = e.getY();
					drawPlace.i++;

				} else if (drawPlace.i == 1) {
					drawPlace.x2 = e.getX();
					drawPlace.y2 = e.getY();
					drawPlace.repaint();
					drawPlace.i++;
				} else if (drawPlace.i == 2) {
					drawPlace.x3 = e.getX();
					drawPlace.y3 = e.getY();
					drawPlace.repaint();
					drawPlace.i++;
				}
			}
		});

	}

	public static void main(String args[]) {

		frame.iniWindow();
		frame.setTitle("¡ı ˆ«Â");
		frame.setVisible(true);
		
	  new Thread(new WelcomeWindow()).start();
	

	}

}
