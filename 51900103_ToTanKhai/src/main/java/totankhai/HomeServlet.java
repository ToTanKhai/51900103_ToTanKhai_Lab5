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
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ProductDAO productDAO = new ProductDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
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
		if (fullname == null) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;
		} else {
			List<Product> products = productDAO.GetAll();
			request.setAttribute("productList", products);
			request.getRequestDispatcher("index.jsp").forward(request, response);
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
		if (fullname == null) {
			getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		} else {
			String name = request.getParameter("name-product");
			String price = request.getParameter("price-product");
			String error = "";

			if (name.isEmpty() || price.isEmpty()) {
				error = "Please enter your account!";
			}

			if (error.isEmpty()) {
				int priceInt = Integer.parseInt(price);
				Product product = new Product(0, name, priceInt);
				if (productDAO.add(product)) {
					request.setAttribute("message", "Add product successfully!");
					List<Product> products = productDAO.GetAll();
					request.setAttribute("productList", products);
					request.getRequestDispatcher("/index.jsp").forward(request, response);
				} else {
					error = "Add product unsuccessfully!";
				}
			} else {
				List<Product> products = productDAO.GetAll();
				request.setAttribute("error", error);
				request.setAttribute("name", name);
				request.setAttribute("price", price);
				request.setAttribute("productList", products);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			}
		}
		doGet(request, response);
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String value = request.getParameter("id");
		if (value != null) {
			System.out.println("Found" + value);
		} else {
			System.out.println("Not found");
		}
	}

}
