<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="user.UserDTO"%>
<%@ page import="user.UserDAO"%>
<%@ page import="java.io.PrintWriter"%>

<!-- 로그인 처리하는 부분 -->
<%
	request.setCharacterEncoding("UTF-8");
	
	String email = null;
	String password = null;
	
	if(request.getParameter("email") != null) {
		email = (String) request.getParameter("email");
	}
	
	if(request.getParameter("password") != null) {
		password = (String) request.getParameter("password");
	}
	
	UserDAO userDAO = new UserDAO();
	// DAO에서 email과 password를 result에 담기
	int result = userDAO.login(email, password);
	
	// 로그인 성공 여부
	if(result == 1) {	// email에 세션 부여
		session.setAttribute("email", email);
		
		PrintWriter script = response.getWriter();
		
		script.println("<script>");
		script.println("location.href='index.jsp'");
		script.println("</script>");

		script.close();
	} else if (result == 0){
		PrintWriter script = response.getWriter();
		
		script.println("<script>");
		script.println("alert('비밀번호가 틀립니다.');");
		script.println("history.back();");
		script.println("</script>");

		script.close();
	} 
	
	else if (result == -1) {
		PrintWriter script = response.getWriter();
		
		script.println("<script>");
		script.println("alert('존재하지 않는 이메일입니다.');");
		script.println("history.back();");
		script.println("</script>");

		script.close();
	} else if (result == -2) {
		PrintWriter script = response.getWriter();
		
		script.println("<script>");
		script.println("alert('데이터베이스 오류가 발생했습니다.');");
		script.println("history.back();");
		script.println("</script>");

		script.close();
	}
%>