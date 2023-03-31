package totankhai;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDAO userdao = new UserDAO();
	ProductDAO productDAO = new ProductDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession httpSession = request.getSession();
		String fullname = (String) httpSession.getAttribute("fullname");

		if (fullname != null) {
			List<Product> products = productDAO.GetAll();
			request.setAttribute("productList", products);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String remember = request.getParameter("remember");
		
		String error = "";
		if (username.isEmpty() || password.isEmpty()) {
			error = "Please enter your account";
		} else if (!userdao.CheckUser(username)) {
			error = "Invalid account";
		}
		if (error.isEmpty())
			if (userdao.CheckLogin(username, password)) {
				User user = userdao.GetUser(username);
				HttpSession httpSession = request.getSession();
				List<Product> products = productDAO.GetAll();
				request.setAttribute("productList", products);
				httpSession.setAttribute("fullname", user.fullname);
				request.getRequestDispatcher("index.jsp").forward(request, response);
				return;
			} else {
				error = "Invalid password";
			}

		request.setAttribute("error", error);
		request.setAttribute("username", username);
		request.getRequestDispatcher("login.jsp").forward(request, response);
		doGet(request, response);
	}

}


