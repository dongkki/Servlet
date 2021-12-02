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
		foodToImageMap.put("콩불",
				"https://recipe1.ezmember.co.kr/cache/recipe/2019/03/30/ab3e044128060f6d78e9bb55c3f3e45e1.jpg");
		foodToImageMap.put("카레",
				"https://static.wtable.co.kr/image-resize/production/service/recipe/260/4x3/8e1380e6-d21e-46c7-8b56-b26e6c836bb1.jpg");
		foodToImageMap.put("마라탕",
				"http://img3.tmon.kr/cdn3/deals/2019/07/08/2228521646/original_2228521646_front_2ed86_1562548125production.jpg");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("testperson POST로 호출됨!");

		// 1. encoding 설정해주기
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");

		// 2.1 Parameter 처리하기 / getParameter
		Person person = makePerson(req);
		if (person == null) {
			// log 띄우고 페이지 404 띄우고 죽기!
			String msg = (String) req.getAttribute(PageMoveServlet.MSG_ID);
			System.out.println("message : " + msg);
			System.out.println("name : " + req.getParameter("name"));
			System.out.println("null!");
			return;
		}
		System.out.println(person.toString());

		// 2.2 혹시라 Parameter 이름 모를때 팁 : getParameterNames를 사용해라!
		Iterator<String> iter = req.getParameterNames().asIterator();
		while (iter.hasNext()) {
			String key = iter.next();
			System.out.println("key : " + key);
			System.out.println(req.getParameter(key));
			;
		}

		// 3.응답 페이지 html 작성하기.
		String html = makeResponseHttp(req, person);

		// 4. 응답페이지 설정 및 전달하기
		// MIME 셋팅 및 문자열 인코딩 설정
		resp.setContentType("text/html;charset=utf-8");
		resp.getWriter().append(html);
	}

	private String makeResponseHttp(HttpServletRequest req, Person person) {
		StringBuffer sb = new StringBuffer();
		sb.append("<http>");
		sb.append("<body>");
		// 추가 코드 들어 갈 자리

		try {
			String msg = (String) req.getAttribute(PageMoveServlet.MSG_ID);
			Date date = (Date) req.getAttribute(PageMoveServlet.MSG_DATE);
			if (msg != null) {
				sb.append(String.format("<h3> 기존 page msg : %s ", msg));
			}
			if (date != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
				String dateStr = sdf.format(date);
				sb.append(String.format("<h3> 기존 page date : %s ", dateStr));
			}
		} catch (Exception e) {
		}

		sb.append("<h1> 취향 테스트 결과 </h1>");
		sb.append(String.format("<h3> 이름 : %s </h3>", person.getName()));
		sb.append(String.format("<h3> 나이 : %d </h3>", person.getAge()));
		sb.append(String.format("<h3> 신장 : %f </h3>", person.getHeight()));
		sb.append(String.format("<h3> 좋아하는 색 : %s ", person.getColor()));
		sb.append(String.format("<span style='color:%s'>■■■■■</span></h3>", person.getColor()));
		sb.append(String.format("<h3> 좋아하는 음식 : %s </h3>", person.getFoods()));

		List<String> list = person.getFoods();
		for (String foodname : list) {
			String url = foodToImageMap.get(foodname);
			if (url != null) {
				sb.append(String.format("<div><img src='%s' width='100px', height='100px'></div>", url));
			}
		}

		sb.append(String.format("<h3> 좋아하는 동물 : %s </h3>", person.getAnimal()));
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
