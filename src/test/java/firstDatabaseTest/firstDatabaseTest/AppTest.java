package firstDatabaseTest.firstDatabaseTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AppTest {
	
	Connection con;
	Statement stmt;
	ResultSet rs;
	String customerName;
	String customerLastName;
	String customerEmail;
	WebDriver driver = new ChromeDriver();
	
	@BeforeTest
	public void mysetup() throws SQLException {
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels","root","32724");
		driver.get("https://smartbuy-me.com/account/register");
	}
	
	@Test(priority=1, enabled=false)
	public void InsertIntoDatabase() throws SQLException {
		String query = "INSERT INTO customers (customerNumber, customerName, contactLastName, contactFirstName, phone, addressLine1, city, country, salesRepEmployeeNumber, creditLimit) VALUES (999, 'New Corp', 'Smith', 'John', '123456789', '123 Main St', 'Los Angeles', 'USA', 1370, 50000.00);\r\n"
				+ "";
		stmt = con .createStatement();
		int rowEffected = stmt.executeUpdate(query);
		System.out.println(rowEffected);
		
	}
	
	@Test(priority=2)
	public void UpdateDatabase() throws SQLException {
		String query = "UPDATE customers SET creditLimit = 75000 WHERE customerNumber = 999";
		stmt = con .createStatement();
		int rowEffected = stmt.executeUpdate(query);
		System.out.println(rowEffected);
		
	}
	
	@Test(priority=3)
	public void FirstName() throws SQLException {
		String query = "SELECT * FROM customers WHERE customerNumber = 999;";
		stmt = con .createStatement();
		rs = stmt.executeQuery(query);
		
		while (rs.next()) {
			customerName = rs.getString("contactFirstName");
			System.out.println(customerName);
		}
		
		driver.findElement(By.id("customer[first_name]")).sendKeys(customerName);
	

		
	}
	@Test(priority=3)
	public void LastName() throws SQLException {
		String query = "SELECT * FROM customers WHERE customerNumber = 103;";
		stmt = con .createStatement();
		rs = stmt.executeQuery(query);
		
		while (rs.next()) {
			customerLastName = rs.getString("contactLastName");
			System.out.println(customerLastName);
		}
	
		driver.findElement(By.id("customer[last_name]")).sendKeys(customerLastName);

		
	}
	
	@Test(priority=4)
	public void Email() throws SQLException {
		String query = "SELECT * FROM customers WHERE customerNumber = 999;";
		stmt = con.createStatement();
		rs = stmt.executeQuery(query);

		while (rs.next()) {
			customerLastName = rs.getString("contactLastName");
			customerEmail = customerLastName + "@gmail.com";
			System.out.println("Email: " + customerEmail);
		}

		driver.findElement(By.id("customer[email]")).sendKeys(customerEmail);
	}
	
	@Test(priority=5)
	public void Password() throws SQLException {
		String query = "SELECT * FROM customers WHERE customerNumber = 999;";
		stmt = con.createStatement();
		rs = stmt.executeQuery(query);

		String password = "";

		while (rs.next()) {
			int customerNumber = rs.getInt("customerNumber");
			String firstName = rs.getString("contactFirstName");
			password = customerNumber + firstName + "@123";
			System.out.println("Password: " + password);
		}

		driver.findElement(By.id("customer[password]")).sendKeys(password);
	}



}
