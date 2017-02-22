
<%@page contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="h" %>

<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>JSP example 1</title>
</head>
<body>
    <h1>JSP example 1</h1>
    <%
    String target = "JSP";
    String msg = String.format("Hello, %s!", target);
    %>
        
    <div style="color: #2222a0; font-weight: bold;">
        <%= msg %>
    </div>
    
    <div>
        <%@include file="include.message.jsp" %>
    </div>
    
    <%! 
    int count = 0;
    %>
    
    <% count ++; %>
    
    <div>
        <%= String.format("[%d]", count) %>
    </div>
    
    
</body>
</html>

