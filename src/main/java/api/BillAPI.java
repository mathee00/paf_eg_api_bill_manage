package api;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Bill;

/**
 * Servlet implementation class BillAPI
 */
@WebServlet("/BillAPI")
public class BillAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	Bill billObj = new Bill();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BillAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		Bill billObj = new Bill();
		
		String output = "";
		output = billObj.readBill();
		
		response.getWriter().append(output);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		String output = billObj.InsertBillData(
				request.getParameter("billId"),
				request.getParameter("accountNo"),
				request.getParameter("dueAmount"),
				request.getParameter("totalAmount"));
				
				response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map paras = getParasMap(request);
		String output = "";
			if(paras.get("billId") != null) {
				output = billObj.updateBill(
						
						paras.get("billId").toString(),
						paras.get("accountNo").toString(),
						paras.get("dueAmount").toString(),
						paras.get("totalAmount").toString());
						
			}
			else {
				output = billObj.updateBill(
						
						request.getParameter("billId"),
						request.getParameter("accountNo"),
						request.getParameter("dueAmount"),
						request.getParameter("totalAmount"));
			}
			response.getWriter().write(output);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map paras = getParasMap(request);
		String output = "";
		
		if (paras.get("billId") != null) {
			 
			
			output = billObj.deleteBill(paras.get("billId").toString());
			
		}
		else {
			output = billObj.deleteBill(request.getParameter("billId"));
		}
		System.out.println("ID: " + output);
		response.getWriter().write(output);
	}
	
	private static Map getParasMap(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();
		try {
			Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
			String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
			scanner.close();
			String[] params = queryString.split("&");
			for (String param : params) {

				String[] p = param.split("=");
				map.put(p[0], p[1]);
			}
		} catch (Exception e) {
			
		}
		return map;
	}


}
