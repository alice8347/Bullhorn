import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Tweetuser;

/**
 * Servlet implementation class User
 */
@WebServlet("/User")
public class User extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String loginErr;
	private String signupErr;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public User() {
        super();
        loginErr = "";
        signupErr = "";
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("logout") != null) {
			String logout = request.getParameter("logout");
			if (logout.equals("true")) {
				request.getSession().removeAttribute("userName");
				getServletContext().getRequestDispatcher("/ShowPostList").forward(request, response);
			}
		}
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		loginErr = "";
		if (request.getParameter("LoginSub") != null) {
			if (request.getParameter("userName") != null) {
				String inputUserN = request.getParameter("userName");
				if (UserDB.selectByName(inputUserN) == null) {
					loginErr += "<script type=\"text/javascript\">validateUserName()</script>";
					request.setAttribute("loginErr", loginErr);
					getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
				} else {
					if (UserDB.selectByName(inputUserN).getPassword().equals(request.getParameter("password"))) {
						request.getSession().setAttribute("userName", inputUserN);
						getServletContext().getRequestDispatcher("/ShowPostList").forward(request, response);
					} else {
						loginErr += "<script type=\"text/javascript\">validatePassword()</script>";
						request.setAttribute("loginErr", loginErr);
						getServletContext().getRequestDispatcher("/Login.jsp").forward(request, response);
					}
				}
			}
		}
		
		signupErr = "";
		if (request.getParameter("SignupSub") != null) {
			if (request.getParameter("userName") != null) {
				String inputUserN = request.getParameter("userName");
				if (UserDB.selectByName(inputUserN) == null) {
					Tweetuser user = new Tweetuser();
					String userName = request.getParameter("userName");
					user.setName(userName);
					String password = request.getParameter("password");
					user.setPassword(password);
					String motto = request.getParameter("motto");
					user.setMotto(motto);
					Date signupDate = new Date();
					user.setJoinDate(signupDate);
					UserDB.insert(user);
					request.getSession().setAttribute("userName", user.getName());
					getServletContext().getRequestDispatcher("/ShowPostList").forward(request, response);
				} else {
					signupErr += "<script type=\"text/javascript\">validateUserName()</script>";
					request.setAttribute("signupErr", signupErr);
					getServletContext().getRequestDispatcher("/Signup.jsp").forward(request, response);
				}
			}
		}
	}

}
