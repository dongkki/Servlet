package com.data.controller.ex02;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * sendRedirect��?
 * ������ URL�� Ư�� �������� �������ϴ� ������� ������ Post �Ķ���Ͱ� ���µǰ�
 * ��ĵ� get���� �����Ǿ� ���޵Ǵ� ���.
 * ������ ��û(request) ��ü, ����(response) ��ü ��� �ʱ�ȭ �� ���·� ���޵Ǿ�
 * ������ �������� �ʴ´�!!
 * 
 * sendRedirect vs forward
 * sendRedirect : ���� ������ �̵��� �ʿ��Ҷ� ���
 * 				  ex) �߸��� ���� ���޵� ��� -> 404 �������� �̵��ϴ� �ܼ��� ���
 * forward : ������ ������ ������ �� ���� ������� ���� ��
 * 			 ex) login -> admin(vip)�� ��� �߰����� ���ڸ� ó�� �� ��
 * 
 */

@WebServlet("/sendredirect.do")
public class SendRedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("sendredirect.do ȣ��");
		req.setAttribute(PageMoveServlet.MSG_ID, "redirect���� ���� �޼����Դϴ�");

		resp.sendRedirect("testperson.do");
	}

}
