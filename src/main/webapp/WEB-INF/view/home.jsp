<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <h3>Home</h3>
  <br />
  <h4>Welcome Spring Security !!!</h4>
  <br /><br />
  <p>
    User Name : <security:authentication property="principal.username" />
    <br />
    Role(s) : <secutrity:authentication property="principal.authorities" />
  </p>
  
  <form:form action="${pageContext.request.contextPath}/logout" method="POST">
    <button type="submit" value="Logout">logout</button>
  </form:form>
</body>
</html>