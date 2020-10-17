package com.zzc.excise.tools;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class CreateVerificationCodeImage {
	private static final int WIDTH = 100;	// 设置验证码图片的宽度
	private static final int HEIGHT = 30;	// 设置验证码图片高度
	private static final int LENGTH = 4;	// 设置验证码长度
	private static final int LINECOUNT = 20; // 干扰线的数目
	
	private static final String str = "23456789"
					+"ABCDEFGHJKLMNPQRSTUVWXYZ" + "abcdefghijklmnpqrstuvwxyz";
	private static Random random = new Random();
	
	//通过随机数去字符库中的字符组合成4为验证码
	public String createCode () {
		String code = "";
		for(int i = 0;i < LENGTH; i++) {
			char c = str.charAt(random.nextInt(str.length()));
			code = code + c;
		}
		return code;
	}
	
	//获取不同颜色
	public Color getColor() {
		return new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
	}
	
	//获取字体样式
	public Font getFont() {
		return new Font("Fixedsys" ,Font.CENTER_BASELINE,22);
	}
	
	//绘制字符
	public void drawChar(Graphics g, String code) {
		g.setFont(getFont());
		for(int i=0;i<LENGTH;i++) {
			char c = code.charAt(i);
			g.setColor(getColor());
			g.drawString(c+"", 20*i+10, 20);
		}
	}
	
	//绘制随机线
	public void drawLine(Graphics g) {
		int x = random.nextInt(WIDTH);
		int y = random.nextInt(HEIGHT);
		int x1 = random.nextInt(13);
		int y1 = random.nextInt(15);
		g.setColor(getColor());
		g.drawLine(x, y, x+x1, y+y1);
	}
	
	//绘制验证码图片
	public BufferedImage CreateImage(String code) {
		//绘制画笔
		BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
		Graphics g = image.getGraphics();
		// 设置背景颜色并绘制矩形背景
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		//验证码绘制
		drawChar(g, code);
		// 随机线的绘制
		for(int i=0;i< LINECOUNT;i++) {
			drawLine(g);
		}
		//绘制图片
		g.dispose();
		//返回生成图片
		return image;
	}
	
}
