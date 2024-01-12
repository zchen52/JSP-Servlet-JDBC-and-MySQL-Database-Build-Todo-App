package net.todoapp.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.todoapp.dao.LoginDao;
import net.todoapp.dao.UserDao;
import net.todoapp.model.LoginBean;
import net.todoapp.model.User;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginDao loginDao;
       
	public void init(){
		loginDao=new LoginDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.sendRedirect("login/login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		authenticate(request, response);
	}

	private void authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String userName=request.getParameter("username");
		String password=request.getParameter("password");
		
		LoginBean employee = new LoginBean();
        employee.setUsername(userName);
        employee.setPassword(password);
        
        try {
 
        	if (loginDao.validate(employee)) {
        		System.out.println("Login Success");
        		RequestDispatcher dispatcher = request.getRequestDispatcher("todo/todo-list.jsp");
                dispatcher.forward(request, response);
            }else {
            	System.out.println("Login Fail");
                response.sendRedirect("login/login.jsp?noficatin=Incorrect Username or Password");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
