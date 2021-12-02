package com.kh.ajax.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.kh.ajax.model.vo.User;

@WebServlet("/jqAjax4.do")
public class JqueryAjaxServlet4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<User> list	= new ArrayList<User>();
		
		list.add(new User(1, "박박나나", 32, '남'));
		list.add(new User(2, "김형석", 27, '여'));
		list.add(new User(3, "황동수", 26, '남'));
		list.add(new User(4, "윤승혁", 21, '여'));
		
		JSONArray array = new JSONArray();
		
		for(User u : list) {
			JSONObject obj = new JSONObject();
			obj.put("no", u.getNo());
			obj.put("name", u.getName());
			obj.put("age", u.getAge());
			obj.put("gender", u.getGender() + "");
			array.add(obj);
		}
		
		System.out.println(array.toJSONString());
		
		resp.setContentType("application/json; charset=UTF-8");
		resp.getWriter().print(array);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}




















