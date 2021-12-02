package com.data.controller.ex02;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * pagemove.do ��û�� ���� testperson.do�� �̵��ϴ� �ڵ� �ۼ� �ش� �������� Ư���� ���ڸ� �ְ�,
 * testperson.do ���ڸ� �޴� �������� �ڵ��Ǿ� ����
 * RequestDispatcher : ������ �̵��� ���� url�� �����ϴ� ��ü
 * forward() : ���� �������� �̵���Ű�� �ڵ�� pagemove.do -> testperson.do�� ��ȯ������ url�� ������ ����
 * include() : �������� �����ϴ� �ڵ�� pagemove.do -> testperson.do -> pagemove.do �� �̵��Ǿ� ������ ó���� ��
 */

@WebServlet("/pagemove.do")
public class PageMoveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String MSG_ID = "MSG";
	public static final String MSG_DATE = "DATE";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// request�� ���ڸ� �־� �ٸ��������� �����ϴ� ��

		// 1. request�� ���ڸ� �ֱ� setAttribute(String, Object)
		req.setAttribute(MSG_ID, "�ȳ��ϼ���?");
		req.setAttribute(MSG_DATE, new Date());
		// ���� id���� ���� ��� �޼��� ���� ���Ⱑ ��
		req.setAttribute(MSG_ID, "���� ȫ�浿 �Դϴ�.");

		// 2. request ���� �д� ���
		try {
			String msg = (String) req.getAttribute(MSG_ID);
			Date date = (Date) req.getAttribute(MSG_DATE);
			System.out.println(msg);
			System.out.println(date.toString());
			// �ش� �ڵ��� ���輺 : null point, type cast
		} catch (Exception e) {
		}

		// 3. ������ �����ϴ� ���
		// getRequestDispatcher ���� �� forward()�� ���� �̵�
		// getRequestDisapatcher("������Ī�ּ�||jsp�ּ�");

		RequestDispatcher rd = req.getRequestDispatcher("testperson.do");
		rd.forward(req, resp); // ���� �������� �̵� �Ǵ� �ڵ�!!
	}

//	// ��� �������� �Ϻη� �̾����� �ʴ´�!!
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		doPost(req, resp);
//	}

}
