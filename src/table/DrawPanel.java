package table;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class DrawPanel extends JPanel {

	public static final int W = 206;
	public static final int H = 280;

	 int x1=-1;
	 int x2=-1;
	 int y1 = -1;
	 int y2 = -1;
	 int x3 = -1;
	 int y3 = -1;
	 int i = 0;
	Toole toole = Toole.getToole();

	static boolean isGenerate=false;
	static boolean isChange = false;
	static double[][] change = { { 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } };

	double angle = 0.0;
	static double scale = 1;

	static double[][] symmetric_X = { { 1, 0, 0 }, { 0, -1, 0 }, { 0, 0, 1 } };
	static double[][] symmetric_Y = { { -1, 0, 0 }, { 0, 1, 0 }, { 0, 0, 1 } };
	// 对矩阵初始化，真正使用并不是这个
	double[][] amplify_big = { { 1, 0, 0 }, { 0, 1, 0 }, { 0, 0, 1 } };
	double[][] rotate = { { 1, 1, 0 }, { 1, 1, 0 }, { 0, 0, 1 } };;

	private static MyArray operation(double[][] a, double[][] b) {

		MyArray result = new MyArray();
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < b[0].length; j++) {
				result.M[i][j] = 0;
				for (int k = 0; k < b.length; k++) {
					result.M[i][j] += a[i][k] * b[k][j];
				}
			}
		}
		return result;
	}

	@Override
	public synchronized void  paintComponent(Graphics g) {
		super.paintComponent(g);
		// 使用这个工具使得画面的画笔起了作用
		setBackground(Color.WHITE);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setStroke(new BasicStroke(3f));
		g.setFont(getFont().deriveFont(Font.ITALIC, 20f));

		g.drawString("(0),(0)", W, H);
		// 用来画箭头
		int[] xPoints_X = { 2 * W - 3, 2 * W + 8, 2 * W - 3 };
		int[] yPoints_X = { H -4, H + 1, H +4 };

		int[] xPoints_Y = { W-5, W , W + 5 };
		int[] yPoints_Y = { 10, 0, 10 };
		// 画出坐标系形状
		g.setColor(Color.BLUE);
		Font font = new Font("", Font.LAYOUT_LEFT_TO_RIGHT, 20);
		int mX = getWidth();
		int mY = getHeight();
		g.drawLine(W, 0, W, 2*H);
		g.drawLine(0,H,2*W, H);
		g.fillPolygon(xPoints_X, yPoints_X, yPoints_X.length);
		g.drawString("X", 2 * W - 10, H-3);
		g.drawString("max:200", 2 * W-70, H+30);
		g.fillPolygon(xPoints_Y, yPoints_Y, yPoints_Y.length);
		g.drawString("Y   max:200", W - 25, 20);
		g.fillOval(W-5, H-5, 10, 10);
		// 放大矩阵
		amplify_big[0][0] = scale;
		amplify_big[1][1] = scale;
		// 旋转矩阵
		rotate[0][0] = Math.cos(angle);
		rotate[0][1] = Math.sin(angle);
		rotate[1][0] = -Math.sin(angle);
		rotate[1][1] = Math.cos(angle);
		// 原矩阵
		double[][] origination = { { x1 - DrawPanel.W, -y1 + DrawPanel.H, 1 },
				{ x2 - DrawPanel.W, -y2 + DrawPanel.H, 1 },
				{ x3 - DrawPanel.W, -y3 + DrawPanel.H, 1 } };
		// 变化后的结果矩阵
		double[][] variant = operation(origination, change).M;
		// 准备画线
		g.setColor(Color.BLACK);
		int[] x = { x1, x2, x3 };
		int[] y = { y1, y2, y3 };
		if (x2 != -1 && y2 != -1) {
			g.drawString("P1", x1, y1);
			g.drawString("P2", x2, y2);
			g.drawLine(x1, y1, x2, y2);
			if(isGenerate==false){
				toole.x_1.setText(x1 - DrawPanel.W + "");
				toole.x_2.setText(x2 - DrawPanel.W + "");
				toole.x_3.setText(x3 - DrawPanel.W + "");
				toole.y_1.setText(-y1 + DrawPanel.H + "");
				toole.y_2.setText(-y2 + DrawPanel.H + "");
				toole.y_3.setText(-y3 + DrawPanel.H + "");			
			}		
			isGenerate=false;
		}
		// 画三角形
		if (x3 != -1 && y3 != -1) {
			g.drawString("P3", x3, y3);
			g.drawPolygon(x, y, x.length);
			System.out.println(x3+"---"+y3);
			System.out.println(x2+"---"+y2);
			System.out.println(x1+"---"+y1);
		}
		// 变换后的矩阵
		if (isChange) {
			System.out.println("angle is:" + drawPanel.angle
					+ (int) variant[0][0]);
			int[] changeX = { (int) variant[0][0] + DrawPanel.W,
					(int) variant[1][0] + DrawPanel.W,
					(int) variant[2][0] + DrawPanel.W };

			int[] changeY = { (int) -variant[0][1] + DrawPanel.H,
					(int) -variant[1][1] + DrawPanel.H,
					(int) -variant[2][1] + DrawPanel.H };

			g.drawPolygon(changeX, changeY, changeY.length);
			isChange = false;
		}

	}

	static DrawPanel drawPanel = new DrawPanel();

	public static DrawPanel getDrawPanel() {

		return drawPanel;
	}

}
