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
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDAO userdao = new UserDAO();
	ProductDAO productDAO = new ProductDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
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
			request.getRequestDispatcher("register.jsp").forward(request, response);
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession httpSession = request.getSession();
		String fullname = (String) httpSession.getAttribute("fullname");
		if (fullname != null) {
			List<Product> products = productDAO.GetAll();
			request.setAttribute("productList", products);
			getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		} else {
			fullname = request.getParameter("fullname");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String confirm_password = request.getParameter("confirm-password");
			String error = "";
			if (fullname.isEmpty() || username.isEmpty() || password.isEmpty() || confirm_password.isEmpty()) {
				error = "Please enter your account!";
			} else if (userdao.CheckUser(username)) {
				error = "Invalid username!";
			} else if (password.length() < 6) {
				error = "Your password at least 6 characters";
			} else if (!password.equals(confirm_password)) {
				error = "Invalid password!";
			}
			if (error.isEmpty()) {
				User user = new User(fullname, username, password);
				if (userdao.CreateUser(user)) {
					request.setAttribute("message", "Create account successfully");
					request.setAttribute("fullname", "");
					request.setAttribute("username", "");
					request.getRequestDispatcher("register.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("error", error);
				request.setAttribute("fullname", fullname);
				request.setAttribute("username", username);
				request.getRequestDispatcher("register.jsp").forward(request, response);
			}
		}
	}

}

