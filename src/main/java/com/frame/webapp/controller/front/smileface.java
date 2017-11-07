package com.frame.webapp.controller.front;
import java.awt.*;

import java.awt.geom.*;
import javax.swing.*;
//打印笑脸
public class smileface {
	public static void main(String[]args){
		DrawFrame f = new DrawFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}
class DrawFrame extends JFrame{
	public DrawFrame(){
		Toolkit tl = Toolkit.getDefaultToolkit();
		Dimension ds = tl.getScreenSize();
		int width = ds.width;
		int height = ds.height;
		setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
		setLocation(width/2-DEFAULT_WIDTH/2,height/2-DEFAULT_WIDTH/2);
		add(new DrawPanel());
	}
	public static int DEFAULT_WIDTH = 500;
	public static int DEFAULT_HEIGHT = 550;
}
class DrawPanel extends JPanel{
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
	double radius = 180;
	Ellipse2D face = new Ellipse2D.Double();/**定义圆face,mouth1,mouth2,eye1,eye2*/
	Ellipse2D mouth1 = new Ellipse2D.Double();
	Ellipse2D mouth2 = new Ellipse2D.Double();
	Ellipse2D eye1 = new Ellipse2D.Double();
	Ellipse2D eye2 = new Ellipse2D.Double();
	face.setFrameFromCenter(middle, middle, middle-radius, middle-radius);/**画脸*/
	g2.setPaint(Color.YELLOW);
	g2.draw(face);
     g2.fill(face);/**填黄色*/
     mouth1.setFrameFromCenter(middle,middle,middle+140,middle+140);/**用两个圆叠加得到圆弧的嘴*/
     g2.setPaint(Color.BLACK);
     g2.draw(mouth1);
     g2.fill(mouth1);
     mouth2.setFrameFromCenter(middle,middle-Y,middle+2*X,middle+X+Y);    
     g2.setPaint(Color.YELLOW);
     g2.draw(mouth2);
     g2.fill(mouth2);
     eye1.setFrameFromCenter(middle-X,middle-2*Y,middle-X+Y,middle-Y);/**画两只眼睛*/
     g2.setPaint(Color.BLACK);
     g2.draw(eye1);
     g2.fill(eye1);
     eye2.setFrameFromCenter(middle+X,middle-2*Y,middle+X+Y,middle-Y);
     g2.setPaint(Color.BLACK);
     g2.draw(eye2);
     g2.fill(eye2);
     String s = new String("Smiling...");
	Font f = new Font("Serif",Font.BOLD+Font.ITALIC,30);
	g2.setPaint(Color.BLUE);
	g2.setFont(f);
	  g2.drawString(s, 180, 480);	
	}
	public static final int middle=250;
	public static final int X=80;
	public static final int Y=30;
}
