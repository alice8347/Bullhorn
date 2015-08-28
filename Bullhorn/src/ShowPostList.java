import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.Date;
import java.util.List;

import model.Post;
import model.Tweetuser;
/**
 * Servlet implementation class ShowPostList
 */
@WebServlet("/ShowPostList")
public class ShowPostList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String message;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowPostList() {
        super();
        message = "";
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		response.setContentType("text/html");
		
		if (request.getParameter("addPostSub") != null) {
			Post newPost = new Post();
			String currentUserN = (String) session.getAttribute("userName");
			Tweetuser currentUser = UserDB.selectByName(currentUserN);
			newPost.setTweetuser(currentUser);
		
			String content = "";
			if (request.getParameter("content") != null) {
				content = request.getParameter("content");
				newPost.setContent(content);
				Date today = new Date();
				newPost.setPostDate(today);
				PostDB.insert(newPost);
			}
		}
		
		message = "";
		if (request.getParameter("searchPostSub") != null) {
			List<Post> searchList = PostDB.selectByKeyword(request.getParameter("search"));
			message += "<div class=\"container\"><table class=\"table table-striped\">";
			for (int i = 0; i < searchList.size(); i++) {
				message += "<tr><td width=\"10%\">" + searchList.get(i).getId() + "</td><td width=\"60%\">" + searchList.get(i).getTweetuser().getName() + "</td><td width=\"30%\" align=\"right\">" + searchList.get(i).getPostDate() + "</td></tr>";
				message += "<tr><td colspan=\"3\">" + searchList.get(i).getContent() + "</td></tr>";
			}
			message += "</table></div>";
		} else {
			List<Post> postList = PostDB.select();
			message += "<div class=\"container\"><table class=\"table table-striped\">";
			for (int i = 0; i < postList.size(); i++) {
				message += "<tr><td width=\"10%\">" + postList.get(i).getId() + "</td><td width=\"60%\">" + postList.get(i).getTweetuser().getName() + "</td><td width=\"30%\" align=\"right\">" + postList.get(i).getPostDate() + "</td></tr>";
				message += "<tr><td colspan=\"3\">" + postList.get(i).getContent() + "</td></tr>";
			}
			message += "</table></div>";
		}
		request.setAttribute("message", message);
		getServletContext().getRequestDispatcher("/PostList.jsp").forward(request, response);
	}

}
