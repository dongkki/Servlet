package com.kh.ajax.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.kh.ajax.model.vo.User;

@WebServlet("/jqAjax3.do")
public class JqueryAjaxServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		
		String userNo = req.getParameter("userNo");
		System.out.println("userNo : " + userNo);
		
		JSONObject object = new JSONObject();
		
		User user = new User(Integer.parseInt(userNo), "최홍구", 35, '남');
		
		object.put("no", user.getNo());
		object.put("name", user.getName());
		object.put("age", "" + user.getAge());
		object.put("gender", "" + user.getGender());
		
		System.out.println(object.toJSONString());
		
		resp.setContentType("application/json; charset=UTF-8");
		resp.getWriter().print(object);
		resp.getWriter().flush();
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
