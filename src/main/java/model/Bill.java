package model;

import java.sql.*; 

public class Bill {
	private Connection connect()
	{
		Connection con = null;
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");		
			con = DriverManager.getConnection("jdbc:mysql://localhost:3308/egbilldb", "root", "");
			System.out.println("Connected!!");
		}
		catch (Exception e) {
			System.out.println("Failed");
			e.printStackTrace();
		}
		
		return con;
	}	
	
	
	//Inserting to bill
	public String InsertBillData(String billId, String accountNo, String dueAmount, String totalAmount) {
	 
		String output = "";
		try {
			Connection con = connect();
			
			if (con == null)
			{
				return  "Error while connecting to the database for inserting.";
			}
			
			
			// create a prepared statement			
			String query = "insert into bill (`billId`,`accountNo`,`dueAmount`,`totalAmount`)" + " values (?, ?, ?, ?)";			
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			 // binding values
			 preparedStmt.setString(1, billId); 
			 preparedStmt.setString(2, accountNo);
			 preparedStmt.setString(3, dueAmount);
			 preparedStmt.setString(4, totalAmount); 
	
			 
			// execute the statement			 
			 preparedStmt.execute(); 

			 con.close(); 
			 String newCons = readBill();
				output = "{\"status\":\"success\", \"data\": \"" +newCons+ "\"}";
				//output = "Inserted successfully"; 
		}
		catch (Exception e) {
			  
			 output = "{\"status\":\"error\", \"data\":\"Error while Inserting to the Bill.\"}";
			 System.err.println(e.getMessage()); 
		}
		
		return output;
		}
	
	
	//Viewing bill
	public String readBill() {
		
		String output = "";
		
		try {
			
			Connection con = connect();
			
			if(con == null) {
				return "Error while connecting to the database for reading.";
			}
			
			 // Prepare the html table to be displayed
			 output = "<table class='table' border='1'>"
					+"<thead>"
			 		+ "<tr>"
			 		+ "<th>Bill ID</th>"
			 		+ "<th>Account Number</th>" +
			 		  "<th>Due Amount</th>" + 
			 		  "<th>Total Amount</th>" +		 		
			 		 "<th>Update</th>"+
			 		 "<th>Delete</th></tr></thead>";
			 
			 String query = "select * from bill"; 
			 Statement stmt = con.createStatement(); 
			 ResultSet rs = stmt.executeQuery(query); 
			 
			 while(rs.next()) {
				 
				 String billId = rs.getString("billId");
				 String accountNo = rs.getString("accountNo"); 
				 String dueAmount = rs.getString("dueAmount"); 
				 String totalAmount = rs.getString("totalAmount"); 
				
				 
				 // Add into the HTML table
				 //output += "<tr>"; 
				 output += "<tr><td>" + billId + "</td>";
				 output += "<td>" + accountNo + "</td>";
				 output += "<td>" + dueAmount + "</td>"; 
				 output += "<td>" + totalAmount + "</td>"; 
				 
				 
				 // buttons
				 output += "<td><input id='btnUpdate'  name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary' ></td>"
							+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-billId='"
							+ billId + "'>" + "</td></tr>";
				
			 }
			 con.close(); 
			 // Complete the html table
			 output += "</table>";
		}
		catch(Exception e)
		{
			 output = "Error while reading the customer."; 
			 System.err.println(e.getMessage()); 
		}
		
		return output;
		}
	
	
	//Updating bill
	public String updateBill(String billId, String accountNo, String dueAmount, String totalAmount) {
		
		String output = "";
		
		try {
			Connection con = connect();
			
			if (con == null)
			{
				return  "Error while connecting to the database for updating.";
			}
			
			// create a prepared statement
			String query = "  UPDATE bill SET accountNo=?,dueAmount=?,totalAmount=? where billId=?";		
			PreparedStatement preparedStmt = con.prepareStatement(query);
			
			 // binding values
			 preparedStmt.setString(1,accountNo); 
			 preparedStmt.setString(2, dueAmount); 
			 preparedStmt.setString(3, totalAmount); 	
			 preparedStmt.setString(4, billId); 
			 
			// execute the statement			 
			 preparedStmt.execute(); 
			 
			 con.close(); 
			 String newcons = readBill();
			output = "{\"status\":\"success\", \"data\": \"" +newcons+ "\"}";
//			 output = "Updated successful"; 
		}
		catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while Updating the Bill.\"}";
			 
			 System.err.println(e.getMessage()); 
		}
		
		return output;
		}
	
	//Deleting bill
	public String deleteBill(String billId) {
		 String output = ""; 
		 try {
			 Connection con = connect(); 
			 
			 if (con == null) 
			 {return "Error while connecting to the database for deleting."; }
			 
			 // create a prepared statement
			 String query = "delete from bill where billId=?"; 			 
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
			 // binding values
			 preparedStmt.setString(0, billId);
			 
			 // execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
			 
			 String newcons = readBill();
				output = "{\"status\":\"success\", \"data\": \"" +newcons+ "\"}";
		 }
		 catch (Exception e) 
		 { 
			 output = "{\"status\":\"error\", \"data\":\"Error while Deleting the Bill.\"}";
			 System.err.println(e.getMessage()); 
		 } 
		 
		 return output;
	}
	
}
