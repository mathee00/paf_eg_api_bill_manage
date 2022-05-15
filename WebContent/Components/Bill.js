$(document).ready(function()
{
if ($("#alertSuccess").text().trim() == "")
 {
 $("#alertSuccess").hide();
 }
 $("#alertError").hide();
});


//SAVE ============================================
$(document).on("click", "#btnSave", function(event)
{
// Clear alerts---------------------
 $("#alertSuccess").text("");
 $("#alertSuccess").hide();
 $("#alertError").text("");
 $("#alertError").hide();
 // Form validation-------------------
var status = validateInsertForm();
if (status != true)
 {
 $("#alertError").text(status);
 $("#alertError").show();
 return;
 }

 var type = ($("#billId").val() == "") ? "POST" : "PUT";


$.ajax(
		{
		 url : "BillAPI",
		 type : type,
		data: $("#billForm").serialize(),
		 dataType : "text",
		 complete : function(response, status)
		 {
		 onBillSaveComplete(response.responseText, status);
		 }
		});

});

function onBillSaveComplete(response, status)
{
if (status == "success")
 {
	var resultSet = JSON.parse(response);
	if (resultSet.status.trim() == "success")
	{
		$("#alertSuccess").text("Successfully saved.");
		$("#alertSuccess").show();
		
		$("#divUserGrid").html(resultSet.data);
	} else if (resultSet.status.trim() == "error")
	{
		$("#alertError").text(resultSet.data);
		$("#alertError").show();
	}
 	} else if (status == "error")
 	{
 		$("#alertError").text("Error while saving.");
 		$("#alertError").show();
 	} else
 	{
 		$("#alertError").text("Unknown error while saving..");
 		$("#alertError").show();
 	}
		$("#billId").val("");
		$("#billForm")[0].reset();
}


//UPDATE==========================================
$(document).on("click", ".btnUpdate", function(event)
{
 $("#billId").val($(this).closest("tr").find('td:eq(0)').text());
 $("#accountNo").val($(this).closest("tr").find('td:eq(1)').text());
 $("#dueAmount").val($(this).closest("tr").find('td:eq(2)').text()); 
 $("#totalAmount").val($(this).closest("tr").find('td:eq(3)').text());

});

$(document).on("click", ".btnRemove", function(event)
		{
		 $.ajax(
		 {
		 url : "BillAPI",
		 type : "DELETE",
		 data : "billId=" + $(this).data("billId"),
		 dataType : "text",
		 complete : function(response, status)
		 {
		 onBillDeleteComplete(response.responseText, status);
		 }
		 });
		});

function onBillDeleteComplete(response, status)
{
if (status == "success")
 {
 var resultSet = JSON.parse(response);
 if (resultSet.status.trim() == "success")
 {
 $("#alertSuccess").text("Successfully deleted.");
 $("#alertSuccess").show();
 $("#divUserGrid").html(resultSet.data);
 } else if (resultSet.status.trim() == "error")
 {
 $("#alertError").text(resultSet.data);
 $("#alertError").show();
 }
 } else if (status == "error")
 {
 $("#alertError").text("Error while deleting.");
 $("#alertError").show();
 } else
 {
 $("#alertError").text("Unknown error while deleting..");
 $("#alertError").show();
 }
}

//CLIENTMODEL=========================================================================
function validateInsertForm()
{
//bill_id
if ($("#billId").val().trim() == "")
{
return "Insert Bill ID.";
}

//account_no
if ($("#accountNo").val().trim() == "")
{
return "Insert Account No.";
}

//due_amount
if ($("#dueAmount").val().trim() == "")
{
return "Insert Due Amount.";
}

//total_amount
if ($("#totalAmount").val().trim() == "")
{
return "Insert Total Amount.";
}

return true;
}