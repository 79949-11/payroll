<!DOCTYPE html>
<head>
<meta charset="UTF-8">
<title>Organisation Chart</title>
</head>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<body>
${node.name}
</br>
<ul>
<c:forEach var="node" items="${node.children}">
  <li>
    <c:set var="node" value="${node}" scope="request"/>
    <jsp:include page="manager.jsp"/>
  </li>
</c:forEach>
</ul>
</body>