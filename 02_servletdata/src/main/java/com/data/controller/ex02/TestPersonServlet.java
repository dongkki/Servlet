package com.data.controller.ex02;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/testperson.do")
public class TestPersonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Map<String, String> foodToImageMap = null;

	static {
		foodToImageMap = new HashMap<String, String>();
		foodToImageMap.put("���",
				"https://recipe1.ezmember.co.kr/cache/recipe/2019/03/30/ab3e044128060f6d78e9bb55c3f3e45e1.jpg");
		foodToImageMap.put("ī��",
				"https://static.wtable.co.kr/image-resize/production/service/recipe/260/4x3/8e1380e6-d21e-46c7-8b56-b26e6c836bb1.jpg");
		foodToImageMap.put("������",
				"http://img3.tmon.kr/cdn3/deals/2019/07/08/2228521646/original_2228521646_front_2ed86_1562548125production.jpg");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("testperson POST�� ȣ���!");

		// 1. encoding �������ֱ�
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		// 2.1 Parameter ó���ϱ� / getParameter
		Person person = makePerson(req);
		if (person == null) {
			// log ���� ������ 404 ���� �ױ�!
			String msg = (String) req.getAttribute(PageMoveServlet.MSG_ID);
			System.out.println("message : " + msg);
			System.out.println("name : " + req.getParameter("name"));
			System.out.println("null!");
			return;
		}
		System.out.println(person.toString());

		// 2.2 Ȥ�ö� Parameter �̸� �𸦶� �� : getParameterNames�� ����ض�!
		Iterator<String> iter = req.getParameterNames().asIterator();
		while (iter.hasNext()) {
			String key = iter.next();
			System.out.println("key : " + key);
			System.out.println(req.getParameter(key));
			;
		}

		// 3.���� ������ html �ۼ��ϱ�.
		String html = makeResponseHttp(req, person);

		// 4. ���������� ���� �� �����ϱ�
		// MIME ���� �� ���ڿ� ���ڵ� ����
		resp.setContentType("text/html;charset=utf-8");
		resp.getWriter().append(html);
	}

	private String makeResponseHttp(HttpServletRequest req, Person person) {
		StringBuffer sb = new StringBuffer();
		sb.append("<http>");
		sb.append("<body>");
		// �߰� �ڵ� ��� �� �ڸ�

		try {
			String msg = (String) req.getAttribute(PageMoveServlet.MSG_ID);
			Date date = (Date) req.getAttribute(PageMoveServlet.MSG_DATE);
			if (msg != null) {
				sb.append(String.format("<h3> ���� page msg : %s ", msg));
			}
			if (date != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
				String dateStr = sdf.format(date);
				sb.append(String.format("<h3> ���� page date : %s ", dateStr));
			}
		} catch (Exception e) {
		}

		sb.append("<h1> ���� �׽�Ʈ ��� </h1>");
		sb.append(String.format("<h3> �̸� : %s </h3>", person.getName()));
		sb.append(String.format("<h3> ���� : %d </h3>", person.getAge()));
		sb.append(String.format("<h3> ���� : %f </h3>", person.getHeight()));
		sb.append(String.format("<h3> �����ϴ� �� : %s ", person.getColor()));
		sb.append(String.format("<span style='color:%s'>������</span></h3>", person.getColor()));
		sb.append(String.format("<h3> �����ϴ� ���� : %s </h3>", person.getFoods()));

		List<String> list = person.getFoods();
		for (String foodname : list) {
			String url = foodToImageMap.get(foodname);
			if (url != null) {
				sb.append(String.format("<div><img src='%s' width='100px', height='100px'></div>", url));
			}
		}

		sb.append(String.format("<h3> �����ϴ� ���� : %s </h3>", person.getAnimal()));
		sb.append("</body>");
		sb.append("</http>");
		return sb.toString();
	}

	private Person makePerson(HttpServletRequest req) {
		try {
			Person person = new Person();
			person.setName(req.getParameter("name"));
			person.setAge(req.getParameter("age"));
			person.setHeight(req.getParameter("height"));
			person.setColor(req.getParameter("color"));
			person.setFoods(req.getParameterValues("foods"));
			person.setAnimal(req.getParameter("animal"));
			return person;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}

class Person {
	private String name;
	private int age;
	private double height;
	private String color;
	private List<String> foods;
	private String animal;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setAge(String age) {
		try {
			this.age = Integer.parseInt(age);
		} catch (Exception e) {
		}
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public void setHeight(String height) {
		try {
			this.height = Double.parseDouble(height);
		} catch (Exception e) {
		}
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public List<String> getFoods() {
		return foods;
	}

	public void setFoods(List<String> foods) {
		this.foods = foods;
	}

	public void setFoods(String[] parameterValues) {
		foods = Arrays.asList(parameterValues);
	}

	public String getAnimal() {
		return animal;
	}

	public void setAnimal(String animal) {
		this.animal = animal;
	}

	public Person(String name, int age, double height, String color, List<String> foods, String animal) {
		super();
		this.name = name;
		this.age = age;
		this.height = height;
		this.color = color;
		this.foods = foods;
		this.animal = animal;
	}

	public Person() {
		super();
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", height=" + height + ", color=" + color + ", foods=" + foods
				+ ", animal=" + animal + "]";
	}
}
