package com.kh.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * ���ʹ� ������ ȣ��Ǳ� ���� �׸��� ���� �Ŀ� Stream�� Ư��ó���ϱ� ���� ���Ǵ� Ŭ����
 * �ַ� ���� ���� ��û ���� ���� ����/���� ó���� ������.
 */

@WebFilter(filterName = "EncodingFilter", urlPatterns = "/*")
public class EncodingFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// Filter�� �������� ���� ȣ��Ǵ� �޼ҵ�
		System.out.println(filterConfig.getFilterName()+"�� ������!!");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// Filter�� �����ϴ� �޼ҵ�
		
		// ��� ��û�� ���� UTF-8 ���ڵ� ����
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		httpRequest.setCharacterEncoding("UTF-8");
		System.out.println("UTF-8�� ���͸� ���� �Ϸ�!");
		
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.setCharacterEncoding("UTF-8");
		
		// ���Ͱ� �ִ°�� �ٸ� ���͸� ȣ���ϰ�,
		// ���Ͱ� ���� ��� ������ ȣ���ϴ� �ڵ�
		chain.doFilter(request, response);
		
//		response.getWriter().append("test");
		System.out.println("���� ���� �Ϸ�!!");
	}

	@Override
	public void destroy() {
		// Filter�� �Ҹ��Ҷ� ȣ��Ǵ� �޼ҵ�, ���� ȣ����� �� �𸥴�.
		System.out.println("���Ͱ� �Ҹ��!!");
	}
	
}
