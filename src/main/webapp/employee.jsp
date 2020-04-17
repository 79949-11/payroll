<!DOCTYPE html>
<html>
<head>
<script>

function validate(text){
   var letters = /^[A-Za-z]+$/;
   if(text.value == ''){
   return true;
}
   if(text.value.match(letters))
     {
      return true;
     }
   else
     {
     alert("Please input letters only");
     return false;
     }
  }
</script>
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

.button {
         background-color: #1c87c9;
         border: none;
         color: white;
         /* Manipulates the size of the button box */
         padding: 27px 34px;
         text-align: center;
         /* Removes the underline from the text */
         text-decoration: none;
         /* Creates even blocks of background colour */
         display: inline-block;
         font-size: 20px;
         margin: 60px;
         cursor: pointer;
         margin-left: 20%;
    	 width: 8em
         }

.striped:nth-child(even){background-color: #f2f2f2}

</style>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
</head>
<body>

<table class="table1">
<form action = "new_employee" method = "post">
	<tr>
	<td>First Name:</td>
	<td><input type = "text" name = "first_name" required onfocusout="validate(first_name)"/></td>
	</tr>
	<tr>
	<td>Surname:</td>
	<td><input type = "text" name = "surname" required onfocusout="validate(surname)"/></td>
	</tr>
	<tr>
	<td>Tax Number:</td>
	<td><input type = "number" name = "tax_number" min="100000000" max="999999999" required/></td>
	</tr>
	<tr>
	<td>Username:</td>
    <td><input type="text" name = "username" required/></td>
    </tr>
    <tr>
    <td>Password:</td>
    <td><input type="text" name = "password" required/></td>
    </tr>
    <tr>
    <td>Admin:</td>
    <td><input type="checkbox" name = "adminFlag" value = "1"/></td>
    </tr>
    <tr>
    	    <td>Manager ID:</td>
    	    <td><select name = "manager_Id">
    	                   <option value=""> </option>
                           <c:forEach items="${employeeList}" var="item">
                           <option value="${item.id}">${item.firstName} ${item.surname}</option>
                           </c:forEach>
                         </select>
                    </td>
    	</tr>
	<tr>
	<td><input type="submit" value = "Create"/></td>
	</tr>
</form>
<tr>
<td><a class="button" href="display_organisation"> Organisational Chart</a></td>
</tr>
</table>

    <table class="table2">
    <tr>
    <th>Surname</th>
    <th>First Name</th>
    <th>Tax Number</th>
    <th>Username</th>
    <th>Manager ID</th>
    <th>Actions </th>
    </tr>

    <c:forEach items="${employeeList}" var="dataItem">
        <tr class="striped">
        <form action='update_employee' method = "POST">
           <td class="b1"><input type="text" value="${dataItem.surname}" name="surname"></td>
           <td class="b1"><input type="text" value="${dataItem.firstName}" name="firstName"></td>
           <td class="b1"><input type="number" value="${dataItem.taxNumber}" name="taxNumber" min="100000000" max="999999999"></td>
           <td class="b1"><c:out value="${dataItem.username}"/></td>
           <td class="b1"><input type="number" value="${dataItem.managerId}" name="managerId"></td>
           <td class="b1"><input type="submit" class="floated" value="edit"/>
           <input type="hidden" name = "idForUpdate" value="${dataItem.id}"/>
           </form>
           <form action='delete_employee' method = "POST">
           <input type="submit" class="floated" value="delete"/>
           <input type="hidden" name = "idForDelete" value="${dataItem.id}"/></form></td>
        </tr>
    </c:forEach>
    </table>
    <form action='export_employee' method = "POST" onsubmit="return confirm('Do you want to export to C:/IB/CS IA/Employee.txt?');">
    <input type = "submit" value = "Export"/>
    </form>

    <a href="admin.jsp"> Back</a>
</body>
</html>