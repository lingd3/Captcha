package com.example;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageServlet extends HttpServlet {
	
	//步骤
	//1.定义BufferedImage对象
	//2.获得Graphics
	//3.通过Random长生随机验证码信息
	//4.使用Graphics绘制图片
	//5.记录验证码信息到session中
	//6.使用ImageIO输出图片

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedImage bi = new BufferedImage(68, 22, BufferedImage.TYPE_INT_RGB);
		Graphics g = bi.getGraphics();
		Color c = new Color(200, 150, 255);
		g.setColor(c);
		g.fillRect(0, 0, 68, 22);
				
		char[] op = "+-".toCharArray();
		Random r = new Random();
		int index, len1 = op.length;
		int result = 0, firstNum = 0, secondNum = 0;
		char operation = '0';
		for (int i = 0; i < 4; i++) {
			if (i != 1) index = r.nextInt(100);
			else index = r.nextInt(len1);
			
			g.setColor(new Color(r.nextInt(88), r.nextInt(188), r.nextInt(255)));
			if (i == 0) {
				g.drawString(index+"", (i*15)+3, 18);
				firstNum = index;
			}
			else if (i == 2) {
				g.drawString(index+"", (i*15)+3, 18);
				secondNum = index;
			}
			else if (i == 1) {
				g.drawString(op[index]+"", (i*15)+3, 18);
				operation = op[index];
			}
			else {
				g.drawString("=", (i*15)+3, 18);
			}
		}
		
		if (operation == '+') result = firstNum+secondNum;
		else if (operation == '-') result = firstNum-secondNum;
		else if (operation == '*') result = firstNum*secondNum;
		request.getSession().setAttribute("result", result);
		ImageIO.write(bi, "JPG", response.getOutputStream());
	}
	
}

















