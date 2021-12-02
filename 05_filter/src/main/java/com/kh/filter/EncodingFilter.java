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
 * 필터는 서블릿이 호출되기 전과 그리고 응답 후에 Stream을 특수처리하기 위해 사용되는 클래스
 * 주로 응답 값과 요청 값에 대해 사전/사후 처리를 진행함.
 */

@WebFilter(filterName = "EncodingFilter", urlPatterns = "/*")
public class EncodingFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// Filter가 생성되자 마자 호출되는 메소드
		System.out.println(filterConfig.getFilterName()+"가 생성됨!!");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// Filter를 수행하는 메소드
		
		// 모든 요청에 대해 UTF-8 인코딩 수행
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		httpRequest.setCharacterEncoding("UTF-8");
		System.out.println("UTF-8로 필터링 수행 완료!");
		
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		httpResponse.setCharacterEncoding("UTF-8");
		
		// 필터가 있는경우 다른 필터를 호출하고,
		// 필터가 끝난 경우 서블릿을 호출하는 코드
		chain.doFilter(request, response);
		
//		response.getWriter().append("test");
		System.out.println("필터 동작 완료!!");
	}

	@Override
	public void destroy() {
		// Filter가 소멸할때 호출되는 메소드, 언제 호출될지 잘 모른다.
		System.out.println("필터가 소멸됨!!");
	}
	
}
