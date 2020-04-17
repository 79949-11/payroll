<!DOCTYPE html>
<html>
<style>
.table {
  border-collapse: collapse;
   table-layout: fixed;
}

.table, th, .b1 {
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
<head>
</head>
<body>

<table class="table">
    <tr>
    <th>Employee ID</th>
    <th>Payment Date</th>
    <th>Pay Received</th>
    <th>Tax Amount</th>
    <th>Super Amount</th>
    <th>Pay Period Start</th>
    <th>Pay Period End</th>
    <th>Export PDF</th>
    </tr>

    <c:forEach items="${payslipList}" var="dataItem">
    <tr class="striped">
           <td class="b1"><c:out value="${dataItem.employeeId}" /></td>
           <td class="b1"><c:out value="${dataItem.paymentDate}" /></td>
           <td class="b1"><c:out value="${dataItem.salary}" /></td>
           <td class="b1"><c:out value="${dataItem.taxAmount}" /></td>
           <td class="b1"><c:out value="${dataItem.superAmount}" /></td>
           <td class="b1"><c:out value="${dataItem.payPeriodStart}" /></td>
           <td class="b1"><c:out value="${dataItem.payPeriodEnd}" /></td>
           <td class="b1"><form action='export_pdf_payslip' method = "POST">
                      <input type="submit" onsubmit="return confirm('Do you want to export to C:/IB/CS IA/PaySlip.pdf?);" class="floated" value="Export PDF"/>
                      <input type="hidden" name = "id" value="${dataItem.id}"/></form></td>
        </tr>
    </c:forEach>
    </table>

<a class="button" href="logout"> Sign Out</a>
</body>