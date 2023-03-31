package totankhai;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
public class ProductDAO {
	ConnectDB connectDB = new ConnectDB();
	Connection conn = connectDB.connectionsql();

	public List<Product> GetAll() {
		String sql = "select * from product";

		try {
			List<Product> productList = new ArrayList<Product>();
			Statement stm = (Statement) conn.createStatement();
			ResultSet data = stm.executeQuery(sql);
			while (data.next()) {
				int id = data.getInt(1);
				String name = data.getString(2);
				int price = data.getInt(3);
				Product product = new Product(id, name, price);
				productList.add(product);
			}
			return productList;
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	public Product GetProduct(Integer id) {
		String sql = "select * from product where id=" + id;

		try {
			Statement stm = (Statement) conn.createStatement();
			ResultSet data = stm.executeQuery(sql);

			if (data.next()) {
				int idProduct = data.getInt(1);
				String name = data.getString(2);
				int price = data.getInt(3);
				Product product = new Product(idProduct, name, price);
				return product;
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}

	public boolean add(Product product) {
		String sql = "insert into product values(?, ?)";

		try {
			PreparedStatement ptm = (PreparedStatement) conn.prepareStatement(sql);
			ptm.setString(1, product.name);
			ptm.setInt(2, product.price);
			int result = ptm.executeUpdate();
			if (result == 1) {
				return true;
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}

	public boolean update(Product product) {
		String sql = "Update product set name='" + product.name + "', price='" + product.price + "' where id="
				+ product.id;

		try {
			Statement stm = (Statement) conn.createStatement();
			int result = stm.executeUpdate(sql);
			if (result == 1) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}

	public boolean delete(Integer id) {
		String sql = "Delete from product where id=" + id;

		try {
			Statement stm = (Statement) conn.createStatement();

			int result = stm.executeUpdate(sql);
			if (result == 1) {
				return true;
			} else {
				return false;

			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return false;
	}

}
