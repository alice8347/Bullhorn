import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Date;
import java.util.List;

import model.Post;
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
//		message = "";
//		List<Post> postList = PostDB.select();
//		message += "<div class=\"container\"><table class=\"table table-striped\">";
//		for (int i = 0; i < postList.size(); i++) {
//			message += "<tr><td width=\"10%\">" + postList.get(i).getId() + "</td><td width=\"20%\">" + postList.get(i).getUserId() + "</td><td width=\"70%\" align=\"right\">" + postList.get(i).getPostDate() + "</td></tr>";
//			message += "<tr><td colspan=\"3\">" + postList.get(i).getContent() + "</td></tr>";
//		}
//		message += "</table></div>";
//		request.setAttribute("message", message);
//		getServletContext().getRequestDispatcher("/PostList.jsp").forward(request, response);
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		Post newPost = new Post();
		long userId = 0;
		if (request.getParameter("userId") != null) {
			userId = Long.parseLong(request.getParameter("userId"));
			newPost.setUserId(userId);
		}
		
		String content = "";
		if (request.getParameter("content") != null) {
			content = request.getParameter("content");
			newPost.setContent(content);
			Date today = new Date();
			newPost.setPostDate(today);
			PostDB.insert(newPost);
		}
		
		message = "";
		List<Post> postList = PostDB.select();
		message += "<div class=\"container\"><table class=\"table table-striped\">";
		for (int i = 0; i < postList.size(); i++) {
			message += "<tr><td width=\"10%\">" + postList.get(i).getId() + "</td><td width=\"20%\">" + postList.get(i).getUserId() + "</td><td width=\"70%\" align=\"right\">" + postList.get(i).getPostDate() + "</td></tr>";
			message += "<tr><td colspan=\"3\">" + postList.get(i).getContent() + "</td></tr>";
		}
		message += "</table></div>";
		request.setAttribute("message", message);
		getServletContext().getRequestDispatcher("/PostList.jsp").forward(request, response);
		//doGet(request, response);
	}

}
