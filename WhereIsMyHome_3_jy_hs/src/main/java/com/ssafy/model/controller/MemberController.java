package com.ssafy.model.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import com.ssafy.model.dto.UserInfo;
import com.ssafy.model.service.UserInfoService;
import com.ssafy.model.service.UserInfoServiceImpl;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class MemberController
 */
@WebServlet("/member")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final UserInfoService userInfoService = UserInfoServiceImpl.getInstance();
	
	private ServletContext application;
	private String root;
	
	public void init() {
		application = getServletContext();
		root = application.getContextPath();
		application.setAttribute("root", root);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		
		switch(action) {
		case "login":
			try {
				String path = signin(request, response);
				forward(path, request, response);
			} catch (ServletException | IOException | SQLException e) {
				e.printStackTrace();
			}
			break;
		case "signup":
			try {
				String path = signup(request, response);
				redirect(path, request, response);
			} catch(Exception e) {
				e.printStackTrace();
			}
			break;
		case "logout":
			try {
				String path = logout(request, response);
				forward(path, request, response);
			} catch (ServletException | IOException | SQLException e) {
				e.printStackTrace();
			}
			break;
		case "mvedit":
			try {
				String path = root + "/edit-profile.jsp";
				forward(path, request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
			break;
		case "edit":
			try {
				String path = edit(request, response);
				forward(path, request, response);
			} catch (ServletException | IOException | SQLException e) {
				e.printStackTrace();
			}
			break;
		case "delete":
			try {
				String path = delete(request, response);
				redirect(path, request, response);
			} catch (ServletException | IOException | SQLException e) {
				e.printStackTrace();
			}
			break;
		}
		
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//톰캣 10버전부터는 post에서 한글 인코딩 안해도 문제 없음
//		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}
	
	private void forward(String path, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("forward: " + path);
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
	
	private void redirect(String path, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("redirect: " + path);
		response.sendRedirect(request.getContextPath() + path);
	}
	
	private String signin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		try {
			String userEmail = request.getParameter("mail");
			String userPassword = request.getParameter("password");
			UserInfo userInfo = userInfoService.login(userEmail, userPassword);
			if (Objects.nonNull(userInfo)) {
				
				HttpSession session = request.getSession();
				session.setAttribute("userInfo", userInfo);
				return "/index.jsp";
			} else {
				request.setAttribute("msg", "아이디, 비밀번호를 확인 해주세요");
				return "/member/login.jsp";
			}
		} catch(Exception e) {
			e.printStackTrace();
			return "/error.jsp";
		}

	}
	
	private String logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		try {
			HttpSession session = request.getSession();
			session.invalidate();	
			return "/index.jsp";
		} catch(Exception e) {
			e.printStackTrace();
			return "/error.jsp";
		}

	}
	
	private String signup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		try {
			UserInfo userInfo = new UserInfo();
			
			userInfo.setUserName(request.getParameter("name"));
			userInfo.setUserEmail(request.getParameter("email"));
			userInfo.setUserPassword(request.getParameter("password"));
			boolean success = userInfoService.addUser(userInfo);
			if (success) {
				return "/member/login.jsp";
			} else {
				return "/error.jsp";
			}
		} catch(Exception e) {
			e.printStackTrace();
			return "/error.jsp";
		}

	}
	
	private String edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		try {
			UserInfo userInfo = new UserInfo();
			UserInfo newUserInfo;
			HttpSession session = request.getSession();
			UserInfo sessionUserInfo = (UserInfo) session.getAttribute("userInfo");
			
			userInfo.setUserName(request.getParameter("name"));
			userInfo.setUserEmail(request.getParameter("email"));
			userInfo.setUserPassword(request.getParameter("password"));
			
			System.out.println();
			if (!sessionUserInfo.getUserEmail().equals(userInfo.getUserEmail())) {
				return "/error.jsp";
			} else if (userInfo.getUserPassword().isBlank()) {
				newUserInfo = userInfoService.modifyUserName(userInfo.getUserEmail(), userInfo.getUserName());
				System.out.println("passowrd == null: " + newUserInfo);
			} else {
				newUserInfo = userInfoService.modifyPwd(userInfo.getUserEmail(), userInfo.getUserName(), userInfo.getUserPassword());
				System.out.println("passowrd != null: " + newUserInfo);
			}
			
			if (Objects.nonNull(newUserInfo)) {
				session.invalidate();
				request.getSession().setAttribute("userInfo", newUserInfo);
				return "/member/profile.jsp";
			} else {
				System.out.println("edit fail");
				return "/error.jsp";
			}
		} catch(Exception e) {
			e.printStackTrace();
			return "/error.jsp";
		}

	}
	
	private String delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		try {
			HttpSession session = request.getSession();
			UserInfo sessionUserInfo = (UserInfo) session.getAttribute("userInfo");
			session.invalidate();
		
			if (userInfoService.deleteUser(sessionUserInfo.getUserEmail())) {
				return "/index.jsp";
			}
			return "/error.jsp";
			
		} catch(Exception e) {
			e.printStackTrace();
			return "/error.jsp";
		}

	}
	
	
}
