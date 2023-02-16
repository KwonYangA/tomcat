package com.mvc.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.mvc.dao.TestDao;

public class TestServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	Logger logger = Logger.getLogger(TestServlet.class);
	
	//사용자 정의 메소드(@ovrrideX)
	public void doService(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException	
	{
		logger.info("doService호출");
		logger.debug("doService호출");
		logger.fatal("doService호출");
		logger.warn("doService호출");
		logger.error("doService호출");
		TestDao tDao = new TestDao();
		List<Map<String, Object>> mList = tDao.getBookMember();
		tDao.getBookMember();
		//페이지 이동
		//페이지 이름 getMemberList.jsp
		//페이지의 물리적인 경로는 어디를 바라보고 있는가-> web.xml -> servlet-mapping[/test.do]
		//-> url-pattern
		// 경로명/요청을 처리하는 이름. do[뒤에 온 확장자는 의미없다-why? 해당 요청을 인터셉트해서 해당 업무를
		// 담당하는 클래스에서 연결(FrontController.java설계-각 업무별 XXXController필요함)]을 해야함
		// 페이지 처리는 JSP에게 맡김
		// 서블릿은 요청을 받아서 업무 담당자에게 연결(매핑, 매칭)
		// 이것을 어떻게 나눌 것인가?
		// 요청은 사용자 선택에 따라 결정
		// 결정에 대응하는 클래스를 FrontController가 연결
		// FrontController에서 실제 업무를 담당하는 XXXController에 전달할 때 
		// 요청객체와 응답객체 또한 전달이 되어야 한다
		// request.getParameter("XXX"); mem_id, mem_name, mem_address
		// HttpSession session = request.getSession(); //세션객체 생성
		// http 프로토콜이 비상태 프로토콜이므로 상태값을 관리하는 별도의 메카니즘 필요
		// 쿠키와 세션 <- List, Map <- 객체 배열 <- 배열 <- 원시형 타입
		// response.setContentType(); 마임타입을 결정한다
		// 서블릿인데 json, 서블릿인데 html, 서블릿인데 xml
		// response.sendRedirect("페이지 이름") -> 페이지 이동
		// 주소창이 바뀐다 -> 기존에 요청이 끊어지고 새로운 요청이 발생함
		// 그런데 마치 계속 유지 하고 있는 것처럼 보여져야 함 -> 그러니까 쿠키나 세션에 담아둔다 -> 왜냐하면 비상태 프로토콜
		// 쿠키 - 문자열 - 객체는 못 받음
		// 세션 - 캐시메모리 - 객체
		//아래 코드를 만나기 전까지는 http://localhost:9000/test/test.do
		//테스트 방법
		//방법1: TestServlet.java 소스에서 오른쪽 버튼 눌러 실행시킨다
		//방법2: 브라우저 주소창에 http://localhost:9000/test/getMemberList.jsp를 요청함
		res.sendRedirect("/test/getMemberList.jsp");
		//String cTime = tDao.testDate();
		//logger.info("today : " + cTime);		
		
		//String cTime = tDao.testDate();
		//logger.info("today : " + cTime);
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException	
	{
		doService(req, res);
	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, IOException	
	{
		doService(req, res);
	}
}
