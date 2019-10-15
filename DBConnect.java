
/*Contains all the required queries*/
import java.sql.*;
import java.util.ArrayList;

/*To connect to database*/
public class DBConnect {
	private Connection con;
	/* st allows to query */
	private Statement st;
	private Statement st2;
	/* RS receives the results of the queries */
	private ResultSet rs;
	private ResultSet rs2;
	/*When executing update, # of rows is returned instead of resultset*/
	private int rowsAffected;
	
	

	public DBConnect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mcs", "root", "");
			st = con.createStatement();
			st2 = con.createStatement();
		} catch (Exception e) {
			System.out.println(e);// TODO: handle exception
		}
	}

	/*
	 * Returns true if email and pw are valid
	 */
	/**
	 * Lookups email and password from the admin table
	 * @param email 
	 * @param password
	 * @return true if 
	 */
	public boolean verifyAdmin(String email, String password) {
		try {
			rs = st.executeQuery("SELECT AdminID FROM admin WHERE (AdminEmail = '" + email + "' AND AdminPassword = '"
					+ password + "')");
			if (!rs.isBeforeFirst()) {
				return false;
			}
			else
				return true;
			/*rs = st.executeQuery("SELECT * FROM `admin`");

			ArrayList<Admin> a = new ArrayList<>();

			 Store admin credentials in arraylist 

			while (rs.next()) {
				a.add(new Admin(rs.getInt(1), rs.getString(2), rs.getString(3)));
			}

			boolean correctEmail = false;
			boolean correctPassword = false;
			 Check email 
			for (int i = 0; i < a.size(); i++) {
				if (a.get(i).getUserEmailAddress().equals(email)) {
					correctEmail = true;
					break;
				}
			}
			 We can skip checking the password if email is already incorrect 
			if (correctEmail == false) {
				return false;
			}

			 Check password 
			for (int i = 0; i < a.size(); i++) {
				if (a.get(i).getUserPassword().equals(password)) {
					correctPassword = true;
					break;
				}
			}
			 If both boolean variables are true then valid login 
			if (correctPassword && correctEmail) {
				return true;
			} else {
				return false;}*/
			
		} catch (

		SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * @param email
	 * @param password
	 * @return
	 */
	public boolean verifyCustomer(String email, String password) {
		try {

			rs = st.executeQuery("SELECT CustomerID FROM customer WHERE (CustEmail = '" + email + "' AND CustPassword = '"
					+ password + "')");
			if (!rs.isBeforeFirst()) {
				return false;
			}
			else
				return true;

		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;

	}
	/*
	 * Returns true if email and pw are valid
	 */
	/**
	 * @param email Input email of the user attempting to login as admin
	 * @param password Input password of the user attempting to login as admin
	 * @return true if email and password are valid for admin login
	 */
	public boolean verifySupplier(String email, String password) {
		try {

			rs = st.executeQuery("SELECT SupplierID FROM supplier WHERE (SuppEmail = '" + email + "' AND SuppPassword = '"
					+ password + "')");
			if (!rs.isBeforeFirst()) {
				return false;
			}
			else
				return true;

		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	/*Returns adminID based on unique email and password*/
	/**
	 * @param email 
	 * @param password
	 * @return the adminID based on email and password
	 */
	public int getAdminID(String email, String password) {

		try {
			int adminID = 0;
			rs = st.executeQuery("SELECT AdminID FROM admin WHERE (AdminEmail = '" + email + "' AND AdminPassword = '"
					+ password + "')");
			while (rs.next()) {
				adminID=rs.getInt(1);
			}
			
			return adminID;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}


	/*ADMIN METHOD: To add category*/
	/**
	 * Update the category table with an insertion of 1 new category
	 * @param catName
	 * @param catDesc
	 */
	public void addCategory(String catName, String catDesc) {
		// TODO Auto-generated method stub
		try {
			rowsAffected = st.executeUpdate("INSERT INTO `category` (`CategoryID`, `CategoryName`, `CategoryDesc`) VALUES (NULL, '" + catName + "', '"+catDesc+"')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/*ADMIN METHOD: To add sizes*/
	/**
	 * Update the stock_size table with an insertion of 1 new size
	 * @param newSize
	 */
	public void addSize(String newSize) {
		// TODO Auto-generated method stub
		try {
			rowsAffected = st.executeUpdate("INSERT INTO `stock_size` (`SizeID`, `Size`) VALUES (NULL, '"+newSize+"')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	/*ADMIN METHOD: To add color*/
	/**
	 * Update the stock_color table with an insertion of 1 new color
	 * @param newColor
	 */
	public void addColor(String newColor) {
		// TODO Auto-generated method stub
		try {
			rowsAffected = st.executeUpdate("INSERT INTO `stock_color` (`ColorID`, `Color`) VALUES (NULL, '"+newColor+"')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/*ADMIN METHOD: get all existing suppliers*/
	/**
	 * @return the list of all registered suppliers
	 */
	public ArrayList<Supplier> getSuppliers() {
		// TODO Auto-generated method stub
		try {
			/*Make a Supplier object from each row of the Supplier table*/
			rs = st.executeQuery("SELECT * FROM `supplier` ");
			/*Supplier objects are stored in an arrayList*/
			ArrayList<Supplier> supp = new ArrayList<Supplier>();
			
			while(rs.next()) {
				supp.add(new Supplier(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getInt(11), rs.getString(12), rs.getString(13)));		
			}
			
			/* SELECT ContactNum FROM supplier_contactnum WHERE (SupplierID = '1') */
			return supp;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/*ADMIN METHOD: get all existing customers*/
	/**
	 * @return the list of all registered customers
	 */
	public ArrayList<Customer> getCustomers() {
		// TODO Auto-generated method stub
		try {
			rs = st.executeQuery("SELECT * FROM `customer` ");
			
			ArrayList<Customer> cust = new ArrayList<Customer>();
			
			while(rs.next()) {
				cust.add(new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getLong(5), rs.getInt(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getInt(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getInt(14), rs.getString(15), rs.getString(16), rs.getString(17), rs.getString(18)));
			}
			return cust;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/*ADMIN METHOD: print all existing sizes*/
	/**
	 * Prints all of the sizes from the stock_size table
	 */
	public void printSizes() {
		// TODO Auto-generated method stub
		try {
			rs = st.executeQuery("SELECT * FROM `stock_size`");
			while(rs.next()) {
				System.out.println(rs.getString(1)+" "+rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/*ADMIN METHOD: print all existing colors*/
	/**
	 * Prints all of the sizes from the stock_color table
	 */
	public void printColors() {
		try {
			rs = st.executeQuery("SELECT * FROM `stock_color`");
			while (rs.next()) {
				System.out.println(rs.getString(1)+" "+rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	/*ADMIN METHOD: print all existing categories + descriptions*/
	/**
	 * Prints all of the categories from the category table
	 */
	public void printCategories() {
		// TODO Auto-generated method stub
		try {
			
			rs = st.executeQuery("SELECT CategoryName, CategoryDesc FROM `category`");
			/*If we want to retrieve column names
			 * ResultSetMetaData rsmd = rs.getMetaData(); String name =
			 * rsmd.getColumnName(1);
			 */
			while (rs.next()) {
				System.out.println(rs.getString(1)+" - "+rs.getString(2));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		
	}

	/*Returns supplierID based on unique email and password*/
	/**
	 * @param email
	 * @param password
	 * @return supplierID based on valid supplier email and password parameters
	 */
	public int getSupplierID(String email, String password) {

		try {
			int supplierID = 0;
			rs = st.executeQuery("SELECT SupplierID FROM supplier WHERE (SuppEmail = '" + email + "' AND SuppPassword = '"
					+ password + "')");
			while (rs.next()) {
				supplierID=rs.getInt(1);
			}
			
			return supplierID;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;

	}

	/**
	 * Prints all of the products listed by the supplier 
	 * @param supplierID
	 */
	public void viewSupplierProducts(int supplierID) {
		// TODO Auto-generated method stub
		try {
			rs = st.executeQuery("SELECT product.ProductID, product.ProductName, product_stock.ColorID, product_stock.SizeID ,product_stock.Stock, product.ProductDesc, product.QtyPerUnit, product.UnitPrice, category.CategoryName FROM ((product LEFT JOIN category ON product.CategoryID = category.CategoryID AND product.SupplierID = '"+supplierID+"') LEFT JOIN product_stock ON product.ProductID = product_stock.ProductID)");
			
			/*To print column names*/
			ResultSetMetaData column = rs.getMetaData();
			for (int i = 1; i < column.getColumnCount() + 1; i++) {
				System.out.print(column.getColumnName(i)+"|");
			}
			System.out.println("\n");
			while(rs.next()) {
				System.out.println(rs.getString(1)+"|"+rs.getString(2)+"|"+ rs.getString(3)+"|"+ rs.getString(4)+"|"+rs.getString(5)+"|"+rs.getString(6)+"|"+rs.getString(7)+"|"+rs.getString(8)+"|"+rs.getString(9));
			}
			System.out.println("");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	/*Returns a supplier reference that contains all the information of the current logged in supplier*/
	/**
	 * Returns a supplier reference that contains all the 
	 * information of the current logged in supplier
	 * @param supplierID
	 * @return
	 */
	public Supplier getSupplier(int supplierID) {
		try {
			Supplier supp = null;
	
			rs = st.executeQuery("SELECT * FROM supplier WHERE (SupplierID = '"+supplierID+"')");
			rs2 = st2.executeQuery("SELECT ContactNum FROM supplier_contactnum WHERE (SupplierID = '"+supplierID+"')");      
			
			while(rs.next()) {
				supp = new Supplier(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getInt(11), rs.getString(12), rs.getString(13));
			}
			while(rs2.next()) {
				supp.getContactNumber().add(rs2.getString(1)); 
			}
			return supp;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Returns distinct supplier 
	 * @param supplierID
	 */
	public void viewDistinctSupplierProducts(int supplierID) {
		// TODO Auto-generated method stub
		try {
			rs = st.executeQuery("SELECT DISTINCT product.ProductID, product.ProductName, product.ProductDesc, product.QtyPerUnit, product.UnitPrice, category.CategoryName FROM ((product LEFT JOIN category ON product.CategoryID = category.CategoryID AND product.SupplierID = '"+supplierID+"') LEFT JOIN product_stock ON product.ProductID = product_stock.ProductID)");
			
			/*To print column names*/
			ResultSetMetaData column = rs.getMetaData();
			for (int i = 1; i < column.getColumnCount() + 1; i++) {
				System.out.print(column.getColumnName(i)+"|");
			}
			System.out.println("\n");
			while(rs.next()) {
				System.out.println(rs.getString(1)+"|"+rs.getString(2)+"|"+ rs.getString(3)+"|"+ rs.getString(4)+"|"+rs.getString(5)+"|"+rs.getString(6));
			}
			System.out.println("");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	/**
	 * Add a product into the product table
	 * @param supplierID
	 * @param cname
	 * @param pname
	 * @param pdesc
	 * @param pquantity
	 * @param pprice
	 */
	public void addProduct(int supplierID, int cname, String pname, String pdesc, int pquantity, 
			int pprice/* , int stock, int colorID, int sizeID */) {
		// TODO Auto-generated method stub
		try {
			rowsAffected = st2.executeUpdate("INSERT INTO `product` (`ProductID`, `SupplierID`, `CategoryID`, `ProductName`, `ProductDesc`, `QtyPerUnit`, `UnitPrice`) VALUES (NULL, '"+supplierID+"', '"+cname+"', '"+pname+"', '"+pdesc+"', '"+pquantity+"', '"+pprice+"')");
			/*
			 * rowsAffected = st2.
			 * executeUpdate("INSERT INTO `product_stock` (`ProductID`, `Stock`, `ColorID`, `SizeID`) VALUES ('3', '"
			 * +stock+"', '"+colorID+"', '"+sizeID+"')");
			 */
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Print all categories that contains at least one product
	 */
	public void viewValidCategories() {
		// TODO Auto-generated method stub
		try {
			rs = st.executeQuery("SELECT DISTINCT category.CategoryID, category.CategoryName, category.CategoryDesc FROM category INNER JOIN product ON category.CategoryID = product.CategoryID;");
			/*To print column names*/
			ResultSetMetaData column = rs.getMetaData();
			for (int i = 1; i < column.getColumnCount() + 1; i++) {
				System.out.print(column.getColumnName(i)+"|");
			}
			System.out.println("");
			while(rs.next()) {
				System.out.println(rs.getString(1)+"|"+rs.getString(2)+"|"+ rs.getString(3));
			}
			System.out.println("");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * View all registered categories
	 */
	public void viewCategories() {
		// TODO Auto-generated method stub
		try {
			rs = st.executeQuery("SELECT * FROM `category`");
			/*To print column names*/
			ResultSetMetaData column = rs.getMetaData();
			for (int i = 1; i < column.getColumnCount() + 1; i++) {
				System.out.print(column.getColumnName(i)+"|");
			}
			System.out.println("");
			while(rs.next()) {
				System.out.println(rs.getString(1)+"|"+rs.getString(2)+"|"+ rs.getString(3));
			}
			System.out.println("");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Add product variation into product_stock table
	 * @param prodID
	 * @param colorID
	 * @param sizeID
	 * @param stocks
	 */
	public void registerStock(int prodID, int colorID, int sizeID, int stocks) {
		// TODO Auto-generated method stub
		try {
			rowsAffected = st2.executeUpdate("INSERT INTO `product_stock` (`ProductID`, `Stock`, `ColorID`, `SizeID`) VALUES ('"+prodID+"', '"+stocks+"', '"+colorID+"', '"+sizeID+"')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param email
	 * @param password
	 * @return customerID based on valid customer email and password
	 */
	public int getCustomerID(String email, String password) {
		try {
			int customerID = 0;
			rs = st.executeQuery("SELECT CustomerID FROM customer WHERE (CustEmail = '" + email + "' AND CustPassword = '"
					+ password + "')");
			while (rs.next()) {
				customerID=rs.getInt(1);
			}
			
			return customerID;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * @param customerID
	 * @return returns a reference of an object Customer based on customerID
	 */
	public Customer getCustomer(int customerID) {
		try {
			Customer cust = null;

			rs = st.executeQuery("SELECT * FROM customer WHERE (CustomerID = '"+customerID+"')");
			rs2 = st2.executeQuery("SELECT ContactNum FROM customer_contnum WHERE (CustomerID = '"+customerID+"')");      
			
			while(rs.next()) {
				cust = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getLong(5), rs.getInt(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getInt(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getInt(14), rs.getString(15), rs.getString(16), rs.getString(17), rs.getString(18));
			}
			while(rs2.next()) {
				cust.getContactNumber().add(rs2.getString(1)); 
			}
			return cust;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Print all contact numbers of the a customer based on customerID
	 * @param customerID
	 */
	public void printCustomerContNums(int customerID) {
		// TODO Auto-generated method stub
		try {
			rs = st.executeQuery("SELECT ContactNum FROM customer_contnum WHERE CustomerID = '"+customerID+"'");
			int ctr = 1;
			while(rs.next()) {
				System.out.println("["+ctr+"]"+rs.getString(1));
				ctr++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Print a supplier's contact number
	 * @param supplierID
	 */
	public void printSupplierContNums(int supplierID) {
		// TODO Auto-generated method stub
		try {
			rs = st.executeQuery("SELECT ContactNum FROM supplier_contactnum WHERE SupplierID = '"+supplierID+"'");
			int ctr = 1;
			while(rs.next()) {
				System.out.println("["+ctr+"]"+rs.getString(1));
				ctr++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}//add stock for supplier

	/**
	 * View all products in a category that have at least one stock
	 * @param catID
	 */
	public void viewProductsInCategoryID(int catID) {
		try {
			//Let user view only products on categories that are at stock
			rs = st.executeQuery("SELECT DISTINCT product.ProductID, product.ProductName, product.ProductDesc, product.QtyPerUnit, product.UnitPrice FROM ((product INNER JOIN product_stock ON product.ProductID = product_stock.ProductID AND product_stock.Stock <>0) INNER JOIN category ON category.CategoryID = product.CategoryID AND category.CategoryID = "+catID+"); ");
			
			System.out.println("[AVAILABLE PRODUCTS FOR CATEGORYID = "+catID+"]");
			/*To print column names*/
			ResultSetMetaData column = rs.getMetaData();
			for (int i = 1; i < column.getColumnCount() + 1; i++) {
				System.out.print(column.getColumnName(i)+"|");
			}
			System.out.println("");
			while(rs.next()) {
				System.out.println(rs.getInt(1)+"|"+rs.getString(2)+"|"+rs.getString(3)+"|"+rs.getString(4)+"|"+rs.getString(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * View available sizes for a product
	 * @param prodID
	 */
	public void viewAvailableSizesFor(int prodID) {
			try {
				rs = st.executeQuery("SELECT DISTINCT product_stock.SizeID, stock_size.Size\r\n" + 
						"FROM product_stock\r\n" + 
						"INNER JOIN stock_size\r\n" + 
						"on product_stock.SizeID = stock_size.SizeID  \r\n" + 
						"AND product_stock.ProductID = "+prodID+";\r\n" + 
						"\r\n" + 
						"");
				System.out.println("[AVAILABLE SIZES FOR PROD ID = "+prodID+"]");
				while(rs.next()) {
					System.out.println(rs.getInt(1)+"|"+rs.getString(2));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

	/**
	 * View available colors for a product
	 * @param prodID
	 */
	public void viewAvailableColorsFor(int prodID) {
		try {
			rs = st.executeQuery("SELECT DISTINCT product_stock.ColorID, stock_color.Color\r\n" + 
					"FROM product_stock\r\n" + 
					"INNER JOIN stock_color\r\n" + 
					"on product_stock.ColorID = stock_color.ColorID  \r\n" + 
					"AND product_stock.ProductID = "+prodID+";\r\n" + 
					"\r\n" + 
					"");
			System.out.println("[AVAILABLE COLORS FOR PROD ID = "+prodID+"]");
			while(rs.next()) {
				System.out.println(rs.getInt(1)+"|"+rs.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Print all available product stocks based on size-color combination  
	 * @param prodID
	 * @param sizeID
	 * @param colorID
	 * @return stock amount based on size-color combination  
	 */
	public int viewAvailableStockFor(int prodID, int sizeID, int colorID) {
		// TODO Auto-generated method stub
			try {
				rs = st.executeQuery("SELECT Stock FROM product_stock WHERE(ProductID = "+prodID+" AND sizeID = "+sizeID+" AND colorID = "+colorID+");");
				while(rs.next()) {
					System.out.println(rs.getInt(1)+" avalable stocks left for the chosen product.");
					return rs.getInt(1);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
	}

	/**
	 * Decrements the stock amount when a customer buys a product
	 * @param prodID
	 * @param sizeID
	 * @param colorID
	 * @param quantity
	 */
	public void decrementStockAt(int prodID, int sizeID, int colorID, int quantity) {
		// TODO Auto-generated method stub
		try {
			rowsAffected = st.executeUpdate("UPDATE product_stock SET `Stock`= (`Stock`-"+quantity+") WHERE ProductID = "+prodID+" AND ColorID = "+colorID+" AND SizeID = "+sizeID+";");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param prodID
	 * @return the supplierId of the supplier that supplied the product
	 */
	public int getSupplierIDOf(int prodID) {
		// TODO Auto-generated method stub
		try {
			rs = st.executeQuery("SELECT SupplierID FROM product WHERE ProductID = "+prodID+";");
			while(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * @param prodID
	 * @return price of the product
	 */
	public double getPriceOf(int prodID) {
		// TODO Auto-generated method stub
		try {
			rs = st.executeQuery("SELECT UnitPrice FROM product WHERE ProductID = "+prodID+"; ");
			while(rs.next()) {
				return rs.getDouble(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 
	 * @param custoID
	 * @return the latest orderID of a customer
	 */
	public int getLatestOrderIDof(int custoID) {
		// TODO Auto-generated method stub
		try {
			rs = st.executeQuery("SELECT MAX(OrderID) AS id FROM `order` WHERE CustomerID = "+custoID+" ");
			while(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * Insert into orderdetail table a new entry
	 * @param prodID
	 * @param supplierID
	 * @param priceAtOrder
	 * @param subTotal
	 * @param orderID
	 * @param sizeID
	 * @param colorID
	 * @param catID
	 * @param quantity
	 * @param prodl
	 * @param supp
	 */
	public void fillNewOrderDetail(int prodID, int supplierID, double priceAtOrder, double subTotal, int orderID,
			String sizeID, String colorID, int catID, int quantity, String prodl, String supp) {
			try {
				rowsAffected = st.executeUpdate("INSERT INTO `orderdetail` (`OrderDetailID`, `ProductID`, `SupplierID`, `priceAtOrder`, `Quantity`, `Subtotal`, `OrderID`, `chosenSize`, `chosenColor`, `CategoryID`, `productLabel`, `Supplier`) VALUES (NULL, '"+prodID+"', '"+supplierID+"', '"+priceAtOrder+"', '"+quantity+"', '"+subTotal+"', '"+orderID+"', '"+sizeID+"', '"+colorID+"', '"+catID+"', '"+prodl+"', '"+supp+"')");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		// TODO Auto-generated method stub
	}

	/**
	 * 
	 * @param customerID
	 * @return false if the customer has never ordered once
	 */
	public boolean userHasOrdered(int customerID) {
		// TODO Auto-generated method stub
		try {
			rs = st.executeQuery("SELECT MAX(OrderID) AS id FROM `order` WHERE CustomerID = "+customerID+"");
			
			while(rs.next()) {
				if (rs.getInt(1) <= 0) {
					return false;
				}
				else {
					return true;
				}
			}
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Insert a new entry to the order table
	 * @param customerID
	 */
	public void placeNewOrder(int customerID) {
		try {
			/*Choose a random shipper to ship the order*/
			int shipperCount = 0;
			rs = st2.executeQuery("SELECT COUNT(*) FROM shipper;");
			while(rs.next()) {
				shipperCount = rs.getInt(1);
			}
			int shipperID = (int)(Math.random() * shipperCount + 1);
			//Fill up order
			rowsAffected = st.executeUpdate("INSERT INTO `order` (`OrderID`, `ShipperID`, `TotalPrice`, `ShipDate`, `PaymentDate`, `CustomerID`) VALUES (NULL, '"+shipperID+"', NULL, NULL, NULL, '"+customerID+"')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param colorID
	 * @return the color label of the colorID
	 */
	public String getColorLabel(int colorID) {
		// TODO Auto-generated method stub
		try {
			rs = st.executeQuery("SELECT Color FROM stock_color WHERE ColorId = "+colorID+" ");
			while(rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @param sizeID
	 * @return the size label of the sizeID
	 */
	public String getSizeLabel(int sizeID) {
		// TODO Auto-generated method stub
		try {
			rs = st.executeQuery("SELECT Size FROM stock_size WHERE SizeID = "+sizeID+"");
			while(rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param prodID
	 * @return the product name of the productID
	 */
	public String getprodLabel(int prodID) {
		// TODO Auto-generated method stub
		try {
			rs = st.executeQuery("SELECT ProductName FROM product WHERE ProductID = "+prodID+" ");
			while(rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param supplierID
	 * @return the company name of the supplier
	 */
	public String getCompanyName(int supplierID) {
		// TODO Auto-generated method stub
		try {
			rs = st.executeQuery("SELECT SuppCompanyName FROM supplier WHERE SupplierID = "+supplierID+" ");
			while(rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param customerID
	 * @return totals of the subtotals of order items of the current order
	 */
	public double displayTotalOfRecentUnpaidOrder(int customerID) {
		// TODO Auto-generated method stub
		try {
			//select id of recent order
			
			//store id to a variable
			int latestOrderID = getRecentOrderID(customerID);
			//then display
			rs = st.executeQuery("SELECT SUM(Subtotal) AS totalOnOrder FROM orderdetail WHERE OrderID = "+latestOrderID+";");
			double total = 0;
			
			
			while(rs.next()) {
				total = rs.getDouble(1);
				System.out.println("TOTAL FOR ORDER ID "+latestOrderID+": "+total);
			}
			return total;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * 
	 * @param customerID
	 * @return the recent orderID of the customer
	 * @throws SQLException
	 */
	public int getRecentOrderID(int customerID) throws SQLException {
		rs = st.executeQuery("SELECT MAX(OrderID) AS id FROM `order` WHERE CustomerID = "+customerID+";");
		while(rs.next()) {
			return rs.getInt(1);
		}
		return 0;
	}

	/**
	 * Fill up the current order of the customer when paid
	 * @param customerID
	 * @param total
	 */
	public void payForRecentUnpaidOrder(int customerID,double total) {
		// TODO Auto-generated method stub
	
		try {
			int latestOrderID;
			latestOrderID = getRecentOrderID(customerID);
			
			int totalOrderDetails = countTotalOrderDetails(latestOrderID);
			
			rowsAffected = st.executeUpdate("UPDATE `order` SET `TotalPrice` = '"+total+"', counttotalorderdetails = "+totalOrderDetails+", `PaymentDate` = CURRENT_DATE() WHERE `order`.`OrderID` = "+latestOrderID+"");
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * @param latestOrderID
	 * @return total order items belonging to the recent order
	 */
	private int countTotalOrderDetails(int latestOrderID) {
		// TODO Auto-generated method stub
		try {
			rs = st.executeQuery("SELECT COUNT(*) FROM orderdetail WHERE orderdetail.OrderID = "+latestOrderID+"");
			int count = 0;
			while(rs.next()) {
				count = rs.getInt(1);
			}
			return count;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * @param recentOrderID
	 * @return total of the subtotals of the items that belong to the most recent order
	 */
	private double computeTotalOnRecentOrder(int recentOrderID){
		try {
			rs = st.executeQuery("SELECT SUM(Subtotal) AS totalOnOrder FROM orderdetail WHERE OrderID = "+recentOrderID+";");
			double total = 0;
			while(rs.next()) {
				total = rs.getDouble(1);
			}
			
			return total;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * Print orderdetails of the most recent order of the customer
	 * @param customerID
	 */
	public void displayOrderDetailsOfRecentOrder(int customerID) {
		// TODO Auto-generated method stub
		
		int latestOrderID;
		try {
			latestOrderID = getRecentOrderID(customerID);
			rs = st.executeQuery("SELECT * FROM orderdetail WHERE OrderID = "+latestOrderID+"");
			
			/*To print column names*/
			ResultSetMetaData column = rs.getMetaData();
			for (int i = 1; i < column.getColumnCount() + 1; i++) {
				System.out.print(column.getColumnName(i)+"|");
			}
			System.out.println("");
			

			while(rs.next()) {
				System.out.println(rs.getString(1)+"|"+rs.getString(2)+"|"
						+rs.getString(3)+"|"
						+rs.getString(4)+"|"
						+rs.getString(5)+"|"
						+rs.getString(6)+"|"
						+rs.getString(7)+"|"
						+rs.getString(8)+"|"
						+rs.getString(9)+"|"
						+rs.getString(10)+"|"
						+rs.getString(11)+"|"
						+rs.getString(12)+"|"
						+rs.getString(13)+"|");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Print all of the orders that have been paid by the customer
	 * @param customerID
	 */
	public void displayAllPaidOrders(int customerID) {
		// TODO Auto-generated method stub
		try {
			//if price was not computed and thus not entered in TotalPrice then order wasnt paid yet
			rs = st.executeQuery("SELECT * FROM `order` WHERE CustomerID = "+customerID+" AND TotalPrice <> 0");
			/*To print column names*/
			ResultSetMetaData column = rs.getMetaData();
			for (int i = 1; i < 8; i++) {
				System.out.print(column.getColumnName(i)+"|");
			}
			System.out.println("");
			

			
			while(rs.next()) {
				System.out.println(rs.getString(1)+"|"
						+rs.getString(2)+"|"
						+rs.getString(3)+"|"
						+rs.getString(4)+"|"
						+rs.getString(5)+"|"
						+rs.getString(6)+"|"
						+rs.getString(7)+"|");

			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Print all of the available product stocks that is listed by the supplier
	 * @param supplierID
	 */
	public void displaySupplierStocks(int supplierID) {
		// TODO Auto-generated method stub
		try {
			rs = st.executeQuery("SELECT product_stock.ProductID, product_stock.Stock, product_stock.ColorID, product_stock.SizeID FROM `product_stock` LEFT JOIN product ON product.ProductID = product_stock.ProductID AND product.SupplierID = "+supplierID+"");
			ResultSetMetaData column = rs.getMetaData();
			for (int i = 1; i < column.getColumnCount() + 1; i++) {
				System.out.print(column.getColumnName(i)+"|");
			}
			System.out.println("");
			
			while(rs.next()) {
				System.out.println(rs.getString(1)+"|"
						+rs.getString(2)+"|"
						+rs.getString(3)+"|"
						+rs.getString(4));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Update the amount of stock of the product based on size-color combination
	 * @param pID
	 * @param previousstock
	 * @param colorID
	 * @param sizeID
	 * @param newstock
	 */
	public void updateStockAt(int pID, int previousstock, int colorID, int sizeID, int newstock) {
		// TODO Auto-generated method stub
		try {
			rowsAffected = st.executeUpdate("UPDATE `product_stock` SET `Stock` = '"+newstock+"' WHERE `product_stock`.`ProductID` = "+pID+" AND `product_stock`.`Stock` = "+previousstock+" AND `product_stock`.`ColorID` = "+colorID+" AND `product_stock`.`SizeID` = "+sizeID+"");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Display all order items that have been paid and shipped by the supplier
	 * @param supplierID
	 */
	public void displayAllOrderDetailsPaidAndShipped(int supplierID) {
		// TODO Auto-generated method stub
		try {
			rs = st.executeQuery("SELECT `order`.PaymentDate, orderdetail.productLabel, orderdetail.priceAtOrder, orderdetail.Quantity, orderdetail.chosenSize, orderdetail.chosenColor, orderdetail.Subtotal, `order`.`CustomerID` FROM orderdetail INNER JOIN `order` ON orderdetail.OrderID = `order`.`OrderID` WHERE orderdetail.SupplierID = "+supplierID+" AND `order`.`ShipDate` IS NOT NULL AND `order`.`PaymentDate` IS NOT NULL;");
			rs2 = st2.executeQuery("SELECT SUM(orderdetail.Subtotal) FROM orderdetail INNER JOIN `order` ON orderdetail.OrderID = `order`.`OrderID` WHERE orderdetail.SupplierID = "+supplierID+" AND `order`.`ShipDate` IS NOT NULL AND `order`.`PaymentDate` IS NOT NULL");
			/*To print column names*/
			ResultSetMetaData column = rs.getMetaData();
			for (int i = 1; i < column.getColumnCount() + 1; i++) {
				System.out.print(column.getColumnName(i)+"|");
			}
			System.out.println("");
			
			while(rs.next()) {
				System.out.println(rs.getString(1)+"|"
						+rs.getString(2)+"|"
						+rs.getString(3)+"|"
						+rs.getString(4)+"|"
						+rs.getString(5)+"|"
						+rs.getString(6)+"|"
						+rs.getString(7)+"|"
						+rs.getString(8)+"|");

			}
			while(rs2.next()){
				System.out.println("=====TOTAL SALES: "+rs2.getDouble(1)+"=====");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * Print all unaccomplished order items of the supplier
	 * @param supplierID
	 */
	public void displayUnaccomplishedOrderDetails(int supplierID) {
		// TODO Auto-generated method stub
		try {
			rs = st.executeQuery("SELECT `order`.orderid, orderdetail.orderdetailid, orderdetail.productLabel ,orderdetail.priceAtOrder, orderdetail.Quantity, orderdetail.chosenSize, orderdetail.chosenColor, orderdetail.Subtotal, `order`.`CustomerID` FROM orderdetail INNER JOIN `order` ON orderdetail.OrderID = `order`.`OrderID` WHERE orderdetail.SupplierID = "+supplierID+" AND `order`.`ShipDate` IS NULL AND `order`.`PaymentDate` IS NOT NULL AND `orderdetail`.accomplished = 0");
			/*To print column names*/
			ResultSetMetaData column = rs.getMetaData();
			for (int i = 1; i < column.getColumnCount() + 1; i++) {
				System.out.print(column.getColumnName(i)+"|");
			}
			System.out.println("");
			

			
			while(rs.next()) {
				System.out.println(rs.getString(1)+"|"
						+rs.getString(2)+"|"
						+rs.getString(3)+"|"
						+rs.getString(4)+"|"
						+rs.getString(5)+"|"
						+rs.getString(6)+"|"
						+rs.getString(7)+"|"
						+rs.getString(8)+"|"
						+rs.getString(9)+"|");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Mark the order detail row accomplished
	 * @param orderid
	 * @param orderdetailid
	 */
	public void accomplishOrderdetail(int orderid, int orderdetailid) {
		// TODO Auto-generated method stub
		try {
			rowsAffected = st.executeUpdate("UPDATE `orderdetail` SET `accomplished` = '1' WHERE `orderdetail`.`OrderID` = "+orderid+" AND `orderdetail`.`OrderDetailID` = "+orderdetailid+"");
			//increment the totalorderdetail accomplished too
			rowsAffected = st.executeUpdate("UPDATE `order` SET `countAccomplishedOrderDetails` = `countAccomplishedOrderDetails` + '1' WHERE `order`.`OrderID` = "+orderid+"");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @param email
	 * @param password
	 * @return true if email and password for shipper login is valid
	 */
	public boolean verifyShipper(String email, String password) {
		// TODO Auto-generated method stub
		try {
			rs = st.executeQuery("SELECT ShipperID FROM shipper WHERE (shipemail = '" + email + "' AND shippassword = '"
					+ password + "')");
			if (!rs.isBeforeFirst()) {
				return false;
			}
			else
				return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * @param shipperID
	 * @return returns a Shipper reference that contains all of the
	 * information about the shipper based on shipperID retrieved from
	 * the shipper table
	 */
	public Shipper getShipper(int shipperID) {
		// TODO Auto-generated method stub

		try {
			Shipper ship = null;
			rs = st.executeQuery("SELECT * FROM shipper WHERE (ShipperID = '"+shipperID+"')");
			rs2 = st2.executeQuery("SELECT ContactNum FROM shipper_contnum WHERE (ShipperID = '"+shipperID+"')");      
			
			while(rs.next()) {
				ship = new Shipper(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
			}
			while(rs2.next()) {
				ship.getContactNumber().add(rs2.getString(1)); 
			}
			return ship;
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @param email
	 * @param password
	 * @return the shipper id based on email and password parameters
	 */
	public int getShipperID(String email, String password) {
		// TODO Auto-generated method stub
		int shipperID = 0;
		try {
			rs = st.executeQuery("SELECT ShipperID FROM shipper WHERE (shipemail = '" + email
					+ "' AND shippassword = '" + password + "')");
			while (rs.next()) {
				shipperID = rs.getInt(1);
			}
			return shipperID;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * Print all orders that are assigned to the shipper for shipment
	 * @param shipperID
	 */
	public void viewOrdersAssignedTo(int shipperID) {
		// TODO Auto-generated method stub
		try {
			rs = st.executeQuery("SELECT DISTINCT `order`.`OrderID`,`order`.`TotalPrice`, customer.CustomerID, customer.CustFName, customer.CustMinit, customer.CustLName,customer.CustEmail,customer.CSCity, customer.CSState,customer.CSPostalCode,customer.CSCountry FROM customer INNER JOIN `order` ON customer.CustomerID = `order`.`CustomerID` AND `order`.ShipperID = "+shipperID+" AND `order`.`ShipDate` IS NULL AND `order`.`PaymentDate` IS NOT NULL AND `order`.`countAccomplishedOrderDetails` = `order`.`countTotalOrderDetails`");
					/*To print column names*/
			ResultSetMetaData column = rs.getMetaData();
			for (int i = 1; i < column.getColumnCount() + 1; i++) {
				System.out.print(column.getColumnName(i)+"|");
			}
			System.out.print("\n");
			while(rs.next()) {
				System.out.println
				(rs.getString(1)+"|"
				+rs.getString(2)+"|"
						+ rs.getString(3)+"|"
				+ rs.getString(4)+"|"+rs.getString(5)
				+"|"+rs.getString(6)+"|"+rs.getString
				(7)+"|"+rs.getString(8)
				+"|"+rs.getString(9)
				+"|"+rs.getString(10)
				+"|"+rs.getString(11)
						);
			}
			System.out.println("");
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Update the received date field and the ship date field of the order
	 * @param orderid
	 */
	public void shipOrder(int orderid) {
		// TODO Auto-generated method stub
		try {
			rowsAffected = st.executeUpdate("UPDATE `order` SET `ShipDate` = CURRENT_DATE(), `receivedDate` = CURRENT_DATE() WHERE `order`.`OrderID` = "+orderid+"");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Print all order details of the order
	 * @param orderNum
	 */
	public void getDisplayOrderDetailsAt(int orderNum) {
		// TODO Auto-generated method stub
		try {
			rs = st.executeQuery("SELECT orderdetail.productLabel, orderdetail.chosenSize, orderdetail.chosenColor, orderdetail.Quantity, orderdetail.priceAtOrder, orderdetail.Subtotal, orderdetail.Supplier FROM orderdetail INNER JOIN `order` ON `order`.OrderID = orderdetail.OrderID AND `order`.`OrderID` = "+orderNum+"");
			ResultSetMetaData column = rs.getMetaData();
			for (int i = 1; i < column.getColumnCount() + 1; i++) {
				System.out.print(column.getColumnName(i)+"|");
			}
			System.out.print("\n");
			while(rs.next()) {
				System.out.println(
						 rs.getString(1)+"|"
						+rs.getString(2)+"|"
						+rs.getString(3)+"|"
						+rs.getString(4)+"|"
						+rs.getString(5)+"|"
						+rs.getString(6)+"|"
						+rs.getString(7)+"|"
						);
			}
			System.out.println("");
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
