package net.todoapp.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.todoapp.dao.UserDao;
import net.todoapp.model.User;

@WebServlet("/register")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;
       
	public void init(){
		userDao=new UserDao();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		response.sendRedirect("register/register.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		register(request,response);
	}

	private void register(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String firstName=request.getParameter("firstName");
		String lastName=request.getParameter("lastName");
		String userName=request.getParameter("username");
		String password=request.getParameter("password");
		
		User employee = new User();
		employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setUsername(userName);
        employee.setPassword(password);
        
        try {
        	int result =userDao.registerEmployee(employee);
        	
        	if (result == 1) {
                request.setAttribute("NOTIFICATION", "User Registered Successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("register/register.jsp");
        dispatcher.forward(request, response);
	}
	
}
