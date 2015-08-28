import java.io.IOException;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Post;
import model.Tweetuser;

/**
 * Servlet implementation class ShowProfile
 */
@WebServlet("/ShowProfile")
public class ShowProfile extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
	private String profile;
	private String post;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowProfile() {
        super();
        profile = "";
        post = "";
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
		profile = "";
		post = "";
		String name = request.getParameter("name");
		if (name != null) {
			Tweetuser searchedUser = UserDB.selectByName(name);
			profile += "<div class=\"container\"><div class=\"jumbotron\"><p>User Name: " + searchedUser.getName() + "</p><p>Motto: " + searchedUser.getMotto() + "</p><p>Join Date: " + searchedUser.getJoinDate() + "</p></div></div>";
			request.setAttribute("name", name);
			request.setAttribute("profile", profile);
			
			if (request.getParameter("searchProfilePostSub") != null) {
				List<Post> searchedKeyPost = PostDB.selectByUserKey(name, request.getParameter("search"));
				post += "<div class=\"container\"><table class=\"table table-striped\">";
				for (int i = 0; i < searchedKeyPost.size(); i++) {
					post += "<tr><td width=\"10%\">" + searchedKeyPost.get(i).getId() + "</td><td width=\"60%\"><a href=\"ShowProfile?name=" + searchedKeyPost.get(i).getTweetuser().getName() + "\">" + searchedKeyPost.get(i).getTweetuser().getName() + "</a></td><td width=\"30%\" align=\"right\">" + searchedKeyPost.get(i).getPostDate() + "</td></tr>";
					post += "<tr><td colspan=\"3\">" + searchedKeyPost.get(i).getContent() + "</td></tr>";
				}
				post += "</table></div>";
			} else {
				List<Post> searchedPost = PostDB.selectByUser(name);
				post += "<div class=\"container\"><table class=\"table table-striped\">";
				for (int i = 0; i < searchedPost.size(); i++) {
					post += "<tr><td width=\"10%\">" + searchedPost.get(i).getId() + "</td><td width=\"60%\"><a href=\"ShowProfile?name=" + searchedPost.get(i).getTweetuser().getName() + "\">" + searchedPost.get(i).getTweetuser().getName() + "</a></td><td width=\"30%\" align=\"right\">" + searchedPost.get(i).getPostDate() + "</td></tr>";
					post += "<tr><td colspan=\"3\">" + searchedPost.get(i).getContent() + "</td></tr>";
				}
				post += "</table></div>";
			}
		}
		request.setAttribute("name", name);
		request.setAttribute("post", post);
		getServletContext().getRequestDispatcher("/Profile.jsp").forward(request, response);
	}

}
