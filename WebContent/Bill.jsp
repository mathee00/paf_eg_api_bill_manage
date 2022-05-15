<%@page import="model.Bill"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ElectroGrid Online System</title>
<style type="text/css">
		.billForm{
			width: 800px;
			margin: 50px auto;

		}
		
			*{
			margin:0 ;
			padding: 0;
			box-sizing: border-box;
		}
		.body{
			background-color: #32312f;
			font-family: sans-serif;
		}
		.table-container{
			padding: 0 10%;
			margin: 40px auto 0;
		}
		.heading{
			font-size: 40px;
			text-align: center;
			color: #f1f1f1;
			margin-bottom: 40px;
		}
		.table{
			width: 100%;
			border-collapse: collapse;
			margin-top: 50px;
		}
		.table thead{
			background-color: #76030f;
		}
		.table thead tr th{
			font-size: 14px;
			font-weight: 600;
			letter-spacing: 0.35px;
			color: #ffff;
			opacity: 1;
			padding: 12px;
			vertical-align: top;
			border: 1px solid #dee2e685;

		}
		.table tbody tr td{
			font-size: 14px;
			font-weight: normal;
			letter-spacing: 0.35px;
			color: #f1f1f1;
			background-color: #3c3f44;
			padding: 8px;
			text-align: center;
			border:1px solid #dee2e685;
		}

		#btn-record{
			margin-left: 85%;
		}
	</style>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/Bill.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
</head>
<body>
<div class="billForm" >
	<div class="container">
<form class="row g-3"  id="billForm" name="billForm" method="post" action="BillAPI">

  <div class="col-md-2">
    <label for="userid" class="form-label" >Bill ID</label>
    <input type="text" class="form-control" id="billId" name="billId" placeholder="Bill ID" >
  </div>
  <div class="col-12">
    <label for="AccountNumber" class="form-label">Account Number</label>
    <input type="text" class="form-control" id="accountNo" name="accountNo" placeholder="Account Number">
  </div>
  <div class="col-12">
    <label for="CustomerName" class="form-label">Due Amount</label>
    <input type="text" class="form-control" id="dueAmount" name="dueAmount" placeholder="Due Amount">
  </div>
  <div class="col-md-4">
    <label for="Units" class="form-label">Total Amount</label>
    <input type="text" name="units" id="totalAmount" name="totalAmount" class="form-control" placeholder="Total Amount" />      
  </div>
  
  
 <input id="btnSave" name="btnSave" type="button" value="Save"
			class="btn btn-primary"/> <input type="hidden"
				id="billId" name="billId" value=""/>
  
</form>

</div>
</div>
<div id="alertSuccess" class="alert alert-success"></div>
					<div id="alertError" class="alert alert-danger"></div>
					<br> <br>
					<div id="divUserGrid" class="table-container" name="divUserGrid">
						<%
						Bill billObj = new Bill();
						out.print(billObj.readBill());
						%>
					</div>
</body>
</html>