package com.kh.ajax.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kh.ajax.model.vo.User;

@WebServlet("/jqAjax5.do")
public class JqueryAjaxServlet5 extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<User> list = new ArrayList<>();
		
		list.add(new User(1, "홍길동", 21, '남'));
		list.add(new User(2, "박길순", 25, '여'));
		list.add(new User(3, "리중딱", 31, '남'));
		list.add(new User(4, "서예지", 51, '여'));

		Gson gson = new Gson();
		
		System.out.println(gson.toJson(list.get(1)));
		System.out.println(gson.toJson(list));
		System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(list));
		
		resp.setContentType("application/json; charset=UTF-8");
		
		new GsonBuilder().setPrettyPrinting().create().toJson(list, resp.getWriter());
	}
}
