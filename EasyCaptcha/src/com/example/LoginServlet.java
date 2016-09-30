package com.example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String piccode = (String)request.getSession().getAttribute("piccode");
		String checkcode = request.getParameter("checkcode");
		checkcode = checkcode.toUpperCase();
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		if (checkcode.equals(piccode)) {
			out.println("验证码输入正确！");
		}
		else {
			out.print("验证码输入错误！");
		}
		out.flush();
		out.close();
	}
	
}
