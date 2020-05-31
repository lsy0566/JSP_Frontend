<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="user.UserDTO"%>
<%@ page import="user.UserDAO"%>
<%@ page import="util.SHA256"%>
<%@ page import="java.io.PrintWriter"%>

<%
	request.setCharacterEncoding("UTF-8");

	String email = null;
	String username = null;
	String password = null;
	String phoneNumber = null;
	String secondPhoneNumber = null;

	if (request.getParameter("email") != null) {
		email = (String) request.getParameter("email");
	}

	if (request.getParameter("username") != null) {
		username = (String) request.getParameter("username");
	}

	if (request.getParameter("password") != null) {
		password = (String) request.getParameter("password");
	}
	
	if (request.getParameter("phoneNumber") != null) {
		phoneNumber = (String) request.getParameter("phoneNumber");
	}
	
	if (request.getParameter("secondPhoneNumber") != null) {
		secondPhoneNumber = (String) request.getParameter("secondPhoneNumber");
	}

	if (email == null || username == null || password == null 
			|| phoneNumber == null || secondPhoneNumber == null) {
		PrintWriter script = response.getWriter();

		script.println("<script>");
		script.println("alert('입력이 안 된 사항이 있습니다.');");
		script.println("history.back();");
		script.println("</script>");

		script.close();

	} else {
		UserDAO userDAO = new UserDAO();

		int result = userDAO
				.join(new UserDTO(email, username, password, phoneNumber, 
						secondPhoneNumber, false, false));

		if (result == -1) {
			PrintWriter script = response.getWriter();

			script.println("<script>");
			script.println("alert('이미 존재하는 이메일입니다.');");
			script.println("history.back();");
			script.println("</script>");
			script.close();

		} else {
			session.setAttribute("email", email);

			PrintWriter script = response.getWriter();

			script.println("<script>");
			script.println("alert('회원가입이 완료 되었습니다.')");
			script.println("location.href = 'index.jsp';");
			script.println("</script>");
			script.close();

		}

	}
%>