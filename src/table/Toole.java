package table;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Toole extends JPanel {


	private static JPanel list = new JPanel();

	private static JPanel buttons = new JPanel();

	static JTextField x_1 = new JTextField();
	static JTextField x_2 = new JTextField();
	static JTextField x_3 = new JTextField();
	static JTextField y_1 = new JTextField();
	static JTextField y_2 = new JTextField();
	static JTextField y_3 = new JTextField();

	static final JButton generate = new JButton("生成");
	static final JButton reset = new JButton("重置");
	static final JButton symmetric_X = new JButton("X轴对称");
	static final JButton symmetric_Y = new JButton("Y轴对称");
	static final JButton rotate_X = new JButton("旋转");
	static final JButton amplify_big = new JButton("比例变换");

	static JTextField rotateX = new JTextField();

	static JTextField scale = new JTextField();

	public static void setListeners() {
		iniWindow();
		generate.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
							
				DrawPanel drawPanel = DrawPanel.getDrawPanel();
				
				if (x_1.getText().equals("") || x_2.getText().equals("")
						|| x_3.getText().equals("") || y_1.getText().equals("")
						|| y_2.getText().equals("") || y_3.getText().equals("")) {
					return;
				}

				else {
					drawPanel.x1 = Integer.parseInt(x_1.getText().toString())+drawPanel.W;
					drawPanel.x2 = Integer.parseInt(x_2.getText().toString())+drawPanel.W;
					drawPanel.x3 = Integer.parseInt(x_3.getText().toString())+drawPanel.W;
					drawPanel.y1 = -Integer.parseInt(y_1.getText().toString())+drawPanel.H;
					drawPanel.y2 =- Integer.parseInt(y_2.getText().toString())+drawPanel.H;
					drawPanel.y3 = -Integer.parseInt(y_3.getText().toString())+drawPanel.H;
					drawPanel.isChange = false;
					drawPanel.i=3;
					drawPanel.isGenerate=true;
					drawPanel.repaint();
				}

			}
		});
		reset.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				DrawPanel drawPanel = DrawPanel.getDrawPanel();
				if (x_1.getText() != null && x_2.getText() != null
						&& x_3.getText() != null && y_1.getText() != null
						&& y_2.getText() != null && y_3.getText() != null) {
					x_1.setText(null);
					x_2.setText(null);
					x_3.setText(null);
					y_1.setText(null);
					y_2.setText(null);
					y_3.setText(null);
					drawPanel.i = 0;
					drawPanel.isChange = false;
					drawPanel.isGenerate=false;
					drawPanel.repaint();
					drawPanel.x3 = -1;
					drawPanel.x2 = -1;

				}
			}
		});
		amplify_big.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				DrawPanel drawPanel = DrawPanel.getDrawPanel();
				if (scale.getText() != null) {
					drawPanel.scale = Double.parseDouble(scale.getText()
							.toString());

					drawPanel.change = drawPanel.amplify_big;
					drawPanel.isChange = true;
					drawPanel.repaint();
				}

			}
		});
		symmetric_X.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				DrawPanel drawPanel = DrawPanel.getDrawPanel();
				drawPanel.change = drawPanel.symmetric_X;
				drawPanel.isChange = true;
				drawPanel.repaint();

			}
		});
		symmetric_Y.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				DrawPanel drawPanel = DrawPanel.getDrawPanel();
				drawPanel.change = drawPanel.symmetric_Y;
				drawPanel.isChange = true;
				drawPanel.repaint();

			}
		});
		rotate_X.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				DrawPanel drawPanel = DrawPanel.getDrawPanel();
				if (rotateX.getText() != null) {
					drawPanel.angle = (Double.parseDouble(rotateX.getText()
							.toString())) / 180 * Math.PI;

					drawPanel.change = drawPanel.rotate;
					drawPanel.isChange = true;
					drawPanel.repaint();
				}

			}
		});

	}

	public Toole() {
		setListeners();
	}

	public static void iniWindow() {

		GridLayout listLayout = new GridLayout(3, 4);
		GridLayout buttonsLayout = new GridLayout(4, 2);

		list.setLayout(listLayout);
		buttons.setLayout(buttonsLayout);

		JLabel x1 = new JLabel("X1=");
		JLabel y1 = new JLabel("Y1=");
		JLabel x2 = new JLabel("X2=");
		JLabel y2 = new JLabel("Y2=");
		JLabel x3 = new JLabel("X3=");
		JLabel y3 = new JLabel("Y3=");

		list.add(x1);
		list.add(x_1);
		list.add(y1);
		list.add(y_1);
		list.add(x2);
		list.add(x_2);
		list.add(y2);
		list.add(y_2);
		list.add(x3);
		list.add(x_3);
		list.add(y3);
		list.add(y_3);
		list.setSize(50, 30);

		buttons.add(generate);
		buttons.add(reset);
		buttons.add(symmetric_X);
		buttons.add(symmetric_Y);

		buttons.add(rotateX);
		buttons.add(rotate_X);

		buttons.add(scale);
		buttons.add(amplify_big);
		buttons.setSize(50, 30);

	}

	static Font font = new Font("ScanSerif", Font.ITALIC, 23);
	static Toole toole = new Toole();

	public static Toole getToole() {

		toole.setFont(font);
		toole.setLayout(new BorderLayout());
		toole.add(list, BorderLayout.NORTH);
		toole.add(buttons, BorderLayout.CENTER);
		return toole;
	}
}
