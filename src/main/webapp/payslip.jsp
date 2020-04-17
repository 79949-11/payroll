<!DOCTYPE html>
<head>
<style>
.table1 {
    width: 50%;
    table-layout: fixed;
    border: 0px;
     }

.floated {
        float:left;
        margin-right:5px;
     }

.table2 {
  border-collapse: collapse;
   table-layout: fixed;
}

.table2, th, .b1 {
  border-collapse: collapse;
  border: 1px solid black;
  text-align: left;
  width: 100%;
  }

th, td {
  padding: 15px;
}

th {
  background-color: #70CAFF;
  color: black;

}

.striped:nth-child(even){background-color: #f2f2f2}

</style>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
</head>

<body>

<table class="table1">
<form action = "new_payslip" method = "post">
	<tr>
	    <td>Employee ID:</td>
	    <td><select name = "employeeId" required>
                       <c:forEach items="${employeeList}" var="item">
                       <option value="${item.id}">${item.firstName} ${item.surname}</option>
                       </c:forEach>
                     </select>
                </td>
	</tr>

    <tr>
        <td> Pay Cycle:</td>
        <td><select name = "cycle" required>
               <option value="weekly">Weekly</option>
               <option value="monthly">Monthly</option>
               <option value="yearly">Yearly</option>
             </select>
        </td>
    </tr>

	<tr>
	    <td>Payment Date:</td>
	    <td><input type = "date" name = "paymentDate" required/></td>
	</tr>

	<tr>
        <td>Pay Received:</td>
        <td><input type = "number" name = "payReceived" min="0"required/></td>
    </tr>

    <tr>
    	<td>Pay Period Start:</td>
    	<td><input type = "date" name = "payPeriodStart" required/></td>
    </tr>

    <tr>
        <td>Pay Period End:</td>
        <td><input type = "date" name = "payPeriodEnd" required/></td>
    </tr>

	<tr>
	    <td><input type="submit" value = "Create"/></td>
	</tr>
</form>
</table>

<table class="table2">
    <tr>
    <th>Employee ID</th>
    <th>Payment Date</th>
    <th>Pay Received</th>
    <th>Tax Amount</th>
    <th>Super Amount</th>
    <th>Pay Period Start</th>
    <th>Pay Period End</th>
    <th> Actions </th>
    </tr>

    <c:forEach items="${payslipList}" var="dataItem">
    <tr class="striped">
    <form action='update_payslip' method = "post">
           <td class="b1"><c:out value="${dataItem.employeeId}"/></td>
           <td class="b1"><input type="date" value="${dataItem.paymentDate}" name="paymentDate"/></td>
           <td class="b1"><input type="number" value="${dataItem.salary}" name="salary" min="0"/></td>
           <td class="b1"><input type="number" value="${dataItem.taxAmount}" name="taxAmount"/></td>
           <td class="b1"><input type="number" value="${dataItem.superAmount}" name="superAmount"/></td>
           <td class="b1"><input type="date" value="${dataItem.payPeriodStart}" name="payPeriodStart"/></td>
           <td class="b1"><input type="date" value="${dataItem.payPeriodEnd}" name="payPeriodEnd"/></td>
           <td class="b1"><input type="submit" class="floated" value="edit"/>
           <input type="hidden" name = "idForUpdate" value="${dataItem.id}"/></form>
           <form action='delete_payslip' method = "POST">
           <input type="submit" class="floated" value="delete"/>
           <input type="hidden" name = "idForDelete" value="${dataItem.id}"/></form>
           <form action='export_pdf_payslip' method = "POST" onsubmit="return confirm('Do you want to export to C:/IB/CS IA/Payslip.pdf?');">
           <input type="submit" class="floated" value="Export PDF"/>
           <input type="hidden" name = "id" value="${dataItem.id}"/></form></td>
        </tr>
    </c:forEach>
    </table>

    <form action='export_payslip' method = "POST" onsubmit="return confirm('Do you want to export to C:/IB/CS IA/Payslip.txt?');">
    <input type = "submit" value = "Export" />
    </form>

    <a class="button" href="admin.jsp"> Back</a>
</body>
</html>