package com.kh.mvc.board.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/board/fileDown")
public class BoardFileDownServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 0. 파라메터에서 파일 읽어오기
		String oriname = req.getParameter("oriname");
		String rename = req.getParameter("rename");
		System.out.println(oriname+" : "+rename);
		
		// 1. 실제경로 찾아와서 파일 생성하기
		String path = getServletContext().getRealPath("/resources/upload/board");
		File downFile = new File(path, rename);
		
		// 2. 파일 읽어오는 Stream 생성
		FileInputStream fis = new FileInputStream(downFile);
 		BufferedInputStream bis = new BufferedInputStream(fis);
		
 		// 3. 파일 보내는 Stream 생성
 		BufferedOutputStream bos = new BufferedOutputStream(resp.getOutputStream());
 		
 		// 4. http 헤더만들기 - file 전송용, 파일 이름 인코딩 셋팅하기
 	   	String header = req.getHeader("user-agent");
    	boolean isMSIE = header.contains("MSIE") || header.contains("Trident");
    	String downName = null;
    	// 브라우저에 따른 인코딩 방식 처리
    	if (isMSIE) {
    		downName = URLEncoder.encode(oriname, "UTF-8").replaceAll("\\+", "%20");
    	} else {    		
    		downName = new String(oriname.getBytes("UTF-8"), "ISO-8859-1");
    	}
    	
    	// octec-stream은 문자열 처리가 아닌 binary 파일 전송을 뜻함
    	resp.setContentType("application/octec-stream");
    	// header는 파일 첨부를 뜻하여 다운로드 창이 띄어지도록 변경됨
    	resp.setHeader("Content-Disposition", "attachment;filename=" + downName);
    	
    	// 5. 파일전송하기
    	int read = 0;
    	while((read = bis.read()) != -1) {
    		bos.write(read);
    	}
    	
    	bis.close();
    	bos.close();
	}
}






